package sttp.example

import org.openapitools.client.api.PetApi
import org.openapitools.client.model.Pet

object Main {
  def main(args: Array[String]): Unit = {
    PetApi()(???).addPet(Pet(Some(123), photoUrls = Seq.empty, name = "reks"))
  }
}
