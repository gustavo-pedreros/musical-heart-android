package labs.gas.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend inline fun <T> apiCall(
    dispatcher: CoroutineDispatcher,
    crossinline retrofitCall: suspend () -> Response<T>
): Either<RemoteError, Response<T>> {
    return withContext(dispatcher) {
        try {
            retrofitCall().processRemoteResponse()
        } catch (e: Exception) {
            return@withContext Either.Error(e.processRemoteException())
        }
    }
}
