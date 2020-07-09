package dev.tobypinfold.authenticationservice.core.services

import dev.tobypinfold.authenticationservice.core.dao.traits.TokensDao
import dev.tobypinfold.authenticationservice.core.models.user.User
import dev.tobypinfold.authenticationservice.core.models.utils.JwtToken

class AuthorizationService(tokensDao: TokensDao) {

  def authorize(token: JwtToken): Option[User] = ???
}
