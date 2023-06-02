package ir.fallahpoor.cleankmmdemo.data.repository

import co.touchlab.kermit.Logger
import ir.fallahpoor.cleankmmdemo.data.database.RocketLaunchLocalModel
import ir.fallahpoor.cleankmmdemo.data.datasource.local.RocketLaunchesLocalDataSource
import ir.fallahpoor.cleankmmdemo.data.datasource.remote.RocketLaunchesRemoteDataSource
import ir.fallahpoor.cleankmmdemo.data.network.api.model.toRocketLaunchLocalModels
import ir.fallahpoor.cleankmmdemo.data.network.api.model.toRocketLaunches
import ir.fallahpoor.cleankmmdemo.domain.model.RocketLaunch
import ir.fallahpoor.cleankmmdemo.domain.repository.RocketLaunchesRepository
import kotlinx.coroutines.flow.*

class RocketLaunchesRepositoryImpl(
    private val rocketLaunchesRemoteDataSource: RocketLaunchesRemoteDataSource,
    private val rocketLaunchesLocalDataSource: RocketLaunchesLocalDataSource
) : RocketLaunchesRepository {

    private companion object {
        const val TAG = "RocketLaunchesRepositoryImpl"
    }

    override fun getLaunches(): Flow<List<RocketLaunch>> =
        rocketLaunchesRemoteDataSource.getLaunches()
            .onEach { rocketLaunchRemoteModels ->
                Logger.i(tag = TAG) {
                    "Saving the fetched data to local data source..."
                }
                rocketLaunchesLocalDataSource.saveLaunches(rocketLaunchRemoteModels.toRocketLaunchLocalModels())
            }
            .map { rocketLaunchRemoteModels ->
                rocketLaunchRemoteModels.toRocketLaunches()
            }
            .catch {
                Logger.e(tag = TAG, throwable = it) {
                    "The remote data source encountered an error. Emitting from the local data source..."
                }
                emitAll(
                    rocketLaunchesLocalDataSource.getLaunches()
                        .map { rocketLaunchLocalModels ->
                            rocketLaunchLocalModels.toRocketLaunches()
                        }
                )
            }

}

private fun List<RocketLaunchLocalModel>.toRocketLaunches(): List<RocketLaunch> =
    this.map { it.toRocketLaunch() }

private fun RocketLaunchLocalModel.toRocketLaunch() = RocketLaunch(
    id = this.id,
    name = this.name,
    flightNumber = this.flightNumber,
    description = this.description,
    successful = this.successful.toBoolean(),
    imageUrl = this.imageUrl
)

private fun Long?.toBoolean(): Boolean? = when {
    this == null -> null
    else -> this != 0L
}
