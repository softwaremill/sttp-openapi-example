package sttp.example

import org.openapitools.client.api.StoreApi
import org.openapitools.client.core.{ApiKeyValue, SttpSerializer}
import sttp.client3.HttpURLConnectionBackend

object Main {
  def main(args: Array[String]): Unit = {
    implicit val serializer: SttpSerializer = new SttpSerializer()
    implicit val apiKeyValue = ApiKeyValue("")
    val backend = HttpURLConnectionBackend()
    println(backend.send(StoreApi().getInventory()))
  }
}
