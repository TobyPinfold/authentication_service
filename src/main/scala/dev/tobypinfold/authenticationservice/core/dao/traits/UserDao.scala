package dev.tobypinfold.authenticationservice.core.dao.traits

import dev.tobypinfold.authenticationservice.core.models.user.{User, UserPass, UserProtected}
import dev.tobypinfold.authenticationservice.core.models.utils.JwtToken
import dev.tobypinfold.authenticationservice.core.repository.MysqlUsersRepository

import scala.concurrent.Future

trait UserDao {
  def createUser() : Future[User]

  def authenticateUser(user: UserPass) : Future[UserProtected]

  def deleteUser(user: User) : Future[Boolean]
}
