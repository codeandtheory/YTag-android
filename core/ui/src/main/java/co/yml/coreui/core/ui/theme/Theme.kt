package co.yml.coreui.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    background = DarkGrey,
    primary = DarkGrey,
    text = DarkGrey,
    button = Color.White
)

private val LightColorPalette = lightColors(
    background = Color.White,
    primary = Color.White,
    text = Color.White,
    button = DarkGrey
)

object CoreUICatalogTheme {

    val colors: CoreUICatalogColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: CoreUICatalogTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: CoreUICatalogShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}

@Composable
fun CoreUICatalogTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: CoreUICatalogTypography = CoreUICatalogTheme.typography,
    shapes: CoreUICatalogShapes = CoreUICatalogTheme.shapes,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalShapes provides shapes,
        LocalTypography provides typography
    ) {
        content()
    }
}
