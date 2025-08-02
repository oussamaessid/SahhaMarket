package app.sahhamarket.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded

@Entity(tableName = "cart_items")
public data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val itemType: String,
    val quantity: Long,
    val price: String,

    @Embedded(prefix = "product_")
    val product: CartProductEmbedded
)

data class CartProductEmbedded(
    val id: Long,
    val name: String,
    val price: String,
    val imageUrl: String? = null // Store as string
)
