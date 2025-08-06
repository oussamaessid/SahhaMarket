package app.sahhamarket.domain.usecase.auth

import app.sahhamarket.domain.repository.AuthRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RequestOtpUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(phoneNumber: String): Result<Boolean> = withContext(ioDispatcher) {
        authRepository.requestOtp(phoneNumber)
            .map { true } // Also return true if successful
    }
}
