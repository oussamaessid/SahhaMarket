package app.sahhamarket.domain.model

data class DailyReceipt(
    val id: Int,
    val name: String,
    val description: String,
    val ingredients: String,
    val cookingTime: String,
    val price: String,
    val imageRes: Int,
    val isFavorite: Boolean = false,
    val ingredientImages: Map<String, Int> = emptyMap()
)