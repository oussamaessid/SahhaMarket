package app.sahhamarket.presentation.cart.vm

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartEventBus @Inject constructor() {
    private val _cartEvents = MutableSharedFlow<Unit>(replay = 0)
    val cartEvents = _cartEvents.asSharedFlow()

    suspend fun emitCartUpdateEvent() {
        _cartEvents.emit(Unit)
    }
}
