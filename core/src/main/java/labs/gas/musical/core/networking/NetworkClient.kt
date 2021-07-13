package labs.gas.musical.core.networking

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import labs.gas.musical.core.BuildConfig
import labs.gas.musical.core.networking.interceptors.Constants.HttpRequest.HTTP_REQUEST_TIMEOUT_SECONDS
import labs.gas.musical.core.networking.interceptors.DefaultInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkClient(endpointBaseUrl: String, private val sslContract: SSLContract? = null) {
    private val defaultInterceptor: Interceptor = DefaultInterceptor()

    private val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        .apply {
            addInterceptor(defaultInterceptor)
            readTimeout(HTTP_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            connectTimeout(HTTP_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        }

    private fun getClient(): OkHttpClient {
        sslContract?.sslServerSocketFactory()?.let {
            clientBuilder.sslSocketFactory(it.first, it.second)
        }
        clientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(getLoggerLevel()))
        return clientBuilder.build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(endpointBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(getClient())
        .build()

    private fun getLoggerLevel() = when (BuildConfig.DEBUG) {
        true -> HttpLoggingInterceptor.Level.BODY
        false -> HttpLoggingInterceptor.Level.NONE
    }

    fun <T> getClient(api: Class<T>): T = retrofit.create(api)
}
