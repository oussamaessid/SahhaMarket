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
        imageUrl = "file:///android_asset/ic_category_packs.png",
        title = "Packages",
        colorBg = Color.parseColor("#D3B0E0")
    ),
    CategoryDto(
        id = 2L,
        imageUrl = "file:///android_asset/ic_category_fruits.png",
        title = "Fruits",
        colorBg = Color.parseColor("#F8A44C")
    ),
    CategoryDto(
        id = 3L,
        imageUrl = "file:///android_asset/ic_category_vegetables.png",
        title = "Vegetables",
        colorBg = Color.parseColor("#53B175")
    ),
    CategoryDto(
        id = 4L,
        imageUrl = "file:///android_asset/ic_category_organic_products.png",
        title = "Organic Products",
        colorBg = Color.parseColor("#F7A593")
    ),
    CategoryDto(
        id = 5L,
        imageUrl = "file:///android_asset/ic_category_recipes.png",
        title = "Recipes",
        colorBg = Color.parseColor("#E2C572")
    ),
)