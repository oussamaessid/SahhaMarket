package app.sahhamarket.domain.repository

import app.sahhamarket.domain.model.Category

interface CategoriesRepository {
    suspend fun getCategories(): Result<List<Category>>
}