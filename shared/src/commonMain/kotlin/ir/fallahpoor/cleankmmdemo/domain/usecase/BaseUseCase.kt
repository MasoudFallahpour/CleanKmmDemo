package ir.fallahpoor.cleankmmdemo.domain.usecase

import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class BaseUseCase<I : BaseUseCase.Input, O : BaseUseCase.Output>(
    private val dispatcher: CoroutineDispatcher
) {

    private companion object {
        const val TAG = "BaseUseCase"
    }

    fun execute(input: I): Flow<UseCaseResult<O>> =
        provideData(input)
            .map { data: O ->
                Logger.i(TAG) {
                    "Emitting Result.Success..."
                }
                UseCaseResult.Success(data) as UseCaseResult<O>
            }
            .flowOn(dispatcher)
            .catch { throwable ->
                Logger.e(TAG, throwable) {
                    "An error occurred. Emitting Result.Error..."
                }
                emit(UseCaseResult.Error(throwable))
            }

    internal abstract fun provideData(input: I): Flow<O>

    interface Input

    interface Output

}