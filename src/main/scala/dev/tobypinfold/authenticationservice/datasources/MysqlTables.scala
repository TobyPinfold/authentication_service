package dev.tobypinfold.authenticationservice.datasources

import slick.jdbc.MySQLProfile.api._
import slick.lifted.{ForeignKeyQuery, ProvenShape}

class Users(tag: Tag) extends Table[(Long, String, String)](tag, "Users") {
  def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def username: Rep[String] = column[String]("username")
  def password: Rep[String] = column[String]("password")
  def * : ProvenShape[(Long, String, String)] = (id, username, password)
}

object Users {
  val table = TableQuery[Users]
}

class Tokens(tag: Tag) extends Table[(String, Long)](tag, "Tokens") {
  def token: Rep[String] = column[String]("token", O.PrimaryKey)
  def userId: Rep[Long] = column[Long]("user_id")
  def user: ForeignKeyQuery[Users, (Long, String, String)] = foreignKey("user_fk", userId, Users.table)(_.id)
  def * : ProvenShape[(String, Long)] = (token, userId)
}

object Tokens {
  val table = TableQuery[Tokens]
}
