package app.sahhamarket.data.mapper
import app.sahhamarket.data.source.local.entities.CartItemEntity
import app.sahhamarket.data.source.local.entities.CartProductEmbedded
import app.sahhamarket.domain.model.CartProduct
import app.sahhamarket.domain.model.Item
    fun CartItemEntity.toItem(): Item {
        return Item(
            id = id,
            itemType = itemType,
            quantity = quantity,
            price = price,
            product = CartProduct(
                id = product.id,
                name = product.name,
                price = product.price,
                imageUrl = product.imageUrl
            )
        )
    }

    fun Item.toEntity(): CartItemEntity {
        return CartItemEntity(
            id = id,
            itemType = itemType,
            quantity = quantity,
            price = price,
            product = CartProductEmbedded(
                id = product.id,
                name = product.name,
                price = product.price,
                imageUrl = product.imageUrl?.toString()
            )
        )
    }
