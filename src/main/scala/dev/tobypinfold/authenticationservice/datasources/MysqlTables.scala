package dev.tobypinfold.authenticationservice.datasources

import slick.jdbc.MySQLProfile.api._
import slick.lifted.{ForeignKeyQuery, ProvenShape}

class UsersTable(tag: Tag) extends Table[(Long, String, String)](tag, "Users") {
  def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def username: Rep[String] = column[String]("username")
  def password: Rep[String] = column[String]("password")
  def * : ProvenShape[(Long, String, String)] = (id, username, password)
}

object UsersTable {
  val table = TableQuery[UsersTable]
}

class TokensTable(tag: Tag) extends Table[(String, Long)](tag, "Tokens") {
  def token: Rep[String] = column[String]("token", O.PrimaryKey)
  def userId: Rep[Long] = column[Long]("user_id")
  def user: ForeignKeyQuery[UsersTable, (Long, String, String)] = foreignKey("user_fk", userId, UsersTable.table)(_.id)
  def * : ProvenShape[(String, Long)] = (token, userId)
}

object TokensTable {
  val table = TableQuery[TokensTable]
}
