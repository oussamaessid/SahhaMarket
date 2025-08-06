package app.sahhamarket.domain.model

data class Package(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val subTitle: String,
    val originalPrice: String,
    val discountedPrice: String,
    val discount: Int? = null,
    val isDonate: Boolean = false
)
