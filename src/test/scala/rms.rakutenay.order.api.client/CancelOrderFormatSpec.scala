package rms.rakutenay.order.api.client

import org.specs2.mutable.Specification
import play.api.libs.json._
import rms.rakutenpay.order.api.client.RWSFormats._
import rms.rakutenpay.order.api.client.{CancelOrderRequestModel, CancelOrderResponseModel}

class CancelOrderFormatSpec extends Specification {

  "CancelOrderFormats should" >> {
    "writes model to Json correctly" >> {
      val cancelOrderRequestModel = CancelOrderRequestModel(
        orderNumber = "123456-20180101-00684725",
        inventoryRestoreType = 2,
        changeReasonDetailApply = 1
      )
      val json = Json.toJson[CancelOrderRequestModel](cancelOrderRequestModel)
      json.\\("orderNumber").contains(JsString("123456-20180101-00684725")) must_== true
      json.\\("inventoryRestoreType").contains(JsNumber(2)) must_== true
      json.\\("changeReasonDetailApply").contains(JsNumber(1)) must_== true
    }

    "reads json to model correctly" >> {
      Json.fromJson[CancelOrderResponseModel](Json.parse(
        """
          |{
          |    "MessageModelList": [
          |        {
          |            "messageType": "INFO",
          |            "messageCode": "ORDER_EXT_API_CANCEL_ORDER_INFO_101",
          |            "message": "キャンセル申請に成功しました。",
          |            "orderNumber": "123456-20180101-00684725"
          |        }
          |    ]
          |}
        """.stripMargin)) match {
        case JsSuccess(model, _) ⇒
          model.MessageModelList.head.messageType must_== "INFO"
          model.MessageModelList.head.messageCode must_== "ORDER_EXT_API_CANCEL_ORDER_INFO_101"
          model.MessageModelList.head.message must_== "キャンセル申請に成功しました。"
          model.MessageModelList.head.orderNumber must beSome("123456-20180101-00684725")
        case _ ⇒ 1 must_== 2
      }
    }
  }
}
