import shared
import SwiftUI

struct RocketLaunches: View {
    let rocketLaunches: [RocketLaunch]

    var body: some View {
        if rocketLaunches.isEmpty {
            Text(Strings.noRocketLaunches)
        } else {
            List(rocketLaunches, id: \.id) { rocketLaunch in
                RocketLaunchItem(rocketLaunch: rocketLaunch)
            }
            .listStyle(.plain)
        }
    }
}

struct RocketLaunches_Previews: PreviewProvider {
    static var previews: some View {
        RocketLaunches(rocketLaunches:
            [
                RocketLaunch(
                    id: "1",
                    name: "Flight 1",
                    flightNumber: 1001,
                    description: "First test flight",
                    successful: true,
                    imageUrl: "https://farm8.staticflickr.com/7695/17138865668_18dcce7072_o.jpg"),
                RocketLaunch(
                    id: "2",
                    name: "Flight 2",
                    flightNumber: 1002,
                    description: "Second test flight",
                    successful: false,
                    imageUrl: "https://farm2.staticflickr.com/1648/23827554109_837b21739e_o.jpg")
            ]
        )
        .preferredColorScheme(.light)
        .previewDisplayName("Non-empty rocket launches - Light mode")

        RocketLaunches(rocketLaunches:
            [
                RocketLaunch(
                    id: "1",
                    name: "Flight 1",
                    flightNumber: 1001,
                    description: "First test flight",
                    successful: true,
                    imageUrl: "https://farm8.staticflickr.com/7695/17138865668_18dcce7072_o.jpg"),
                RocketLaunch(
                    id: "2",
                    name: "Flight 2",
                    flightNumber: 1002,
                    description: "Second test flight",
                    successful: false,
                    imageUrl: "https://farm2.staticflickr.com/1648/23827554109_837b21739e_o.jpg")
            ]
        )
        .preferredColorScheme(.dark)
        .previewDisplayName("Non-empty rocket launches - Dark mode")

        RocketLaunches(rocketLaunches: [])
            .preferredColorScheme(.light)
            .previewDisplayName("No rocket launches - Light mode")

        RocketLaunches(rocketLaunches: [])
            .preferredColorScheme(.dark)
            .previewDisplayName("No rocket launches - Dark mode")
    }
}
