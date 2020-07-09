package dev.tobypinfold.authenticationservice.core.models.user

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

final case class User(userId: String, username: String, password: String)
object User extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val format: RootJsonFormat[User] = jsonFormat3(User.apply)
}

final case class UserProtected(userId: String, username: String)
object UserProtected extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val format: RootJsonFormat[UserProtected] = jsonFormat2(UserProtected.apply)
}

final case class UserPass(username: String, password: String)
object UserPass extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val format: RootJsonFormat[UserPass] = jsonFormat2(UserPass.apply)
}