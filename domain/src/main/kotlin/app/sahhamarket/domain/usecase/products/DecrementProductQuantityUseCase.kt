package app.sahhamarket.domain.usecase.products

import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.repository.ProductsRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DecrementProductQuantityUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val productsRepository: ProductsRepository,
) {

    suspend operator fun invoke(product: Product): Result<Unit> = withContext(ioDispatcher) {
        runCatching {
            productsRepository.decrementProductQuantity(product)
        }
    }
}