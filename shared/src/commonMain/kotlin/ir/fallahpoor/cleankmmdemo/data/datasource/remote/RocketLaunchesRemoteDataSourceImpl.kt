package ir.fallahpoor.cleankmmdemo.data.datasource.remote

import ir.fallahpoor.cleankmmdemo.data.network.api.SpacexApi
import ir.fallahpoor.cleankmmdemo.data.network.api.model.RocketLaunchRemoteModel
import kotlinx.coroutines.flow.Flow

class RocketLaunchesRemoteDataSourceImpl(
    private val spacexApi: SpacexApi
) : RocketLaunchesRemoteDataSource {
    override fun getLaunches(): Flow<List<RocketLaunchRemoteModel>> = spacexApi.getRocketLaunches()
}