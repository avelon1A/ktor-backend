package model.data

import kotlinx.serialization.Serializable

@Serializable
data class RunJavaResponse(
    val status: String,
    val result: String
)
