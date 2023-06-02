package ir.fallahpoor.cleankmmdemo.data.network.api

import ir.fallahpoor.cleankmmdemo.data.network.api.model.RocketLaunchRemoteModel
import kotlinx.coroutines.flow.Flow

interface SpacexApi {
    fun getRocketLaunches(): Flow<List<RocketLaunchRemoteModel>>
}