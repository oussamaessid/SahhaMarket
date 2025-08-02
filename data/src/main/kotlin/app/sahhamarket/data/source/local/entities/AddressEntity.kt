package app.sahhamarket.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val latitude: Double,
    val longitude: Double,
    val street: String?,
    val city: String?,
    val postalCode: String?,
    val country: String?,
    val isDefault: Boolean = false
)
