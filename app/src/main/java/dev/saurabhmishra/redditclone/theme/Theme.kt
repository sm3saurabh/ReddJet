package dev.saurabhmishra.redditclone.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = RedditCloneColors.purple200,
    primaryVariant = RedditCloneColors.purple700,
    secondary = RedditCloneColors.teal200
)

private val LightColorPalette = lightColors(
    primary = RedditCloneColors.purple500,
    primaryVariant = RedditCloneColors.purple700,
    secondary = RedditCloneColors.teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun RedditCloneTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = RedditCloneTypography.typography,
        shapes = RedditCloneShapes.shapes,
        content = content
    )
}