package ir.fallahpoor.cleankmmdemo.data.network.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ir.fallahpoor.cleankmmdemo.data.network.api.model.RocketLaunchRemoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SpacexApiImpl(private val httpClient: HttpClient) : SpacexApi {
    override fun getRocketLaunches(): Flow<List<RocketLaunchRemoteModel>> {
        // TODO: Limit the number of returned results
        return flow {
            emit(httpClient.get("https://api.spacexdata.com/v5/launches").body())
        }
    }
}