package app.sahhamarket.domain.model


data class User(
    val name: String,
    val avatarUrl: String? ,
    val stats: UserStats
)
