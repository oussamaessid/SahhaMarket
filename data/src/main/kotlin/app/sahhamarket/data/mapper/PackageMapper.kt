package app.sahhamarket.data.mapper

import app.sahhamarket.data.source.remote.model.PackageDto
import app.sahhamarket.domain.model.Package
import javax.inject.Inject

class PackageMapper @Inject constructor() {

    fun toPackage(dto: PackageDto) = Package(
        id = dto.id,
        imageUrl = dto.imageUrl,
        title = dto.title,
        subTitle = dto.subTitle,
        originalPrice = dto.originalPrice,
        discountedPrice = dto.discountedPrice,
        discount = dto.discount,
        isDonate = dto.isDonate
    )
}