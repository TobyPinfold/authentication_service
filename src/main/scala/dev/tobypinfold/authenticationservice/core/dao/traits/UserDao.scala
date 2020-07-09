package dev.tobypinfold.authenticationservice.core.dao.traits

import dev.tobypinfold.authenticationservice.core.models.user.{User, UserPass, UserProtected}
import dev.tobypinfold.authenticationservice.core.models.utils.JwtToken
import dev.tobypinfold.authenticationservice.core.repository.MysqlUsersRepository

import scala.concurrent.Future

trait UserDao {
  def createUser(user: UserPass) : Future[Long]

  def getUser(user: UserPass) : Future[User]

  def deleteUser(user: User) : Future[Boolean]
}
