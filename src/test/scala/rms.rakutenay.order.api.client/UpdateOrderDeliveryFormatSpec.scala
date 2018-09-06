package rms.rakutenay.order.api.client

import org.specs2.mutable.Specification
import play.api.libs.json.{JsString, Json}
import rms.rakutenpay.order.api.client.RWSFormats._
import rms.rakutenpay.order.api.client.UpdateDeliveryRequestModel

class UpdateOrderDeliveryFormatSpec extends Specification {
  "UpdateOrderDeliveryFormat should" >> {
    "writes model to json correctly" >> {
      val model = UpdateDeliveryRequestModel(
        orderNumber = "123456-20180101-1241583267",
        deliveryName = "メール便"
      )
      val json = Json.toJson(model)
      json("orderNumber").must_==(JsString("123456-20180101-1241583267"))
      json("deliveryName").must_==(JsString("メール便"))
    }
  }
}
