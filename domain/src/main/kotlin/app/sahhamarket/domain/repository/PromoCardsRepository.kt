package app.sahhamarket.domain.repository

import app.sahhamarket.domain.model.PromoCard

interface PromoCardsRepository {
    suspend fun getPromoCards(): Result<List<PromoCard>>
}