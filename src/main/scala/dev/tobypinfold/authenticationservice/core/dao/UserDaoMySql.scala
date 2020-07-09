package dev.tobypinfold.authenticationservice.core.dao
import dev.tobypinfold.authenticationservice.core.dao.traits.UserDao
import dev.tobypinfold.authenticationservice.core.models.user.{User, UserPass, UserProtected}
import dev.tobypinfold.authenticationservice.core.repository.MysqlUsersRepository
import org.mindrot.jbcrypt.BCrypt

import scala.concurrent.{ExecutionContext, Future}

class UserDaoMySql(userRepository: MysqlUsersRepository)(implicit ec: ExecutionContext) extends UserDao {

  override def createUser(user: UserPass): Future[Long] = {
    userRepository.insert(user)
  }

  override def deleteUser(user: User): Future[Boolean] = ???

  override def getUser(user: UserPass): Future[User] = {
   userRepository.get(user.username)
  }
}
