package ir.fallahpoor.cleankmmdemo.data.datasource.remote

import ir.fallahpoor.cleankmmdemo.data.network.api.model.RocketLaunchRemoteModel
import kotlinx.coroutines.flow.Flow

interface RocketLaunchesRemoteDataSource {
    fun getLaunches(): Flow<List<RocketLaunchRemoteModel>>
}