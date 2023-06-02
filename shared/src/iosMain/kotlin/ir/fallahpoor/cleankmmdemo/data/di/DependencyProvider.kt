package ir.fallahpoor.cleankmmdemo.data.di

import ir.fallahpoor.cleankmmdemo.presentation.RocketLaunchesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Suppress("unused")
class DependencyProvider : KoinComponent {
    val rocketLaunchesViewModel: RocketLaunchesViewModel by inject()
}