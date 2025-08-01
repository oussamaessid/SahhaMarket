package app.sahhamarket.domain.usecase.categories

import app.sahhamarket.domain.model.Category
import app.sahhamarket.domain.repository.CategoriesRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val categoriesRepository: CategoriesRepository
) {

    suspend operator fun invoke(): Result<List<Category>> = withContext(ioDispatcher) {
        categoriesRepository.getCategories()
    }
}