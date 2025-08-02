package app.sahhamarket.domain.repository

import app.sahhamarket.domain.model.User
import app.sahhamarket.domain.model.UserStats

interface ProfileRepository {
        suspend fun getUserProfile() : Result<User>

}