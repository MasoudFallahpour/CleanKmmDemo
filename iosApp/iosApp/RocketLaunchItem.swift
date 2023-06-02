import shared
import SwiftUI

struct RocketLaunchItem: View {
    let rocketLaunch: RocketLaunch

    var body: some View {
        ZStack {
            VStack {
                LaunchImage(imageUrl: rocketLaunch.imageUrl)
                LaunchName(name: rocketLaunch.name)
                LaunchDescription(description: rocketLaunch.description_)
                LaunchResult(successful: rocketLaunch.successful)
            }
            .padding([.bottom])
        }
        .background(Colors.surface)
        .cornerRadius(15)
        .listRowBackground(Colors.background)
        .listRowSeparator(.hidden)
    }
}

struct LaunchImage: View {
    let imageUrl: String?

    var body: some View {
        if let imageUrl = imageUrl {
            AsyncImage(url: URL(string: imageUrl)) { image in
                image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(width: .infinity, height: 200)
                    .clipped()
            } placeholder: {
                ProgressView()
            }
        }
    }
}

struct LaunchName: View {
    let name: String

    var body: some View {
        HStack {
            Text(Strings.name + ": ")
                .fontWeight(.bold)
            Text(name)
                .lineLimit(1)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding([.top, .leading, .trailing])
    }
}

struct LaunchDescription: View {
    let description: String?

    var body: some View {
        HStack {
            Text(Strings.description + ": ")
                .fontWeight(.bold)
            Text(description ?? Strings.notAvailable)
                .lineLimit(5)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding([.leading, .trailing])
    }
}

struct LaunchResult: View {
    let successful: KotlinBoolean?

    var body: some View {
        HStack {
            Text(Strings.successful + ": ")
                .fontWeight(.bold)
            if let successful = successful {
                Text((successful as NSNumber).boolValue ? Strings.yes : Strings.no)
            } else {
                Text(Strings.notAvailable)
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding([.leading, .trailing])
    }
}

struct RocketLaunchItem_Previews: PreviewProvider {
    static var previews: some View {
        RocketLaunchItem(rocketLaunch: RocketLaunch(
            id: "1",
            name: "Flight 1",
            flightNumber: 1001,
            description: "First test flight with some long description so that it's at least two lines",
            successful: true,
            imageUrl: "https://farm2.staticflickr.com/1648/23827554109_837b21739e_o.jpg")
        )
        .preferredColorScheme(.light)
        .previewDisplayName("Light mode")

        RocketLaunchItem(rocketLaunch: RocketLaunch(
            id: "1",
            name: "Flight 1",
            flightNumber: 1001,
            description: "First test flight",
            successful: true,
            imageUrl: "https://farm2.staticflickr.com/1648/23827554109_837b21739e_o.jpg")
        )
        .preferredColorScheme(.dark)
        .previewDisplayName("Dark mode")

        RocketLaunchItem(rocketLaunch: RocketLaunch(
            id: "1",
            name: "Flight 1",
            flightNumber: 1001,
            description: "First test flight",
            successful: true,
            imageUrl: nil)
        )
        .previewDisplayName("No Image")
    }
}
