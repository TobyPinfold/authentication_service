package dev.tobypinfold.authenticationservice.dao

import dev.tobypinfold.authenticationservice.models.user.User
import dev.tobypinfold.authenticationservice.models.utils.JwtToken

import scala.concurrent.Future

trait UserDao {
  def createUser() : Future[User]

  def createToken(user: User) : Future[JwtToken]

  def authenticateToken(token: JwtToken) : Future[User]

  def deleteToken(user: User) : Future[Boolean]

  def deleteUser(user: User) : Future[Boolean]
}
