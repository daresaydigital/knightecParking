package se.daresay.domain.base

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<in Input, Output> {
    abstract operator fun invoke(input: Input): Flow<Response<Output>>
}