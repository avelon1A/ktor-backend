package data
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class CodingQuestion(
    @BsonId val id: String? = null,
    val title: String,
    val description: String,
    val difficulty: String,
    val tags: List<String>
)
