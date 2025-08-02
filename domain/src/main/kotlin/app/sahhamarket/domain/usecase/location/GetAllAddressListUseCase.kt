package app.sahhamarket.domain.usecase.location

import app.sahhamarket.domain.model.Address
import app.sahhamarket.domain.repository.LocationRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllAddressListUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val locationRepository: LocationRepository
) {

    operator fun invoke(): Flow<List<Address>> {
        return locationRepository.getAllAddressList().flowOn(ioDispatcher)
    }
}