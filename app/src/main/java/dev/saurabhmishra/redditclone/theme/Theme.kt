package dev.saurabhmishra.redditclone.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val darkColorPalette = darkColors(
    primary = ReddJetColors.purple200,
    primaryVariant = ReddJetColors.purple700,
    secondary = ReddJetColors.teal200
)

private val lightColorPalette = lightColors(
    primary = ReddJetColors.purple500,
    primaryVariant = ReddJetColors.purple700,
    secondary = ReddJetColors.teal200
)

@Composable
fun ReddJetTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = ReddJetTypogrphy.typography,
        shapes = ReddJetShapes.shapes,
        content = content
    )
}