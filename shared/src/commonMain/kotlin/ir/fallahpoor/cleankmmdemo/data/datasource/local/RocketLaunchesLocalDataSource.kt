package ir.fallahpoor.cleankmmdemo.data.datasource.local

import ir.fallahpoor.cleankmmdemo.data.database.RocketLaunchLocalModel
import kotlinx.coroutines.flow.Flow

interface RocketLaunchesLocalDataSource {
    suspend fun saveLaunches(launches: List<RocketLaunchLocalModel>)

    fun getLaunches(): Flow<List<RocketLaunchLocalModel>>
}