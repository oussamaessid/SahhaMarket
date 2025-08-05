package app.sahhamarket.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class ReceiptDto(
    val id: Long,
    @SerializedName("image_url")
    val imageUrl: String,
    val title: String,
    val time: String,
    val price: Float,
    val currency: String,
    val quantity: Int = 1,
    val ingredients: List<IngredientDto>
)

data class IngredientDto(
    val id: Long,
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String
)
