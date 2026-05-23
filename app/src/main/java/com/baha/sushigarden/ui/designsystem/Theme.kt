package com.baha.sushigarden.ui.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val SushiGardenColorScheme =
    darkColorScheme(
        primary = AppColor.accent,
        onPrimary = AppColor.textPrimary,
        background = AppColor.background,
        onBackground = AppColor.textPrimary,
        surface = AppColor.tabBar,
        onSurface = AppColor.textPrimary,
        surfaceVariant = AppColor.pricePill,
        onSurfaceVariant = AppColor.textSecondary,
        secondary = AppColor.textSecondary,
        onSecondary = AppColor.textPrimary,
        outline = AppColor.inactive,
    )

@Composable
fun SushiGardenTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SushiGardenColorScheme,
        typography = androidx.compose.material3.Typography(),
        content = content,
    )
}
