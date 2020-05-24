package dev.tobypinfold.authenticationservice

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}

import scala.concurrent.{ExecutionContextExecutor, Future}
import akka.http.scaladsl.server.Directives._

import scala.io.StdIn

object AuthenticationService {

  implicit val system: ActorSystem = ActorSystem("authentication-service")
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  private def startServer(): Future[Http.ServerBinding] = {

    val routes =
      path("/authenticate") {
        post {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
        }
      }

    Http().bindAndHandle(routes, "localhost", 8080)
  }

  def main(args: Array[String]) {

    val bindingFuture: Future[Http.ServerBinding] = startServer()

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
