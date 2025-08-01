package app.sahhamarket.domain.repository

import app.sahhamarket.domain.model.Address
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getDevicePosition(): Result<LatLng>
    suspend fun getAddressFromLatLng(position: LatLng): Address?
    suspend fun addNewAddress(address: Address)
    fun getAllAddressList(): Flow<List<Address>>
    suspend fun getDefaultAddress(): Address

}