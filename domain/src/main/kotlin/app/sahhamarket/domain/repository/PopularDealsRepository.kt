package app.sahhamarket.domain.repository

import app.sahhamarket.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface PopularDealsRepository {
    fun getPopularDeals(): Flow<Result<List<Product>>>
}