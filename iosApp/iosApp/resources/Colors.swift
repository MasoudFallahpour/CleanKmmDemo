import SwiftUI

class Colors {
    private static let midnightBlue = UIColor(hex: 0x2C3E50)
    private static let wetAsphalt = UIColor(hex: 0x34495E)
    private static let clouds = UIColor(hex: 0xECF0F1)
    private static let silver = UIColor(hex: 0xBDC3C7)
    private static let peterRiver = UIColor(hex: 0x3498DB)
    
    private static let primaryLight = Color(uiColor: peterRiver)
    private static let backgroundLight = Color(uiColor: clouds)
    private static let surfaceLight = Color(uiColor: silver)

    private static let primaryDark = Color(uiColor: peterRiver)
    private static let backgroundDark = Color(uiColor: midnightBlue)
    private static let surfaceDark = Color(uiColor: wetAsphalt)

    static let primary = Color(light: primaryLight, dark: primaryDark)
    static let background = Color(light: backgroundLight, dark: backgroundDark)
    static let surface = Color(light: surfaceLight, dark: surfaceDark)
}

private extension Color {
    init(light: Self, dark: Self) {
        self.init(uiColor: UIColor(light: UIColor(light), dark: UIColor(dark)))
    }
}

private extension UIColor {
    convenience init(hex: Int64, alpha: Double = 1.0) {
        let red = Double((hex >> 16) & 0xFF) / 255
        let green = Double((hex >> 8) & 0xFF) / 255
        let blue = Double((hex >> 0) & 0xFF) / 255
        self.init(red: red, green: green, blue: blue, alpha: alpha)
    }

    convenience init(light: UIColor, dark: UIColor) {
        self.init { traits in
            switch traits.userInterfaceStyle {
            case .light, .unspecified:
                return light
            case .dark:
                return dark
            @unknown default:
                return light
            }
        }
    }
}
