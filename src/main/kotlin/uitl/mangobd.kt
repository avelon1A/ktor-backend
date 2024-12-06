package uitl

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import data.CodingQuestion
import kotlinx.coroutines.flow.firstOrNull

object MongoDb {
    private val client: MongoClient = MongoClient.create("mongodb://localhost:27017")
    private val database: MongoDatabase = client.getDatabase("codingSite")

    val codingQuestions: MongoCollection<CodingQuestion> = database.getCollection("codingQuestions")

    suspend fun testDatabase() {
        // Create a new CodingQuestion document
        val question = CodingQuestion(
            title = "Sample Coding Question",
            description = "This is a sample coding question description.",
            difficulty = "Medium",
            tags = listOf("arrays", "dynamic programming")
        )

        // Insert the document into the collection
        codingQuestions.insertOne(question)

        // Retrieve the inserted document using a filter (optional)
        val retrievedQuestion = codingQuestions.find().firstOrNull()

        if (retrievedQuestion != null) {
            println("Inserted and retrieved: $retrievedQuestion")
        } else {
            println("No document found in the collection!")
        }
    }
}
