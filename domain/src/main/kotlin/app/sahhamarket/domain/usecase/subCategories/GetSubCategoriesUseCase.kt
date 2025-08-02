package app.sahhamarket.domain.usecase.subCategories

import app.sahhamarket.domain.model.SubCategory
import app.sahhamarket.domain.repository.SubCategoriesRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSubCategoriesUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val subCategoriesRepository: SubCategoriesRepository,
) {

    suspend operator fun invoke(categoryId: Long): Result<List<SubCategory>> =
        withContext(ioDispatcher) {
            subCategoriesRepository.getSubCategories(categoryId)
        }
}