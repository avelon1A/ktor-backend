import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import model.data.DataList
import model.data.Problem
import model.data.ProblemDetails
import model.data.StatusResponse
import service.MainService

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {

    install(CORS) {
        anyHost()
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        allowCredentials = true
    }


    install(ContentNegotiation) {
        json(Json { prettyPrint = true; isLenient = true })

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
        val response = StatusResponse(text = "hello world")
        call.respond(HttpStatusCode.OK, response)
    }


    get("/categories") {
        val categories = mainService.getCategories()
        val dataResult = DataList(categories,3)
        call.respond(dataResult)
    }
    post("/runjava") {
        val javaCode = call.receiveText()

        val result = runJavaCode(javaCode)

        call.respond(HttpStatusCode.OK, result)
    }
    get("/problems") {
        val category = call.parameters["category"]
        var allProblems : List<Problem> = emptyList<Problem>()
        if(category == "array"){
            allProblems = mainService.getArrayProblems()
        }
        else
        {
            emptyList<Problem>()
        }
        call.respond(allProblems)
    }
    get("/problem/{id}") {
        val problemId = call.parameters["id"]?.toIntOrNull()
        val category = call.parameters["category"]
        var allProblems : ProblemDetails?
        if (problemId == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid problem id")
            return@get
        }
        if(category == "array"){
            allProblems = mainService.getProblem(problemId)
        }
        else{
            allProblems =   null
        }

        if (allProblems != null) {
            call.respond(HttpStatusCode.OK, allProblems!!)
        } else {
            call.respond(HttpStatusCode.NotFound, "Problem not found")
        }
    }



}



