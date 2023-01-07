package labs.gas.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient<T> constructor(
    private val apiConfig: ApiConfig,
    private val clazz: Class<T>,
    private val sslContract: SSLContract? = null
) : ApiClient<T> {
    override val endpoints: T by lazy {
        val okHttpClient: OkHttpClient = okhttpBuilder().build()
        val retrofit: Retrofit = retrofitBuilder().client(okHttpClient).build()
        retrofit.create(clazz)
    }

    private fun okhttpBuilder(): OkHttpClient.Builder = OkHttpClient.Builder().apply {
        readTimeout(apiConfig.timeout, TimeUnit.SECONDS)
        interceptors().ifEmpty {
            apiConfig.interceptors().forEach { interceptor -> addInterceptor(interceptor) }
        }
        sslContract?.sslServerSocketFactory()?.let {
            sslSocketFactory(it.first, it.second)
        }
        retryOnConnectionFailure(true)

    }

    private fun retrofitBuilder(): Retrofit.Builder = Retrofit
        .Builder().apply {
            baseUrl(apiConfig.baseUrl)
            addConverterFactory(GsonConverterFactory.create())
        }
}
