package app.sahhamarket.domain.model

data class PreviousOrder(
    val orderId: String,
    val deliveryDate: String,
    val status: String,
    val finalTotal: String,
    val items: List<Product>,
    val moreItemsCount: Int,
)