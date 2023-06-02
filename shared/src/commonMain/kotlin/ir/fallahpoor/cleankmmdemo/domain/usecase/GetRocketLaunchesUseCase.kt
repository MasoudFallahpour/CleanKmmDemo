package ir.fallahpoor.cleankmmdemo.domain.usecase

import ir.fallahpoor.cleankmmdemo.domain.model.RocketLaunch
import ir.fallahpoor.cleankmmdemo.domain.repository.RocketLaunchesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRocketLaunchesUseCase(
    dispatcher: CoroutineDispatcher,
    private val rocketLaunchesRepository: RocketLaunchesRepository
) : BaseUseCase<GetRocketLaunchesUseCase.Input, GetRocketLaunchesUseCase.Output>(dispatcher) {

    override fun provideData(input: Input): Flow<Output> =
        rocketLaunchesRepository.getLaunches()
            .map { rocketLaunches ->
                Output(rocketLaunches)
            }

    object Input : BaseUseCase.Input

    data class Output(val rocketLaunches: List<RocketLaunch>) : BaseUseCase.Output

}