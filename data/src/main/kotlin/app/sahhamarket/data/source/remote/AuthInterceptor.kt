package app.sahhamarket.data.source.remote

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.Interceptor
import okhttp3.Response
class AuthInterceptor(private val context: Context) : Interceptor {
    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SecureStorage.getToken(context)
        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }

        val response = chain.proceed(request)

        if (response.code == 401) {
            UnauthorizedHandler.isUnauthorized.value = true
            SecureStorage.clearToken(context)
        }
        return response
    }
}
