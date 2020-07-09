package dev.tobypinfold.authenticationservice.core.models.utils

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class JwtToken(token: String)
object JwtToken extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val jwtTokenFormat: RootJsonFormat[JwtToken] = jsonFormat1(JwtToken.apply)
}