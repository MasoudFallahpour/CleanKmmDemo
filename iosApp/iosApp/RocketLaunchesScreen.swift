import KMMViewModelSwiftUI
import shared
import SwiftUI

struct RocketLaunchesScreen: View {
    @ObservedViewModel var viewModel: RocketLaunchesViewModel

    var body: some View {
        ZStack {
            Colors.background.ignoresSafeArea()
            let uiState: RocketLaunchesScreenUiState = viewModel.uiState
            switch uiState {
            case let successUiState as RocketLaunchesScreenUiState.Success:
                RocketLaunches(rocketLaunches: successUiState.rocketLaunches)
            case let errorUiState as RocketLaunchesScreenUiState.Error:
                Text(errorUiState.message)
            default:
                ProgressView()
            }
        }.onAppear {
            viewModel.getRocketLaunches()
        }
    }
}
