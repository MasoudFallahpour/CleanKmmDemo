package ir.fallahpoor.cleankmmdemo.presentation

import ir.fallahpoor.cleankmmdemo.domain.model.RocketLaunch

sealed class RocketLaunchesScreenUiState {
    object Loading : RocketLaunchesScreenUiState()
    data class Success(val rocketLaunches: List<RocketLaunch>) : RocketLaunchesScreenUiState()
    data class Error(val message: String) : RocketLaunchesScreenUiState()
}