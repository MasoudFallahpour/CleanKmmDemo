package ir.fallahpoor.cleankmmdemo.presentation

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import ir.fallahpoor.cleankmmdemo.domain.usecase.GetRocketLaunchesUseCase
import ir.fallahpoor.cleankmmdemo.domain.usecase.UseCaseResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class RocketLaunchesViewModel(
    private val getRocketLaunchesUseCase: GetRocketLaunchesUseCase
) : KMMViewModel() {

    private var job: Job? = null
    private val _uiState = MutableStateFlow<RocketLaunchesScreenUiState>(
        viewModelScope = viewModelScope,
        value = RocketLaunchesScreenUiState.Loading
    )

    @NativeCoroutinesState
    val uiState: StateFlow<RocketLaunchesScreenUiState> = _uiState.asStateFlow()

    fun getRocketLaunches() {
        if (shouldFetchRocketLaunches()) {
            _uiState.value = RocketLaunchesScreenUiState.Loading
            job?.cancel()
            job = viewModelScope.coroutineScope.launch {
                getRocketLaunchesUseCase.execute(GetRocketLaunchesUseCase.Input)
                    .map { result: UseCaseResult<GetRocketLaunchesUseCase.Output> ->
                        mapToRocketLaunchesScreenUiState(result)
                    }
                    .collect { uiState: RocketLaunchesScreenUiState ->
                        _uiState.value = uiState
                    }
            }
        }
    }

    private fun shouldFetchRocketLaunches(): Boolean =
        _uiState.value !is RocketLaunchesScreenUiState.Success

    private fun mapToRocketLaunchesScreenUiState(result: UseCaseResult<GetRocketLaunchesUseCase.Output>): RocketLaunchesScreenUiState =
        when (result) {
            is UseCaseResult.Success -> RocketLaunchesScreenUiState.Success(result.data.rocketLaunches)
            is UseCaseResult.Error -> RocketLaunchesScreenUiState.Error(result.throwable.message.toString())
        }

}