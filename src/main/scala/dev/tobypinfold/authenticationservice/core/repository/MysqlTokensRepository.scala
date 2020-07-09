package dev.tobypinfold.authenticationservice.core.repository

import dev.tobypinfold.authenticationservice.core.models.utils.JwtToken
import dev.tobypinfold.authenticationservice.datasources.UsersTable
import dev.tobypinfold.authenticationservice.datasources.TokensTable
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

class MysqlTokensRepository(db: Database) {

  def insert(jwtToken: JwtToken, userId: Long): Future[Unit] =
    db.run(DBIO.seq(TokensTable.table.insertOrUpdate(jwtToken.token, userId)))

}
