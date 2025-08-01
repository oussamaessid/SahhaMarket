package app.sahhamarket.data.repository

import app.sahhamarket.data.mapper.PromoCardMapper
import app.sahhamarket.data.source.remote.model.PromoCardDto
import app.sahhamarket.domain.model.PromoCard
import app.sahhamarket.domain.repository.PromoCardsRepository
import javax.inject.Inject

class PromoCardsRepositoryImpl @Inject constructor(
    private val promoCardMapper: PromoCardMapper
): PromoCardsRepository {
    override suspend fun getPromoCards(): Result<List<PromoCard>> {
        return Result.success(promoCards.map { promoCardMapper.toPromoCard(it) })
    }

}

private val promoCards = listOf(
    PromoCardDto(
        id = 1L,
        title = "Fresh Vegetables",
        discount = "30% OFF",
        description = "Organic & Fresh Daily",
        imageUrl = "https://howdyhealth.tamu.edu/wp-content/uploads/2023/11/3-easy-ways-to-eat-more-vegetables.jpg"
    ),
    PromoCardDto(
        id = 2L,
        title = "Seasonal Fruits",
        discount = "25% OFF",
        description = "Sweet & Juicy Selection",
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Culinary_fruits_front_view.jpg/960px-Culinary_fruits_front_view.jpg"
    ),
    PromoCardDto(
        id = 3L,
        title = "Green Package",
        discount = "20% OFF",
        description = "Eco-Friendly Products",
        imageUrl = "https://cdn.prod.website-files.com/5d2fb52b76aabef62647ed9a/6195c8e178a99295d45307cb_allgreen1000-550.jpg"
    )
)
