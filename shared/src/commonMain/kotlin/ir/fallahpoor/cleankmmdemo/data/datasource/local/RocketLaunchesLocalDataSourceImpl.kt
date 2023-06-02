package ir.fallahpoor.cleankmmdemo.data.datasource.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import co.touchlab.kermit.Logger
import ir.fallahpoor.cleankmmdemo.data.database.Database
import ir.fallahpoor.cleankmmdemo.data.database.RocketLaunchLocalModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class RocketLaunchesLocalDataSourceImpl(
    private val database: Database,
    private val dispatcher: CoroutineDispatcher
) : RocketLaunchesLocalDataSource {

    private companion object {
        const val TAG = "RocketLaunchesLocalDataSourceImpl"
    }

    override suspend fun saveLaunches(launches: List<RocketLaunchLocalModel>) {
        Logger.i(tag = TAG) {
            "Deleting all the existing rocket launches from the database..."
        }
        database.rocketLaunchQueries.deleteLaunches()
        Logger.i(tag = TAG) {
            "Inserting the new rocket launches into the database..."
        }
        database.rocketLaunchQueries.transaction {
            launches.forEach {
                database.rocketLaunchQueries.saveLaunch(it)
            }
        }
    }

    override fun getLaunches(): Flow<List<RocketLaunchLocalModel>> =
        database.rocketLaunchQueries.getLaunches().asFlow().mapToList(dispatcher)

}