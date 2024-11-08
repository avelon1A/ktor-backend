package data

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val title: String,
    val problemsSolved: Int,
    val totalProblems: Int,
    val status: String
)