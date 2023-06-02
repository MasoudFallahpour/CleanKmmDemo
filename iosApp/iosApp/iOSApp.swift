import shared
import SwiftUI

@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            RocketLaunchesScreen(viewModel: DependencyProvider().rocketLaunchesViewModel)
        }
    }
}
