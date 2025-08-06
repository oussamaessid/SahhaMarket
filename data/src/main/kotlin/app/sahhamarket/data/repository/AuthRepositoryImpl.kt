package app.sahhamarket.data.repository

import app.sahhamarket.data.mapper.toDomain
import app.sahhamarket.data.source.remote.SahhaMarketApi
import app.sahhamarket.data.source.remote.model.VerifyOtpRequest
import app.sahhamarket.domain.model.OtpRequest
import app.sahhamarket.domain.model.OtpResponse
import app.sahhamarket.domain.model.VerifyOtpResponse
import app.sahhamarket.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: SahhaMarketApi
) : AuthRepository {
    
    override suspend fun requestOtp(phoneNumber: String): Result<OtpResponse> {
        return try {
            val response = api.requestOtp(OtpRequest(phone = phoneNumber))
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.success(body.toDomain())
            } else {
                val errorMsg = response.errorBody()?.string().orEmpty()
                Result.failure(Exception(errorMsg.ifEmpty { "Failed to request OTP" }))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun verifyOtp(phoneNumber: String, otp: String): Result<VerifyOtpResponse> {
        return try {
            val response = api.verifyOtp(VerifyOtpRequest(phone = phoneNumber, code = otp))
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.success(body.toDomain())
            } else {
                val errorMsg = response.errorBody()?.string().orEmpty()
                Result.failure(Exception(errorMsg.ifEmpty { "Failed to verify OTP" }))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
