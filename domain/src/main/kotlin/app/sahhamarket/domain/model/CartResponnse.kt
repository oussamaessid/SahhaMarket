package app.sahhamarket.domain.model

data class CartResponnse(
    val id: Long,
    val createdAt: String,
    val updatedAt: String,
    val items: List<Item>,
    val total: Long,
)

data class Item(
    val id: Long,
    val itemType: String,
    val quantity: Long,
    val price: String,
    val product: CartProduct,
)

data class CartProduct(
    val id: Long,
    val name: String,
    val price: String,
    val imageUrl: Any?,
)