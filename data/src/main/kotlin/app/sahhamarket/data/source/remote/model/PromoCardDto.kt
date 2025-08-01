package app.sahhamarket.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class PromoCardDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("discount")
    val discount: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("imageUrl")
    val imageUrl: String? = null
)
