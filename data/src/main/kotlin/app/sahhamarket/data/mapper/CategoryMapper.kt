package app.sahhamarket.data.mapper

import app.sahhamarket.data.source.remote.model.CategoryDto
import app.sahhamarket.domain.model.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor() {

    fun toCategory(category: CategoryDto) = Category(
        id = category.id,
        imageUrl = category.imageUrl,
        title = category.title,
        colorBg = category.colorBg,
    )
}