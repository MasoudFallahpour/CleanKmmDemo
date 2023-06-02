package ir.fallahpoor.cleankmmdemo.data.di

import ir.fallahpoor.cleankmmdemo.data.database.Database
import ir.fallahpoor.cleankmmdemo.data.database.DatabaseDriverProvider
import ir.fallahpoor.cleankmmdemo.data.datasource.local.RocketLaunchesLocalDataSource
import ir.fallahpoor.cleankmmdemo.data.datasource.local.RocketLaunchesLocalDataSourceImpl
import ir.fallahpoor.cleankmmdemo.domain.usecase.GetRocketLaunchesUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun koinPlatformModule(): Module = module {
    factory { GetRocketLaunchesUseCase(dispatcher = Dispatchers.Main, rocketLaunchesRepository = get()) }
    factory<RocketLaunchesLocalDataSource> { RocketLaunchesLocalDataSourceImpl(dispatcher = Dispatchers.Main, database = get()) }
    single { Database(driver = DatabaseDriverProvider().getDriver()) }
}