package dev.tobypinfold.authenticationservice.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, headerValueByName, path, post}
import akka.http.scaladsl.server.Route
import dev.tobypinfold.authenticationservice.models.utils.JwtToken
import dev.tobypinfold.authenticationservice.models.user.UserJsonSupport._
import dev.tobypinfold.authenticationservice.services.AuthenticationService
import spray.json._

object AuthenticationRoute {
  val routes: Route =
    path("authenticate") {
      post {
        headerValueByName("X-JWT")  { token =>
          AuthenticationService.authenticate(JwtToken(token))
            .fold(throw new IllegalArgumentException)(userToken => complete(
              HttpEntity(ContentTypes.`application/json`, userToken.toJson.toString())
            ))
        }
      }
    }
}
