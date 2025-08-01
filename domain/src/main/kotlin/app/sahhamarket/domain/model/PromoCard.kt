package app.sahhamarket.domain.model

data class PromoCard(
    val id: Long,
    val title: String,
    val discount: String,
    val description: String,
    val imageUrl: String? = null,
    val imageRes: Int? = null
)