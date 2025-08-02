package app.sahhamarket.domain.usecase.promocard

import app.sahhamarket.domain.model.PromoCard
import app.sahhamarket.domain.repository.PromoCardsRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPromoCardsUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val promoCardsRepository: PromoCardsRepository
) {

    suspend operator fun invoke(): Result<List<PromoCard>> = withContext(ioDispatcher) {
        promoCardsRepository.getPromoCards()
    }
}