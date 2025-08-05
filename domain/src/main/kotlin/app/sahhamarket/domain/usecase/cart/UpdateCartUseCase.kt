package app.sahhamarket.domain.usecase.cart

import app.sahhamarket.domain.model.Address
import app.sahhamarket.domain.model.CartProduct
import app.sahhamarket.domain.model.CartResponnse
import app.sahhamarket.domain.model.Item
import app.sahhamarket.domain.repository.CartRepository
import app.sahhamarket.domain.repository.LocationRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateCartUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(item: Item) = withContext(ioDispatcher) {
        cartRepository.updateCart(item)
    }
}
