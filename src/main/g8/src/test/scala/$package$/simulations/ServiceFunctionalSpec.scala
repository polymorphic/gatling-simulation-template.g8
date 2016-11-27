package $package$.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.funspec.GatlingHttpFunSpec

class ServiceFunctionalSpec extends GatlingHttpFunSpec {
  override def baseURL: String = "http://localhost:8080"

  spec {
    http("ping request")
      .get("/ping")
      .check(bodyString.is("pong"))
  }

  spec {
    http("one")
      .get("/one")
  }

  spec {
    val body = """{"id":"e75254cf-f859-4df9-bb59-8af9cafc958a","timestamp":229503539741824}"""
    http("one")
      .post("/save")
      .body(StringBody(body))
      .check(bodyString.is(body))
  }
}
