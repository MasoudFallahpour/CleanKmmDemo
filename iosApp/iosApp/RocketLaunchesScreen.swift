import KMMViewModelSwiftUI
import shared
import SwiftUI

struct RocketLaunchesScreen: View {
    @ObservedViewModel var viewModel: RocketLaunchesViewModel

    var body: some View {
        ZStack {
            Colors.background.ignoresSafeArea()
            let uiState: RocketLaunchesScreenUiState = viewModel.uiState
            if let successUiState = uiState as? RocketLaunchesScreenUiState.Success {
                RocketLaunches(rocketLaunches: successUiState.rocketLaunches)
            } else if let errorUiState = uiState as? RocketLaunchesScreenUiState.Error {
                Text(errorUiState.message)
            } else {
                ProgressView()
            }
        }.onAppear {
            viewModel.getRocketLaunches()
        }
    }
}
