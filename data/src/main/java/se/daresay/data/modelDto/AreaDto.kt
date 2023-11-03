package se.daresay.data.modelDto

import se.daresay.data.base.ToDomain

data class AreaDto(
    val response: List<String>
): ToDomain<List<String>>{
    override fun toDomain(): List<String> = this.response

}