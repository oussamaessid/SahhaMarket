package app.sahhamarket.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Notification(
    val id: Int,
    val icon: ImageVector,
    val title: String,
    val description: String,
    val date: String
)
