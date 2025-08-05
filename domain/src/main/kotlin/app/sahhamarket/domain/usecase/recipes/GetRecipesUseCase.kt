package app.sahhamarket.domain.usecase.recipes

import app.sahhamarket.domain.model.Receipt
import app.sahhamarket.domain.repository.RecipesRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val recipesRepository: RecipesRepository
) {

    suspend operator fun invoke(): Result<List<Receipt>> = withContext(ioDispatcher) {
        // TODO inject the repos and api
        recipesRepository.getRecipes()
    }
}