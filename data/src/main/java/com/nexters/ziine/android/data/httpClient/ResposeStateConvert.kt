package com.nexters.ziine.android.data.httpClient

import com.nexters.ziine.android.data.httpClient.dto.response.ErrorResponse
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException

private const val UNKNOWN_HOST_EXCEPTION_LOG_MESSAGE = "네트워크 연결상태 혹은 잘못된 도메인 요청입니다."
private const val SOCKET_TIME_OUT_EXCEPTION_LOG_MESSAGE = "타임아웃 에러가 발생하였습니다."
private const val CONNECT_EXCEPTION_LOG_MESSAGE = "서버가 다운되었거나 방화벽 관련 문제가 발생하였습니다."
private const val UNKNOWN_IO_EXCEPTION_LOG_MESSAGE = "특정되지 않은 IO Exception이 발생하였습니다."

suspend fun <T : Any> convertClassifiedResult(apiBlock: suspend () -> T): Result<T> {
    return runCatching { apiBlock() }
        .fold(
            onSuccess = { Result.success(it) },
            onFailure = { Result.failure(classifyException(it)) },
        )
}

private fun classifyException(throwable: Throwable): Exception {
    return when (throwable) {
        is IOException -> throwable.apply { defaultIoExceptionHandler(throwable) }
        is HttpException -> httpExceptionProcessor(throwable)
        else -> UnknownServiceException()
    }
}

/**
 * 추후 공통처리 필요시 만들어놓은 함수
 * IOException 하위의 여러 분기되는 Exception을 처리
 * 기획 추가사항에 따른 로직 추가가 필요
 */
private fun defaultIoExceptionHandler(exception: IOException) {
    return when (exception) {
        is UnknownHostException -> defaultUnknownHostExceptionHandler(exception)
        is SocketTimeoutException -> defaultSocketTimeoutExceptionHandler(exception)
        is ConnectException -> defaultConnectExceptionHandler(exception)
        else -> restIOExceptionHandler(exception)
    }
}

private fun defaultUnknownHostExceptionHandler(unknownHostException: UnknownHostException) {
    // 인터넷 연결 끊김 혹은 잘못된 도메인 주소에 요청
    Timber.e(unknownHostException, UNKNOWN_HOST_EXCEPTION_LOG_MESSAGE)
}

private fun defaultSocketTimeoutExceptionHandler(socketTimeoutException: SocketTimeoutException) {
    // 타임아웃 발생
    Timber.e(socketTimeoutException, SOCKET_TIME_OUT_EXCEPTION_LOG_MESSAGE)
}

private fun defaultConnectExceptionHandler(connectException: ConnectException) {
    // 서버가 다운되었거나 방화벽 문제
    Timber.e(connectException, CONNECT_EXCEPTION_LOG_MESSAGE)
}

private fun restIOExceptionHandler(ioException: IOException) {
    // 특정되지않은 IO 에러
    Timber.e(ioException, UNKNOWN_IO_EXCEPTION_LOG_MESSAGE)
}

/**Ziine 서버통신 처리 상황
 * 에러시 응답내 HttpStatus code가 아닌 커스텀 코드 포함
 * 관련처리 커스텀 코드를 이용
 */
private fun httpExceptionProcessor(exception: HttpException): Exception {
    val rawErrorString = exception.response()?.errorBody()?.string() ?: ""
    val errorBody: ErrorResponse = Json.decodeFromString(rawErrorString)
    val cause = exception.cause

    return when (errorBody.code) {
//        400 -> BadRequestException(message, cause)
//        401 -> UnAuthorizedException(message, cause)
//        403 -> ForbiddenException(message, cause) 예시들 상황에 맞는 도메인 커스텀 Exception 정의
        else -> exception
    }
}
