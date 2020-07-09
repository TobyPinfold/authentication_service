package dev.tobypinfold.authenticationservice.datasources

import scala.concurrent.Future
import slick.jdbc.MySQLProfile.api._

object MysqlDB {
  val connection: Database = Database.forConfig("db")

  def setup (database: Database): Future[Unit] = database.run(
    DBIO.seq((UsersTable.table.schema ++ TokensTable.table.schema).create)
  )

  def close(database: Database): Unit = database.close()
}
