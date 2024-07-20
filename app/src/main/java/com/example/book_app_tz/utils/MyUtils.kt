package com.example.book_app_tz.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import kotlin.Result.Companion.failure
import kotlin.experimental.ExperimentalTypeInference

fun <T> Response<T>.toResult(): Result<T> {
    return if (isSuccessful) {
        body()?.let {
            Result.success(it)
        } ?: failure(Exception("Response body is null!"))
    } else {
        failure(Exception("Error occurred: ${errorBody()?.string() ?: "Unknown error"}"))
    }
}

suspend fun <T> emitWith(result: Result<T>, collector: FlowCollector<Result<T>>) {
    collector.emit(result)
}


@OptIn(ExperimentalTypeInference::class)
fun <T> safetyFlow(@BuilderInference block: suspend FlowCollector<Result<T>>.() -> Unit): Flow<Result<T>> =
    flow {
        block()
    }
        .flowOn(Dispatchers.IO)
        .catch { exception ->
            emit(failure(exception))
        }