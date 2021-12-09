package us.reachmobi.sportapp.util

import com.google.gson.Gson
import retrofit2.HttpException
import us.reachmobi.sportapp.data.remote.response.ErrorResponseBody
import java.lang.Exception
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

open class ResponseHandler {
    fun <T> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T> handleException(e: Exception): Resource<T> {

        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), null)
//            is HttpException -> {
//                val errorResponse = Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponseBody::class.java)
//                Resource.error(getErrorMessage(e.code(), errorResponse.message), null)
//            }
            is SocketTimeoutException -> Resource.error(getErrorMessage(ErrorCodes.SocketTimeOut.code), null)
//            is IOException -> BaseApiResult.error("No internet connection", null)
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int, message: String? = null): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> {
                "Error code: $code\nError message: $message"
            }
        }
    }
}