package se.daresay.data.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import se.daresay.domain.base.Response

/**
 * ToDomain : raw response wrapped in ToDomain
 * Domain : domain model
 * T : raw response
 */
fun <Domain , T : ToDomain<Domain>> apiCall(loadingMessage: String, req : suspend () -> T  ) : Flow<Response<Domain>> = flow{
    emit(Response.Loading(loadingMessage))
    try {
        emit(Response.Data(req.invoke().toDomain()))
    }catch (e : Exception){
        emit(Response.Error(e))
    }
}

