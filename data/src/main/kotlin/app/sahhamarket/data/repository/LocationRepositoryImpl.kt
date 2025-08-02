package app.sahhamarket.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.os.Build
import androidx.annotation.RequiresApi
import app.sahhamarket.data.mapper.AddressMapper
import app.sahhamarket.data.source.local.dao.AddressDao
import app.sahhamarket.domain.model.Address
import app.sahhamarket.domain.repository.LocationRepository
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val addressTable: AddressDao,
    private val addressMapper: AddressMapper,
) : LocationRepository {

    @SuppressLint("MissingPermission")
    override suspend fun getDevicePosition(): Result<LatLng> {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        return try {
            val location = fusedLocationProviderClient.lastLocation.await()
            Result.success(LatLng(location.latitude, location.longitude))
        } catch (e: Exception) {
            Result.failure(Exception("Unable to regain the position"))
        }
    }

    @RequiresApi(Build.VERSION_CODES.DONUT)
    override suspend fun getAddressFromLatLng(position: LatLng): Address? {
        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(position.latitude, position.longitude, 1)

            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0]
                Address(
                    id = Date().time,
                    position = position,
                    street = listOfNotNull(
                        address.subThoroughfare,
                        address.thoroughfare
                    ).joinToString(" "),
                    city = address.locality,
                    postalCode = address.postalCode,
                    country = address.countryName
                )
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun addNewAddress(address: Address) {
        addressTable.addNewAddress(addressMapper.toAddressEntity(address))
    }

    override fun getAllAddressList(): Flow<List<Address>> =
        addressTable.getAddressList().map { result -> result.map { addressMapper.toAddress(it) } }

    override suspend fun getDefaultAddress(): Address =
        addressMapper.toAddress(addressTable.getDefaultAddress())
}