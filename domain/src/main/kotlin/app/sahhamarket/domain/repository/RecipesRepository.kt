package app.sahhamarket.domain.repository

import app.sahhamarket.domain.model.Receipt

interface RecipesRepository {
    suspend fun getRecipes(): Result<List<Receipt>>
    suspend fun addRecipesToCat(receipt: Receipt)
    suspend fun decrementRecipesQuantity(receipt: Receipt)
}