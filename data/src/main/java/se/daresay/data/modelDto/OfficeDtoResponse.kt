package se.daresay.data.modelDto

import se.daresay.data.base.ToDomain
import se.daresay.domain.model.Office

data class OfficeDtoResponse(
    val response: List<OfficeDto>
): ToDomain<List<Office>>{
    override fun toDomain(): List<Office> =
        response.map {
            it.toDomain()
        }

}

data class OfficeDto(
    val name: String,
    val phoneNumber: String?,
    val address: String
): ToDomain<Office>{
    override fun toDomain(): Office =
        Office(
            name, phoneNumber, address
        )

}