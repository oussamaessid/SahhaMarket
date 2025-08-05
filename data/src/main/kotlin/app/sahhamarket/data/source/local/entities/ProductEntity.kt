package app.sahhamarket.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.sahhamarket.domain.model.Promo
import app.sahhamarket.domain.model.Rating
import app.sahhamarket.domain.model.Status

@Entity
data class ProductEntity(
    @PrimaryKey
    val id: Long,
    val imageUrl: String,
    val title: String,
    val subTitle: String,
    val price: Float,
    val currency: String,
    val promo: Promo? = null,
    val rating: Rating,
    val quantity: Int = 1,
    val status: Status
)