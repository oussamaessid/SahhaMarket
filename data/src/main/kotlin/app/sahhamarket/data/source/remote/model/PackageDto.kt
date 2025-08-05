package app.sahhamarket.data.source.remote.model

data class PackageDto(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val subTitle: String,
    val originalPrice: String,
    val discountedPrice: String,
    val discount: Int?,
    val isDonate: Boolean
)
