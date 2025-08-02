package app.sahhamarket.domain.usecase.location

import app.sahhamarket.domain.model.Address
import app.sahhamarket.domain.repository.LocationRepository
import app.sahhamarket.domain.util.IoDispatcher
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAddressFromLatLngUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(position: LatLng): Address? = withContext(ioDispatcher) {
        locationRepository.getAddressFromLatLng(position)
    }
}