package es.guillermoorellana.keynotedex.backend.user.login

import es.guillermoorellana.keynotedex.backend.*
import es.guillermoorellana.keynotedex.backend.dao.UserStorage
import es.guillermoorellana.keynotedex.backend.error.ErrorResponse
import es.guillermoorellana.keynotedex.backend.user.model.toPublic
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.*

fun Route.login(dao: UserStorage) {
    post<LoginPage> {
        val params = call.receiveParameters()
        val userId = params["userId"] ?: ""
        val password = params["password"] ?: ""

        val login = when {
            userId.length < 4 -> null
            password.length < 6 -> null
            !userNameValid(userId) -> null
            else -> {
                dao.user(userId, hash(password))
            }
        }

        when (login) {
            null -> call.respond(
                HttpStatusCode.Unauthorized,
                ErrorResponse(message = "Invalid username or password")
            )
            else -> {
                call.sessions.set(Session(login.userId))
                call.respond(LoginResponse(login.toPublic()))
            }
        }
    }
}
