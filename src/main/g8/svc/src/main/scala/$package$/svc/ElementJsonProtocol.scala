package $package$.svc

import java.util.UUID

import com.microworkflow.domain.Element
import spray.json._

object ElementJsonProtocol extends DefaultJsonProtocol {
  implicit object ElementJsonFormat extends RootJsonFormat[Element] {
    override def write(element: Element): JsValue = JsObject(
      "id" → JsString(element.id.toString)
      , "timestamp" → JsNumber(element.timestamp)
    )

    override def read(value: JsValue): Element =
      value.asJsObject.getFields("id", "timestamp") match {
        case Seq(JsString(id), JsNumber(timestamp)) ⇒ Element(UUID.fromString(id), timestamp.toLongExact)
        case _ ⇒ throw new DeserializationException("not an element")
      }
  }
}
