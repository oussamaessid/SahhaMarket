package app.sahhamarket.data.mapper

import app.sahhamarket.data.source.remote.model.PromoCardDto
import app.sahhamarket.domain.model.PromoCard
import javax.inject.Inject

class PromoCardMapper @Inject constructor() {

    fun toPromoCard(dto: PromoCardDto): PromoCard {
        return PromoCard(
            id = dto.id,
            title = dto.title,
            discount = dto.discount,
            description = dto.description,
            imageUrl = dto.imageUrl,
            imageRes = null
        )
    }
}
