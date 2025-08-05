package app.sahhamarket.data.mapper

import app.sahhamarket.data.source.remote.model.SubCategoryDto
import app.sahhamarket.domain.model.SubCategory
import javax.inject.Inject

class SubCategoryMapper @Inject constructor() {

    fun toSubCategory(subCategory: SubCategoryDto) = SubCategory(
        id = subCategory.id,
        name = subCategory.name,
    )
}