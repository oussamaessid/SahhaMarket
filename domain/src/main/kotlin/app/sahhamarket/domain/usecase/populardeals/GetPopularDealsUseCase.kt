package app.sahhamarket.domain.usecase.populardeals

import app.sahhamarket.domain.model.Product
import app.sahhamarket.domain.repository.PopularDealsRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPopularDealsUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val popularDealsRepository: PopularDealsRepository,
) {

    operator fun invoke(): Flow<Result<List<Product>>> {
        return popularDealsRepository.getPopularDeals().flowOn(ioDispatcher)
    }
}