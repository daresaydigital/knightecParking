package se.daresay.domain.base

import java.lang.Exception


sealed class Response<T> {
    data class Loading<T>(val msg: String): Response<T>()
    data class Data<T>(val data: T): Response<T>()
    data class Error<T>(val exception: Exception): Response<T>()
}