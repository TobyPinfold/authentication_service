package dev.tobypinfold.authenticationservice.core.dao.traits

import dev.tobypinfold.authenticationservice.core.models.utils.JwtToken

import scala.concurrent.Future

trait TokensDao {

  def createToken(userId: String): Future[JwtToken]

}
