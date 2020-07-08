package dev.tobypinfold.authenticationservice

import akka.actor.ActorSystem
import akka.http.scaladsl.Http

import scala.concurrent.{ExecutionContextExecutor, Future}
import akka.http.scaladsl.server.Directives._
import dev.tobypinfold.authenticationservice.Configuration
import dev.tobypinfold.authenticationservice.repository.DB
import dev.tobypinfold.authenticationservice.routes.{AuthenticationRoute, AuthorizationRoute}

import scala.io.StdIn

object Authenticator {

  implicit val system: ActorSystem = ActorSystem("authentication-service")
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  private def startServer(): Future[Http.ServerBinding] = {

    val db = DB.connection
    DB.setup(db)

    val routes = AuthenticationRoute.routes ~ AuthorizationRoute.routes


    Http().bindAndHandle(routes, Configuration.config.http.address, Configuration.config.http.port)
  }

  def main(args: Array[String]) {

    val address = s"http://${Configuration.config.http.address}:${Configuration.config.http.port}/"
    val bindingFuture: Future[Http.ServerBinding] = startServer()

    println(s"Server online at $address\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
