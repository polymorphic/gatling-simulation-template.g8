package $package$.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class ServiceSimulation extends Simulation {

  val protobolBuilder = http
    .baseURL("http://localhost:8080")

  val scn = scenario("Local simulation")
    .exec(
      http("request one")
        .get("/one")
        .check(status.is(200), bodyString.saveAs("element"))
    )
    .exec(
      http("post one")
        .post("/save")
        .body(StringBody(session ⇒ session("element").validate[String]))
        .check(status.is(200), bodyString.is(session ⇒ session("element").validate[String] ))
    )

  val injections = Seq(rampUsers(10) over 5.seconds)

  val assertions = Seq(
    global.responseTime.mean.lt(1500)
    , forAll.failedRequests.count.lt(1)
  )

  setUp(
    scn
      .inject(injections)
      .protocols(protobolBuilder)
  ).assertions(assertions)
}
