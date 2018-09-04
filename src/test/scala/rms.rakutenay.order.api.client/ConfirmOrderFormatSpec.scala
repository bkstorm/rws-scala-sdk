package rms.rakutenay.order.api.client

import org.specs2.mutable.Specification
import play.api.libs.json._
import rms.rakutenpay.order.api.client.{ConfirmOrderRequestModel, ConfirmOrderResponseModel}
import rms.rakutenpay.order.api.client.RWSFormats._

class ConfirmOrderFormatSpec extends Specification {
  "ConfirmOrderFormat should" >> {
    "writes model to json correctly" >> {
      val confirmOrderRequestModel = ConfirmOrderRequestModel(
        List(
          "123456-20180101-1241583267",
          "123456-20180102-1241583267",
          "123456-20180103-1241583267"
        )
      )
      val json: JsValue = Json.toJson[ConfirmOrderRequestModel](confirmOrderRequestModel)
      val orderNumberList = (json \\ "orderNumberList").head
      orderNumberList must beAnInstanceOf[JsArray]
      orderNumberList.asInstanceOf[JsArray].value contains JsString("123456-20180101-1241583267") must_== true
      orderNumberList.asInstanceOf[JsArray].value contains JsString("123456-20180102-1241583267") must_== true
      orderNumberList.asInstanceOf[JsArray].value contains JsString("123456-20180103-1241583267") must_== true
    }

    "reads json to model correctly" >> {
      val json =
        """
          |{
          |    "MessageModelList": [
          |        {
          |            "messageType": "INFO",
          |            "messageCode": "ORDER_EXT_API_CONFIRM_ORDER_INFO_101",
          |            "message": "注文確認に成功しました。",
          |            "orderNumber": "123456-20180101-1241583267"
          |        },
          |        {
          |            "messageType": "INFO",
          |            "messageCode": "ORDER_EXT_API_CONFIRM_ORDER_INFO_101",
          |            "message": "注文確認に成功しました。",
          |            "orderNumber": "123456-20180102-1241583267"
          |        },
          |        {
          |            "messageType": "INFO",
          |            "messageCode": "ORDER_EXT_API_CONFIRM_ORDER_INFO_101",
          |            "message": "注文確認に成功しました。",
          |            "orderNumber": "123456-20180103-1241583267"
          |        }
          |    ]
          |}
        """.stripMargin
      val result: JsResult[ConfirmOrderResponseModel] = Json.fromJson[ConfirmOrderResponseModel](Json.parse(json))
      result must beAnInstanceOf[JsSuccess[ConfirmOrderResponseModel]]
      result.get.MessageModelList.length must_== 3
    }
  }
}
