package app.sahhamarket.domain.usecase.products

import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.repository.ProductsRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val productsRepository: ProductsRepository,
) {

    suspend operator fun invoke(): Result<List<Product>> = withContext(ioDispatcher) {
        productsRepository.getProducts()
    }
}