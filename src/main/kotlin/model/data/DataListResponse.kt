package model.data

import data.Category
import kotlinx.serialization.Serializable

@Serializable
data class DataList(
    val data: List<Category>,
    val total: Int,
)