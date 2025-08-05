package app.sahhamarket.data.source.remote

import app.sahhamarket.data.source.remote.model.OtpResponse
import app.sahhamarket.data.source.remote.model.VerifyOtpRequest
import app.sahhamarket.data.source.remote.model.VerifyOtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SahhaMarketApi {

    @POST("auth/request-otp")
    suspend fun requestOtp(@Body body: app.sahhamarket.domain.model.OtpRequest): Response<OtpResponse>

    @POST("auth/verify-otp")
    suspend fun verifyOtp(@Body body: VerifyOtpRequest): Response<VerifyOtpResponse>
}