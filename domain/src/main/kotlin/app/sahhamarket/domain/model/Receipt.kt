package app.sahhamarket.domain.model

data class Receipt(
    val id: Long,
    val imageUrl: String,
    val title: String,
    val time: String,
    val price: Float,
    val currency: String,
    val ingredients: List<Ingredient>
)

data class Ingredient(
    val id: Long,
    val name: String,
    val imageUrl: String
)
