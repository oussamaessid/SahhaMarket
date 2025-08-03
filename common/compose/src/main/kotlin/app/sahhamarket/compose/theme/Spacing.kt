package app.sahhamarket.compose.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Spacing(
    val unit: Dp = 1.dp,
    val xxxs: Dp = 4.dp,
    val xxs: Dp = 6.dp,
    val xs: Dp = 8.dp,
    val ms: Dp = 12.dp,
    val s: Dp = 16.dp,
    val m: Dp = 24.dp,
    val l: Dp = 32.dp,
    val xl: Dp = 48.dp,
    val xxl: Dp = 64.dp,
    val xxxl: Dp = 96.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
