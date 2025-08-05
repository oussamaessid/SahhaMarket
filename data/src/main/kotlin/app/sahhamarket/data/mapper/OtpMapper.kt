@file:Suppress("UNREACHABLE_CODE")

package app.sahhamarket.data.mapper


import app.sahhamarket.data.source.remote.model.OtpResponse as RemoteOtpResponse
import app.sahhamarket.domain.model.OtpResponse as DomainOtpResponse

fun RemoteOtpResponse.toDomain(): DomainOtpResponse {
    return DomainOtpResponse(
        // Fill in your real fields
        message = this.message
    )


}

fun app.sahhamarket.data.source.remote.model.VerifyOtpResponse.toDomain(): app.sahhamarket.domain.model.VerifyOtpResponse {
    return app.sahhamarket.domain.model.VerifyOtpResponse(
        access_token = this.access_token,
        id = this.id,
        phone = this.phone,
        purchaseType = this.purchaseType
    )
}