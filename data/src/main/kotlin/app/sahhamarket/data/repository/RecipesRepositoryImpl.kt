package app.sahhamarket.data.repository

import app.sahhamarket.data.mapper.RecipeMapper
import app.sahhamarket.data.source.remote.model.IngredientDto
import app.sahhamarket.data.source.remote.model.ReceiptDto
import app.sahhamarket.domain.model.Receipt
import app.sahhamarket.domain.repository.RecipesRepository
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipeMapper: RecipeMapper
) : RecipesRepository {
    override suspend fun getRecipes(): Result<List<Receipt>> {
        return Result.success(receipts.map { recipeMapper.toRecipe(it) })
    }
}

private val receipts = listOf(
    ReceiptDto(
        id = 1L,
        imageUrl = "file:///android_asset/ic_receipt_1.png",
        title = "Spagheti with Pesto Genovese and Basilico di Parma.",
        ingredients = listOf(
            IngredientDto(
                id = 1L,
                name = "Cherrytoma-ten 250g (Marokko)",
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png"
            ),
            IngredientDto(
                id = 2L,
                name = "Salt lorem ipsum text until here",
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png"
            )
        ),
        price = 5.00F,
        currency = "SAR",
        time = "25 min"
    ),
    ReceiptDto(
        id = 1L,
        imageUrl = "file:///android_asset/ic_receipt_1.png",
        title = "Avocado Toast with Homemade Guacamole, Feta Cheese and tomato.",
        ingredients = listOf(
            IngredientDto(
                id = 1L,
                name = "Cherrytoma-ten 250g (Marokko)",
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png"
            ),
            IngredientDto(
                id = 2L,
                name = "Salt lorem ipsum text until here",
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png"
            ),
            IngredientDto(
                id = 3L,
                name = "Salt lorem ipsum text until here",
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png"
            ),
            IngredientDto(
                id = 4L,
                name = "Salt lorem ipsum text until here",
                imageUrl = "file:///android_asset/ic_moscow_vr_tomatoes.png"
            )
        ),
        price = 6.45F,
        currency = "SAR",
        time = "20 min"
    ),
)