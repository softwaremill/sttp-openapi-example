package sttp.example

import org.openapitools.client.api.StoreApi
import org.openapitools.client.core.{ApiKeyValue, SttpSerializer}
import sttp.client.HttpURLConnectionBackend

object Main {
  def main(args: Array[String]): Unit = {
    implicit val serializer: SttpSerializer = new SttpSerializer()
    implicit val apiKeyValue = ApiKeyValue("")
    implicit val backend = HttpURLConnectionBackend()
    println(StoreApi().getInventory().send())
  }
}
