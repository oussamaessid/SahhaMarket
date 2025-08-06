package app.sahhamarket.domain.repository


import app.sahhamarket.domain.model.OtpResponse
import app.sahhamarket.domain.model.VerifyOtpResponse

interface AuthRepository {

    suspend fun requestOtp(phoneNumber: String): Result<OtpResponse>

    suspend fun verifyOtp(phoneNumber: String, otp: String): Result<VerifyOtpResponse>
}