package app.sahhamarket.domain.usecase.cart

import app.sahhamarket.domain.model.CartResponnse
import app.sahhamarket.domain.repository.CartRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCartItemUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(): Result<CartResponnse> = withContext(ioDispatcher) {
        try {
            val cartResponse = cartRepository.getCartItems().first()
            Result.success(cartResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
