package model.data

import kotlinx.serialization.Serializable

@Serializable
data class JwtClaims(val username: String, val role: String)