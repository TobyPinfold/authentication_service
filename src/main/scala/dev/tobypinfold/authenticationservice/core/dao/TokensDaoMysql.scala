package dev.tobypinfold.authenticationservice.core.dao

import dev.tobypinfold.authenticationservice.core.dao.traits.TokensDao
import dev.tobypinfold.authenticationservice.core.models.utils.JwtToken
import dev.tobypinfold.authenticationservice.core.repository.MysqlTokensRepository

import scala.concurrent.Future

class TokensDaoMysql(tokensRepository: MysqlTokensRepository) extends TokensDao {
  override def createToken(userId: Long): Future[JwtToken] = {
    val token = JwtToken(java.util.UUID.randomUUID.toString)
    tokensRepository.insert(token, userId)
    Future.successful(JwtToken("1231241212123"))
  }
}
