@file:Suppress("ktlint:standard:filename")

package app.sahhamarket.compose.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class SnackbarVisualsWithIcon(
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration = if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
    @DrawableRes val icon: Int? = null,
) : SnackbarVisuals

data class SnackbarHelper(
    val snackbarHostState: SnackbarHostState,
    val snackbarScope: CoroutineScope,
)

fun SnackbarHelper.showSnackbar(snackbarVisuals: SnackbarVisuals) {
    snackbarScope.launch {
        snackbarHostState.showSnackbar(snackbarVisuals)
    }
}

fun SnackbarHostState.dismiss() {
    currentSnackbarData?.dismiss()
}