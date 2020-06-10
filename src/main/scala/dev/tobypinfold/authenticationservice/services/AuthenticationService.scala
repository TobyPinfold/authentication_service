package dev.tobypinfold.authenticationservice.services

import dev.tobypinfold.authenticationservice.models.user.User
import dev.tobypinfold.authenticationservice.models.utils.JwtToken


object AuthenticationService {

  def authenticate(token: JwtToken): Option[User] =
      Option(User("gg"))
}
