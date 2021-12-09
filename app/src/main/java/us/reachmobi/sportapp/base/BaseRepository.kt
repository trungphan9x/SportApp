package us.reachmobi.sportapp.base

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import us.reachmobi.sportapp.util.Resource
import us.reachmobi.sportapp.util.ResponseHandler

abstract class BaseRepository : KoinComponent {
    private val responseHandler : ResponseHandler by inject()

    suspend fun <T> safeApi(callApi: suspend () -> T): Resource<T> {
        try {
            callApi.invoke().let { response ->
                return responseHandler.handleSuccess(response)
            }
        } catch (ex: Exception) {
            return responseHandler.handleException(ex)
        }
    }
}