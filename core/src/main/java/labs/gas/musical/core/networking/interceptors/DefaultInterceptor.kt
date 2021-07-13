package labs.gas.musical.core.networking.interceptors

import labs.gas.musical.core.networking.interceptors.Constants.Header.ACCEPT
import labs.gas.musical.core.networking.interceptors.Constants.Header.ACCEPT_LANGUAGE
import labs.gas.musical.core.networking.interceptors.Constants.Header.APPLICATION_JSON
import labs.gas.musical.core.networking.interceptors.Constants.Header.APPLICATION_LANGUAGE
import labs.gas.musical.core.networking.interceptors.Constants.Header.CONTENT_TYPE
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Request.Builder
import okhttp3.Response

class DefaultInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val request: Builder = original.newBuilder().apply {
            header(CONTENT_TYPE, APPLICATION_JSON)
            header(ACCEPT, APPLICATION_JSON)
            header(ACCEPT_LANGUAGE, APPLICATION_LANGUAGE)
            method(original.method, original.body)
        }
        return chain.proceed(request.build())
    }
}
