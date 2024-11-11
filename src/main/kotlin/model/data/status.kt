package model.data

import kotlinx.serialization.Serializable

@Serializable
data class StatusResponse(val status: String, val method: String)
