package app.sahhamarket.data.mapper

import app.sahhamarket.data.source.local.entities.ProductEntity
import app.sahhamarket.data.source.remote.model.ProductDto
import app.sahhamarket.domain.model.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() {

    fun toProductEntity(product: Product) = ProductEntity(
        id = product.id,
        imageUrl = product.imageUrl,
        title = product.title,
        subTitle = product.subTitle,
        price = product.price,
        currency = product.currency,
        promo = product.promo,
        rating = product.rating,
        quantity = product.quantity,
        status = product.status
    )

    fun toProduct(product: ProductDto) = Product(
        id = product.id,
        imageUrl = product.imageUrl,
        title = product.title,
        subTitle = product.subTitle,
        price = product.price,
        currency = product.currency,
        promo = product.promo,
        rating = product.rating,
        quantity = product.quantity,
        status = product.status
    )

    fun toProduct(product: ProductEntity) = Product(
        id = product.id,
        imageUrl = product.imageUrl,
        title = product.title,
        subTitle = product.subTitle,
        price = product.price,
        currency = product.currency,
        promo = product.promo,
        rating = product.rating,
        quantity = product.quantity,
        status = product.status
    )
}