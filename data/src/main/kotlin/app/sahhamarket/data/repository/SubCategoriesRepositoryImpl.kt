package app.sahhamarket.data.repository

import app.sahhamarket.data.mapper.SubCategoryMapper
import app.sahhamarket.data.source.remote.model.SubCategoryDto
import app.sahhamarket.domain.model.SubCategory
import app.sahhamarket.domain.repository.SubCategoriesRepository

import javax.inject.Inject

class SubCategoriesRepositoryImpl @Inject constructor(
    private val subCategoryMapper: SubCategoryMapper
): SubCategoriesRepository {
    override suspend fun getSubCategories(categoryId: Long): Result<List<SubCategory>> {
        return Result.success(subCategories.map { subCategoryMapper.toSubCategory(it) })
    }
}

private val subCategories = listOf(
    SubCategoryDto(
        id = 1L,
        name = "Tomatoes"
    ),
    SubCategoryDto(
        id = 2L,
        name = "Mushrooms"
    ),
    SubCategoryDto(
        id = 3L,
        name = "Pumpkin"
    ),
    SubCategoryDto(
        id = 4L,
        name = "Cucumbers"
    ),
    SubCategoryDto(
        id = 5L,
        name = "Corn"
    )
)