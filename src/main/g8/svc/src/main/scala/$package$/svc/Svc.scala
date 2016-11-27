package $package$.svc

import java.util.UUID

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server._
import akka.stream.ActorMaterializer
import com.microworkflow.domain.Element

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.io.StdIn

/**
  * Created by dam on 11/21/16.
  */
object Svc extends Directives with JsonMarshalling {

  def main(args: Array[String]): Unit = {
    val host = "localhost"
    val port = 8080
    implicit val actorSystem = ActorSystem("svc")
    implicit val materializer = ActorMaterializer()
    implicit val ec = actorSystem.dispatcher
    val route: Route =
      path("ping") {
        get {
          complete("pong")
        }
      } ~
        path("one") {
          get {
            val e = Element(UUID.randomUUID(), System.nanoTime())
            complete(e)
          }
        }  ~
        path("save") {
          post {
            entity(as[Element]) { e =>
              complete(e)
            }
          }
        }

    val bindingFuture = Http().bindAndHandle(route, host, port)
    bindingFuture.onFailure {
      case _: Exception ⇒ println("Failed to bind :(")
    }
    println("service http://%s:%s/ up".format(host, port))
  }
}
