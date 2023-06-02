package ir.fallahpoor.cleankmmdemo.data.di

import ir.fallahpoor.cleankmmdemo.data.datasource.remote.RocketLaunchesRemoteDataSource
import ir.fallahpoor.cleankmmdemo.data.datasource.remote.RocketLaunchesRemoteDataSourceImpl
import ir.fallahpoor.cleankmmdemo.data.network.KtorEngineProvider
import ir.fallahpoor.cleankmmdemo.data.network.api.SpacexApi
import ir.fallahpoor.cleankmmdemo.data.network.api.SpacexApiImpl
import ir.fallahpoor.cleankmmdemo.data.repository.RocketLaunchesRepositoryImpl
import ir.fallahpoor.cleankmmdemo.domain.repository.RocketLaunchesRepository
import ir.fallahpoor.cleankmmdemo.presentation.RocketLaunchesViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun koinPlatformModule(): Module

// This should be called by the Android client
fun initKoin(koinAppDeclaration: KoinAppDeclaration = {}) = startKoin {
    modules(koinCommonModule())
    koinAppDeclaration()
}

// This should be called by the iOS client
fun initKoin() = initKoin {}

private fun koinCommonModule(): Module = module {
    includes(koinPlatformModule())
    factory { RocketLaunchesViewModel(get()) }
    factory<RocketLaunchesRepository> {
        RocketLaunchesRepositoryImpl(
            rocketLaunchesLocalDataSource = get(),
            rocketLaunchesRemoteDataSource = get()
        )
    }
    factory<RocketLaunchesRemoteDataSource> { RocketLaunchesRemoteDataSourceImpl(spacexApi = get()) }
    single<SpacexApi> { SpacexApiImpl(get()) }
    single { KtorEngineProvider.getEngine() }
}