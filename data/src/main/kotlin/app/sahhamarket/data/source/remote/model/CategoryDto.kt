package app.sahhamarket.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    val id: Long,
    @SerializedName("image_url")
    val imageUrl: String,
    val title: String,
    @SerializedName("color_bg")
    val colorBg: Int,
)
