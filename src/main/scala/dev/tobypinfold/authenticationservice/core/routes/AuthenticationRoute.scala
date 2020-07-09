package dev.tobypinfold.authenticationservice.core.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import dev.tobypinfold.authenticationservice.core.models.user.UserPass
import dev.tobypinfold.authenticationservice.core.services.AuthenticationService
import spray.json._

import scala.concurrent.ExecutionContext

class AuthenticationRoute(authenticationService: AuthenticationService)(implicit ec: ExecutionContext) {
  val routes: Route =
    path("authenticate") {
      post {
        entity(as[UserPass]) {
          userPass =>
            complete {
              authenticationService.authenticate(userPass).map(x => x.toJson.compactPrint)
            }
        }
      }
    }
}
