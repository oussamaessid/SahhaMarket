package app.sahhamarket.domain.model

data class Product(
    val id: Long,
    val imageUrl: String,
    val title: String,
    val subTitle: String,
    val price: Float,
    val currency: String,
    val promo: Promo? = null,
    val rating: Rating,
    val quantity: Int = 1,
    val status: Status,
)

enum class Status {
    AVAILABLE, UNAVAILABLE,
}

data class Promo(
    val value: Int,
    val price: Float,
)

data class Rating(
    val value: Float,
    val number: Int,
)