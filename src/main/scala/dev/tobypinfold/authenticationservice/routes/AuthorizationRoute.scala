package dev.tobypinfold.authenticationservice.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, path, post}
import akka.http.scaladsl.server.Route

object AuthorizationRoute {
  val routes: Route =
    path("authorize") {
      post {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
      }
    }
}
