import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import model.data.StatusResponse
import service.MainService

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json { prettyPrint = true})
    }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, cause.localizedMessage)
        }
    }
    val mainService = MainService()
    routing {
        coverageRouting(mainService)
    }
}

fun Route.coverageRouting(mainService: MainService) {

    get("/") {
        val response = StatusResponse(status = "ok", method = "GET")
        call.respond(HttpStatusCode.OK, response)
    }


    get("/categories") {
        val categories = mainService.getCategories()
        call.respond(HttpStatusCode.OK, categories)
    }
    post("/runjava") {
        val javaCode = call.receiveText()

        val result = runJavaCode(javaCode)

        call.respond(HttpStatusCode.OK, result)
    }
}



