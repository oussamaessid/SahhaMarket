package app.sahhamarket.domain.repository

import app.sahhamarket.domain.model.Product

interface ProductsRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun addProductToCat(product: Product)
    suspend fun decrementProductQuantity(product: Product)
}