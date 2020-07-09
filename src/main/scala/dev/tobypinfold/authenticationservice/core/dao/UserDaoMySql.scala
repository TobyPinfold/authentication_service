package dev.tobypinfold.authenticationservice.core.dao
import dev.tobypinfold.authenticationservice.core.dao.traits.UserDao
import dev.tobypinfold.authenticationservice.core.models.user.{User, UserPass, UserProtected}
import dev.tobypinfold.authenticationservice.core.models.utils.JwtToken
import dev.tobypinfold.authenticationservice.core.repository.MysqlUsersRepository

import scala.concurrent.Future

class UserDaoMySql(repository: MysqlUsersRepository) extends UserDao{

  override def createUser(): Future[User] = ???

  override def deleteUser(user: User): Future[Boolean] = ???

  override def authenticateUser(user: UserPass): Future[UserProtected] = Future.successful(UserProtected("12", user.username))
}
