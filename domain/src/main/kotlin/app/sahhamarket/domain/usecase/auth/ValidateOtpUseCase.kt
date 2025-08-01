package app.sahhamarket.domain.usecase.auth

import app.sahhamarket.domain.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ValidateOtpUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(otp: String): Result<Boolean> = withContext(ioDispatcher) {
        Result.success(otp.length == 6 && otp.all { it.isDigit() })
    }
}