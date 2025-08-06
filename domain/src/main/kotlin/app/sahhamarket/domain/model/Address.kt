package app.sahhamarket.domain.model

import com.google.android.gms.maps.model.LatLng

data class Address(
    val id: Long,
    val position: LatLng,
    val name: String?,
    val street: String?,
    val city: String?,
    val postalCode: String?,
    val country: String?,
    val isDefault: Boolean = false
) {
    override fun toString(): String {
        // Ex: "10 Rue des Lilas, Paris 75012, France"
        val cityPart = listOfNotNull(city, postalCode).joinToString(" ")

        return listOfNotNull(
            street?.ifBlank { null },
            cityPart.ifBlank { null },
            country
        )
            .joinToString(", ")
    }

    fun getStreetWithPostalCode(): String{
        return listOfNotNull(
            street?.ifBlank { null },
            postalCode?.ifBlank { null },
        )
            .joinToString(", ")
    }
}
