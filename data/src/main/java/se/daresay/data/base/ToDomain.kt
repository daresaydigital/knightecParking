package se.daresay.data.base

interface ToDomain<out T> {
    fun toDomain(): T
}