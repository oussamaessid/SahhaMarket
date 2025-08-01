package app.sahhamarket.domain.repository

import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.model.ProductDetails
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(): Result<List<Product>>
    fun getProduct(productId: Long): Flow<Result<ProductDetails>>
    suspend fun addProductToCat(product: Product)
    suspend fun decrementProductQuantity(product: Product)
}