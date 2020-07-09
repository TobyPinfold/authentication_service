package dev.tobypinfold.authenticationservice.core.services

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import dev.tobypinfold.authenticationservice.core.dao.traits.{TokensDao, UserDao}
import dev.tobypinfold.authenticationservice.core.models.user._
import dev.tobypinfold.authenticationservice.core.models.utils.JwtToken
import org.mindrot.jbcrypt.BCrypt

import scala.concurrent.{ExecutionContext, Future}

class AuthenticationService(userDao: UserDao, tokensDao: TokensDao)(implicit ec: ExecutionContext) {

  def createUser(userPass: UserPass): Future[UserProtected] = {
    val passwordHash = BCrypt.hashpw(userPass.password, BCrypt.gensalt())
    userDao.createUser(userPass.copy(password = passwordHash))
      .map(id => UserProtected(id, userPass.username))
  }

  def authenticate(userPass: UserPass): Future[Option[JwtToken]] =
    for {
      userDb <- userDao.getUser(userPass)
      _ <- Future.successful(verifyPassword(userPass, userDb))
      token <- tokensDao.createToken(userDb.userId)
    } yield Some(token)

  private def verifyPassword(userPass: UserPass, dbUser: User): Option[UserProtected] = {
    if(BCrypt.checkpw(userPass.password, dbUser.password)) {
      Some(UserProtected(dbUser.userId, dbUser.username))
    } else None
  }
}
