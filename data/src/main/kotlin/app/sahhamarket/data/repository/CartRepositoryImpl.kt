package app.sahhamarket.data.repository


import android.util.Log
import app.sahhamarket.data.source.local.dao.CartDao
import app.sahhamarket.domain.model.*
import app.sahhamarket.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import app.sahhamarket.data.mapper.toItem
import app.sahhamarket.data.mapper.toEntity

class CartRepositoryImpl @Inject constructor(
    private val dao: CartDao
) : CartRepository {

    override fun getCartItems(): Flow<CartResponnse> {
        return dao.getAll().map { entities ->
            val items = entities.map { it.toItem() }
            val total = items.sumOf { it.price.toDoubleOrNull() ?: 10.0 }.toLong()
            val response = CartResponnse(
                id = 1L,
                createdAt = "",
                updatedAt = "",
                items = items,
                total = total
            )
            Log.d("CartMapper", "Mapped ${items.size} items, total: $total")
            response
        }
    }


    override suspend fun addItemToCart(item: Item) {
        val existing = dao.getItemById(item.id)

        if (existing != null) {
            val newQuantity = existing.quantity + item.quantity
            dao.update(existing.id, newQuantity)
        } else {
            dao.insert(item.toEntity())
        }
    }

    override suspend fun removeItemFromCart(itemId: Long) {
//        dao.delete(itemId)
    }

    override suspend fun clearCart() {
        dao.clear()
    }

    override suspend fun updateCart(item: Item) {
        dao.update(item.id,item.quantity)
    }

}
