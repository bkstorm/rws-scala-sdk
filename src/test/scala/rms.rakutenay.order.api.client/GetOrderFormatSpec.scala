package rms.rakutenay.order.api.client

import org.specs2.mutable.Specification
import play.api.libs.json.{JsSuccess, Json}
import rms.rakutenpay.order.api.client.GetOrderResponseModel
import rms.rakutenpay.order.api.client.RWSFormats._

class GetOrderFormatSpec extends Specification {
  "GetOrderFormat should" >> {
    "read json to model correctly" >> {
      val json =
        """
          |{
          |   "MessageModelList":[
          |      {
          |         "messageType":"INFO",
          |         "messageCode":"ORDER_EXT_API_GET_ORDER_INFO_101",
          |         "message":"受注情報取得に成功しました。(取得件数1件)"
          |      }
          |   ],
          |   "OrderModelList":[
          |      {
          |         "orderNumber":"360238-20180724-00000810",
          |         "orderProgress":300,
          |         "subStatusId":null,
          |         "subStatusName":null,
          |         "orderDatetime":"2018-07-24T16:01:22+0900",
          |         "shopOrderCfmDatetime":"2018-07-25T10:54:24+0900",
          |         "orderFixDatetime":"2018-07-25T10:57:45+0900",
          |         "shippingInstDatetime":"2018-07-25T10:57:45+0900",
          |         "shippingCmplRptDatetime":null,
          |         "cancelDueDate":"2019-07-24",
          |         "deliveryDate":null,
          |         "shippingTerm":null,
          |         "remarks":"[配送日時指定:]\n\n",
          |         "giftCheckFlag":0,
          |         "severalSenderFlag":0,
          |         "equalSenderFlag":1,
          |         "isolatedIslandFlag":0,
          |         "rakutenMemberFlag":1,
          |         "carrierCode":0,
          |         "emailCarrierCode":0,
          |         "orderType":1,
          |         "reserveNumber":null,
          |         "reserveDeliveryCount":null,
          |         "cautionDisplayType":0,
          |         "rakutenConfirmFlag":0,
          |         "goodsPrice":20,
          |         "goodsTax":0,
          |         "postagePrice":100,
          |         "deliveryPrice":100,
          |         "totalPrice":120,
          |         "requestPrice":220,
          |         "couponAllTotalPrice":0,
          |         "couponShopPrice":0,
          |         "couponOtherPrice":0,
          |         "asurakuFlag":0,
          |         "drugFlag":0,
          |         "dealFlag":0,
          |         "membershipType":0,
          |         "memo":null,
          |         "operator":null,
          |         "mailPlugSentence":null,
          |         "modifyFlag":0,
          |         "isTaxRecalc":1,
          |         "OrdererModel":{
          |            "zipCode1":"100",
          |            "zipCode2":"0000",
          |            "prefecture":"東京都",
          |            "city":"千代田区",
          |            "subAddress":"東京都千代田区 Ha Noi - Viet Nam",
          |            "familyName":"Nguyen",
          |            "firstName":"Hoang",
          |            "familyNameKana":"ヤマダ",
          |            "firstNameKana":"タロウ",
          |            "phoneNumber1":"0165",
          |            "phoneNumber2":"626",
          |            "phoneNumber3":"1471",
          |            "emailAddress":"a44c9e3707e299d402ad81f34cc97cb9s1@pc.fw.rakuten.ne.jp",
          |            "sex":"-",
          |            "birthYear":0,
          |            "birthMonth":0,
          |            "birthDay":0
          |         },
          |         "SettlementModel":{
          |            "settlementMethod":"代金引換",
          |            "cardName":null,
          |            "cardNumber":null,
          |            "cardOwner":null,
          |            "cardYm":null,
          |            "cardPayType":null,
          |            "cardInstallmentDesc":null
          |         },
          |         "DeliveryModel":{
          |            "deliveryName":"宅配便",
          |            "deliveryClass":null
          |         },
          |         "PointModel":{
          |            "usedPoint":0
          |         },
          |         "WrappingModel1":null,
          |         "WrappingModel2":null,
          |         "PackageModelList":[
          |            {
          |               "basketId":2965858,
          |               "postagePrice":100,
          |               "deliveryPrice":100,
          |               "goodsTax":0,
          |               "goodsPrice":20,
          |               "totalPrice":220,
          |               "noshi":null,
          |               "packageDeleteFlag":0,
          |               "SenderModel":{
          |                  "zipCode1":"100",
          |                  "zipCode2":"0000",
          |                  "prefecture":"東京都",
          |                  "city":"千代田区",
          |                  "subAddress":"東京都千代田区 Ha Noi - Viet Nam",
          |                  "familyName":"Nguyen",
          |                  "firstName":"Hoang",
          |                  "familyNameKana":"ヤマダ",
          |                  "firstNameKana":"タロウ",
          |                  "phoneNumber1":"0165",
          |                  "phoneNumber2":"626",
          |                  "phoneNumber3":"1471"
          |               },
          |               "ItemModelList":[
          |                  {
          |                     "itemDetailId":2965858,
          |                     "itemName":"mugtestc01",
          |                     "itemId":10000000,
          |                     "itemNumber":"mugtestc01",
          |                     "manageNumber":"mugtestc01",
          |                     "price":20,
          |                     "units":1,
          |                     "includePostageFlag":0,
          |                     "includeTaxFlag":1,
          |                     "includeCashOnDeliveryPostageFlag":0,
          |                     "selectedChoice":null,
          |                     "pointRate":1,
          |                     "inventoryType":1,
          |                     "delvdateInfo":null,
          |                     "restoreInventoryFlag":0,
          |                     "deleteItemFlag":0
          |                  }
          |               ],
          |               "ShippingModelList":[
          |
          |               ],
          |               "DeliveryCvsModel":null
          |            }
          |         ],
          |         "CouponModelList":null,
          |         "ChangeReasonModelList":[
          |
          |         ]
          |      }
          |   ]
          |}
          """.stripMargin
      Json.fromJson[GetOrderResponseModel](Json.parse(json)) must beAnInstanceOf[JsSuccess[GetOrderResponseModel]]
    }
  }
}
