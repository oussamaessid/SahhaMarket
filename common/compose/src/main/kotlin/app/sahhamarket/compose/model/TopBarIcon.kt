package app.sahhamarket.compose.model

data class TopBarIcon(
    val icon: ActionResource,
    val action: () -> Unit,
)
