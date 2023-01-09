package labs.gas.network

import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


fun <T> Response<T>.processRemoteResponse(): Either<RemoteError, Response<T>> {
    return when {
        this.isSuccessful -> Either.Success(this)
        else -> throw HttpException(this)
    }
}

fun Throwable.processRemoteException(): RemoteError {
    return when (this) {
        is HttpException -> response().processHttpException()
        is IOException -> ConnectionError
        else -> UnknownError
    }
}

private fun Response<*>?.processHttpException(): RemoteError {
    return when {
        this == null -> UnknownError
        this.isAuthException -> ApiError("Authentication Error")
        this.hasError -> errorBody()?.string()?.parseApiError() ?: UnexpectedNullBodyError
        else -> UnknownError
    }
}

private fun String.parseApiError(): RemoteError {
    return try {
        val errorResponse = Gson().fromJson(this, RemoteErrorResponse::class.java)
        ApiError(message = errorResponse.status!!)
    } catch (exception: Exception) {
        UnknownError
    }
}

private val <T> Response<T>.hasError: Boolean
    get() = errorBody()?.string()?.isNotBlank() ?: false

private val <T> Response<T>.isAuthException: Boolean
    get() = raw().code == 401
