package app.sahhamarket.compose.model

sealed class ActionResource {
    data class StringRes(val resId: Int) : ActionResource()
    data class DrawableRes(val resId: Int) : ActionResource()
}