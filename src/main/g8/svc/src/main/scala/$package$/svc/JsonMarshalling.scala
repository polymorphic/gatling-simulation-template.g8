package $package$.svc

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.marshalling._
import akka.http.scaladsl.model.MessageEntity
import akka.http.scaladsl.unmarshalling.{FromEntityUnmarshaller, PredefinedFromEntityUnmarshallers, Unmarshaller}
import com.microworkflow.domain.Element
import com.microworkflow.svc.ElementJsonProtocol._
import spray.json._

trait JsonMarshalling extends SprayJsonSupport with DefaultJsonProtocol {

  def element2Json(e: Element): JsObject =
    JsObject(Map("id" → JsString(e.id.toString), "timestamp" → JsNumber(e.timestamp)))

  implicit def element2MessageEntity: Marshaller[Element, MessageEntity] = Marshaller.combined(element2Json)

  implicit val rawElementFromEntityUnmarshaller: FromEntityUnmarshaller[Element] =
    PredefinedFromEntityUnmarshallers.stringUnmarshaller.map(s ⇒ {
      val jsonAst = JsonParser(s)
      jsonAst.convertTo[Element]
    })
}
