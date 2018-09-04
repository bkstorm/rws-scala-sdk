package rms.rakutenay.order.api.client

import org.joda.time.LocalDate
import org.specs2.mutable.Specification
import play.api.libs.json._
import rms.rakutenpay.order.api.client.{BasketidModel, ShippingModel, UpdateShippingRequestModel, UpdateShippingResponseModel}
import rms.rakutenpay.order.api.client.RWSFormats._

class UpdateOrderShippingFormatSpec extends Specification {
  "UpdateOrderShippingFormat should" >> {
    "writes model to json correctly" >> {
      val updateOrderShippingRequestModel = UpdateShippingRequestModel(
        orderNumber = "123456-20180101-00001801",
        BasketidModelList = List(
          BasketidModel(
            basketId = 10746402,
            ShippingModelList = List(
              ShippingModel(
                shippingDetailId = Some(15419),
                deliveryCompany = Some("1001"),
                shippingNumber = Some("SN111111"),
                shippingDate = Some(new LocalDate(2018, 1, 25))
              )
            )
          )
        )
      )

      val json = Json.toJson(updateOrderShippingRequestModel)
      json \\ "orderNumber" contains JsString("123456-20180101-00001801") must_== true
      json \\ "basketId" contains JsNumber(10746402) must_== true
      json \\ "shippingDetailId" contains JsNumber(15419) must_== true
      json \\ "deliveryCompany" contains JsString("1001") must_== true
      json \\ "shippingNumber" contains JsString("SN111111") must_== true
      json \\ "shippingDate" contains JsString("2018-01-25") must_== true
    }

    "read json to model correctly" >> {
      val json =
        """
          |{
          |    "MessageModelList": [
          |        {
          |            "messageType": "INFO",
          |            "messageCode": "ORDER_EXT_API_UPDATE_ORDERSHIPPING_INFO_102",
          |            "message": "発送情報の更新設定が完了しました。",
          |            "dataNumber": 1,
          |            "shippingDetailId": 15419
          |        },
          |        {
          |            "messageType": "INFO",
          |            "messageCode": "ORDER_EXT_API_UPDATE_ORDERSHIPPING_INFO_101",
          |            "message": "発送情報の追加設定が完了しました。",
          |            "dataNumber": 2,
          |            "shippingDetailId": null
          |        },
          |        {
          |            "messageType": "INFO",
          |            "messageCode": "ORDER_EXT_API_UPDATE_ORDERSHIPPING_INFO_101",
          |            "message": "発送情報の追加設定が完了しました。",
          |            "dataNumber": 3,
          |            "shippingDetailId": null
          |        },
          |        {
          |            "messageType": "INFO",
          |            "messageCode": "ORDER_EXT_API_UPDATE_ORDERSHIPPING_INFO_101",
          |            "message": "発送情報の追加設定が完了しました。",
          |            "dataNumber": 4,
          |            "shippingDetailId": null
          |        },
          |        {
          |            "messageType": "INFO",
          |            "messageCode": "ORDER_EXT_API_UPDATE_ORDERSHIPPING_INFO_101",
          |            "message": "発送情報の追加設定が完了しました。",
          |            "dataNumber": 5,
          |            "shippingDetailId": null
          |        }
          |    ]
          |}
        """.stripMargin
      val updateOrderShippingResponseModel = Json.fromJson[UpdateShippingResponseModel](Json.parse(json)).get
      updateOrderShippingResponseModel.MessageModelList.filter(_.shippingDetailId.contains(15419)).length must_==(1)
      updateOrderShippingResponseModel.MessageModelList.filter(_.shippingDetailId.isEmpty).length must_==(4)
    }
  }
}
