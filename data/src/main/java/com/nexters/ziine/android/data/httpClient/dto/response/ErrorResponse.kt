package com.nexters.ziine.android.data.httpClient.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
)
