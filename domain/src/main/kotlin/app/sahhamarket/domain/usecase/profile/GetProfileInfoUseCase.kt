package app.sahhamarket.domain.usecase.profile

import app.sahhamarket.domain.model.SubCategory
import app.sahhamarket.domain.model.User
import app.sahhamarket.domain.model.UserStats
import app.sahhamarket.domain.repository.ProfileRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProfileInfoUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ) {
    suspend operator fun invoke(): Result<User> = withContext(ioDispatcher) {
//        return repository.getUserProfile()
        Result.success(User("azmi hwamed","" , UserStats(20 ,20 ,20)))
    }
}
