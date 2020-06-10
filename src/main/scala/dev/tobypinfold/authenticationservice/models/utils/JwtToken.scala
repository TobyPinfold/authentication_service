package dev.tobypinfold.authenticationservice.models.utils

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class JwtToken(token: String)
object JwtTokenJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val jwtTokenFormat: RootJsonFormat[JwtToken] = jsonFormat1(JwtToken)
}