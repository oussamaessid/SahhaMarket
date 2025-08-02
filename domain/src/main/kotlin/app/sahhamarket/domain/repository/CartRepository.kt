package app.sahhamarket.domain.repository

import app.sahhamarket.domain.model.CartResponnse
import app.sahhamarket.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<CartResponnse>
    suspend fun addItemToCart(item: Item)
    suspend fun removeItemFromCart(itemId: Long)
    suspend fun clearCart()
    suspend fun updateCart(item: Item)
}
