package se.daresay.car_service.screen.details

import java.lang.Exception

sealed class State<T> {
    class Idle<T>: State<T>()
    data class Loading<T>(val msg: String): State<T>()
    data class Data<T>(val data: T): State<T>()
    data class Error<T>(val exception: Exception): State<T>()
}
