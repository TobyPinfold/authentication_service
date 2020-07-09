package dev.tobypinfold.authenticationservice.core.services

import dev.tobypinfold.authenticationservice.core.dao.traits.{TokensDao, UserDao}
import dev.tobypinfold.authenticationservice.core.models.user.{User, UserPass}
import dev.tobypinfold.authenticationservice.core.models.utils.JwtToken

import scala.concurrent.{ExecutionContext, Future}

class AuthenticationService(userDao: UserDao, tokensDao: TokensDao)(implicit ec: ExecutionContext) {

  def authenticate(userPass: UserPass): Future[Option[JwtToken]] =
    for {
      user <- userDao.authenticateUser(userPass)
      token <- tokensDao.createToken(user.userId)
    } yield Some(token)
}
