package dev.tobypinfold.authenticationservice

import pureconfig.ConfigSource
import pureconfig.generic.auto._

package object Configuration {

 final case class ServerConfiguration(http: HttpConfiguration)
 final case class HttpConfiguration(address: String, port:Int)

 val config: ServerConfiguration =  ConfigSource.default.loadOrThrow[ServerConfiguration]
}
