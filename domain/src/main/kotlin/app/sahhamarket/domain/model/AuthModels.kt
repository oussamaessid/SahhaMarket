package app.sahhamarket.domain.model

data class OtpRequest(
    val phone: String
)

data class OtpResponse(
    val message: String
)

data class VerifyOtpRequest(
    val phone: String,
    val code: String,
    val inviteCode: String = "",
    val purchaseType : String = "retail"
)

data class VerifyOtpResponse(
    val id : Int,
    val phone : String,
    val purchaseType : String,
    val access_token: String
)
data class ApiErrorResponse(
    val message: List<String>?
)
data class InviteLink (
    val inviteLink : String,
)
