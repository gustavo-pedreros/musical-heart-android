package labs.gas.network

import okhttp3.Interceptor

interface ApiConfig {
    val baseUrl: String
    val timeout: Long
    val isProd: Boolean
    fun interceptors(): List<Interceptor> = emptyList()
}
