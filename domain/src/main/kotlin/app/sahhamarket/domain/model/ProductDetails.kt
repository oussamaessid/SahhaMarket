package app.sahhamarket.domain.model

data class ProductDetails(
    val product: Product,
    val similarProducts: List<Product>,
    val recipes: List<Receipt>
)