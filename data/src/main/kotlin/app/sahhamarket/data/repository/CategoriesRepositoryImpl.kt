package app.sahhamarket.data.repository

import android.graphics.Color
import app.sahhamarket.data.mapper.CategoryMapper
import app.sahhamarket.data.source.remote.model.CategoryDto
import app.sahhamarket.domain.model.Category
import app.sahhamarket.domain.repository.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val categoryMapper: CategoryMapper
): CategoriesRepository {
    override suspend fun getCategories(): Result<List<Category>> {
        return Result.success(categories.map { categoryMapper.toCategory(it) })
    }
}

private val categories = listOf(
    CategoryDto(
        id = 1L,
        imageUrl = "file:///android_asset/ic_packages.svg",
        title = "Packages",
        colorBg = Color.parseColor("#4AB7B6")
    ),
    CategoryDto(
        id = 1L,
        imageUrl = "file:///android_asset/ic_fruits.svg",
        title = "Fruits",
        colorBg = Color.parseColor("#4B9DCB")
    ),
    CategoryDto(
        id = 1L,
        imageUrl = "file:///android_asset/ic_vegetables.svg",
        title = "Vegetables",
        colorBg = Color.parseColor("#BB6E9D")
    ),
    CategoryDto(
        id = 1L,
        imageUrl = "file:///android_asset/ic_drinks.svg",
        title = "Drinks",
        colorBg = Color.parseColor("#4B9DCB")
    ),
)