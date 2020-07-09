package dev.tobypinfold.authenticationservice.core.repository
import dev.tobypinfold.authenticationservice.core.models.user.{User, UserPass, UserProtected}
import dev.tobypinfold.authenticationservice.datasources.UsersTable
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class MysqlUsersRepository(db: Database)(implicit ec: ExecutionContext) {

  val users = UsersTable.table

  def insert(user: UserPass): Future[Long] = {
    db.run {
      users returning users.map(_.id) += (0, user.username, user.password)
    }
  }

  def get(username: String): Future[User] = {
    db.run {
      users.filter(_.username === username).distinct.result.map[User](x => {
        val y = x.head
        User(y._1, y._2, y._3)
      })
    }
  }
}
