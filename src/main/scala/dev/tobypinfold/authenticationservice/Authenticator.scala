package dev.tobypinfold.authenticationservice

import akka.actor.ActorSystem
import akka.http.scaladsl.Http

import scala.concurrent.{ExecutionContextExecutor, Future}
import akka.http.scaladsl.server.Directives._
import dev.tobypinfold.authenticationservice.core.dao.{TokensDaoMysql, UserDaoMySql}
import dev.tobypinfold.authenticationservice.core.repository.{MysqlTokensRepository, MysqlUsersRepository}
import dev.tobypinfold.authenticationservice.core.routes.{AuthenticationRoute, AuthorizationRoute}
import dev.tobypinfold.authenticationservice.core.services.{AuthenticationService, AuthorizationService}
import dev.tobypinfold.authenticationservice.datasources.MysqlDB
import dev.tobypinfold.authenticationservice.Configuration

import scala.io.StdIn

object Authenticator {

  implicit val system: ActorSystem = ActorSystem("authentication-service")
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val db = MysqlDB.connection
  MysqlDB.setup(db)

  val mysqlTokensRepository = new MysqlTokensRepository(db)
  val mysqlUsersRepository = new MysqlUsersRepository(db)

  val tokensDaoMysql = new TokensDaoMysql(mysqlTokensRepository)
  val userDaoMySql = new UserDaoMySql(mysqlUsersRepository)

  val authenticationService: AuthenticationService = new AuthenticationService(userDaoMySql, tokensDaoMysql)
  val authorizationService: AuthorizationService = new AuthorizationService(tokensDaoMysql)


  private def startServer(): Future[Http.ServerBinding] = {
    val authenticationRoute = new AuthenticationRoute(authenticationService)
    val authorizationRoute = new AuthorizationRoute()

    val routes = authenticationRoute.routes ~ authorizationRoute.routes

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
