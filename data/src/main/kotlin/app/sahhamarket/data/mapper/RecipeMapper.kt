package app.sahhamarket.data.mapper

import app.sahhamarket.data.source.remote.model.IngredientDto
import app.sahhamarket.data.source.remote.model.ReceiptDto
import app.sahhamarket.domain.model.Ingredient
import app.sahhamarket.domain.model.Receipt
import javax.inject.Inject

class RecipeMapper @Inject constructor() {

    fun toRecipe(receipt: ReceiptDto) = Receipt(
        id = receipt.id,
        imageUrl = receipt.imageUrl,
        title = receipt.title,
        time = receipt.time,
        price = receipt.price,
        currency = receipt.currency,
        ingredients = receipt.ingredients.map { toIngredient(it) }
    )

    private fun toIngredient(ingredient: IngredientDto) = Ingredient(
        id = ingredient.id,
        name = ingredient.name,
        imageUrl = ingredient.imageUrl,
    )
}