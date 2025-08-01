package app.sahhamarket.data.mapper

import app.sahhamarket.data.source.local.entities.AddressEntity
import app.sahhamarket.domain.model.Address
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class AddressMapper @Inject constructor() {

    fun toAddressEntity(address: Address) = AddressEntity(
        id = address.id,
        latitude = address.position.latitude,
        longitude = address.position.longitude,
        street = address.street,
        city = address.city,
        postalCode = address.postalCode,
        country = address.country,
        isDefault = address.isDefault
    )

    fun toAddress(address: AddressEntity) = Address(
        id = address.id,
        position = LatLng(address.latitude, address.longitude),
        street = address.street,
        city = address.city,
        postalCode = address.postalCode,
        country = address.country,
        isDefault = address.isDefault
    )
}