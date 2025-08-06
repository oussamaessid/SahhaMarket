package app.sahhamarket.data.source.remote

import kotlinx.coroutines.flow.MutableStateFlow

object UnauthorizedHandler {
    val isUnauthorized = MutableStateFlow(false)
}
