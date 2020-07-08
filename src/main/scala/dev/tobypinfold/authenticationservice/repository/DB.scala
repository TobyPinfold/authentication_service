package dev.tobypinfold.authenticationservice.repository
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

object DB {
  val connection: Database = Database.forConfig("db")

  def setup (database: Database): Future[Unit] = database.run(
    DBIO.seq((Users.table.schema ++ Tokens.table.schema).create)
  )

  def close(database: Database): Unit = database.close()
}
