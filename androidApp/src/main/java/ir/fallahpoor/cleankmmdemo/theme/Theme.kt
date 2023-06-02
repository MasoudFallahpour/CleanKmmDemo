package ir.fallahpoor.cleankmmdemo.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = PeterRiver,
    background = MidnightBlue,
    surfaceVariant = WetAsphalt,
    onSurfaceVariant = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = PeterRiver,
    background = Clouds,
    surfaceVariant = Silver,
    onSurfaceVariant = Color.Black
)

@Composable
fun CleanKmmDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}