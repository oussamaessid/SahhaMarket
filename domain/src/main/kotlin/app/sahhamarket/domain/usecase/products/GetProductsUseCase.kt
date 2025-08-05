package app.sahhamarket.domain.usecase.products

import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.repository.ProductsRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val productsRepository: ProductsRepository,
) {

    operator fun invoke(): Flow<Result<List<Product>>> {
        return productsRepository.getProducts().flowOn(ioDispatcher)
    }
}