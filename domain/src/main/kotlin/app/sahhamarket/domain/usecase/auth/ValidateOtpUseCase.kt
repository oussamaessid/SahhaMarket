package app.sahhamarket.domain.usecase.auth

import app.sahhamarket.domain.repository.AuthRepository
import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ValidateOtpUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(phoneNumber: String, otp: String): Result<String> = withContext(ioDispatcher) {
        authRepository.verifyOtp(phoneNumber, otp).map {
            it.access_token
        }

    }
}
