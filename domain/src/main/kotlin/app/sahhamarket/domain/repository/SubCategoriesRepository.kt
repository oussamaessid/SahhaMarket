package app.sahhamarket.domain.repository

import app.sahhamarket.domain.model.SubCategory

interface SubCategoriesRepository {
    suspend fun getSubCategories(categoryId: Long): Result<List<SubCategory>>
}