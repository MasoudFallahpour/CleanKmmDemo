package ir.fallahpoor.cleankmmdemo.domain.usecase

sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val throwable: Throwable) : UseCaseResult<Nothing>()
}