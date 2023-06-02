package ir.fallahpoor.cleankmmdemo.domain.repository

import ir.fallahpoor.cleankmmdemo.domain.model.RocketLaunch
import kotlinx.coroutines.flow.Flow

interface RocketLaunchesRepository {
    fun getLaunches(): Flow<List<RocketLaunch>>
}