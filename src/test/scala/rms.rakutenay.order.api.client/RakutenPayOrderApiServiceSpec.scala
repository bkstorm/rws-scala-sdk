package rms.rakutenay.order.api.client

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import requests.Response
import rms.rakutenpay.order.api.client._

class RakutenPayOrderApiServiceSpec extends Specification with Mockito {

  lazy val rakutenPayOrderApiService: RakutenPayOrderApiService = new RakutenPayOrderApiService("SP360238_yD3cYM1Gg0nbNB7o", "SL360238_9WLIP0UqG4Sa89F7")

  "RakutenPayOrderApiService should" >> {

    "get order correctly when it exists" >> {
      val response = rakutenPayOrderApiService.getOrder(GetOrderRequestModel(List("360238-20180829-00000723")))
      response must beAnInstanceOf[Right[Response, GetOrderResponseModel]]
    }

    "get payment " >> {
      val response = rakutenPayOrderApiService.getPayment(GetPaymentRequestModel("360238-20180829-00000723"))
      response must beAnInstanceOf[Right[Response, GetPaymentResponseModel]]
    }

    "get sub status list" >> {
      val response = rakutenPayOrderApiService.getSubStatusList()
      response must beAnInstanceOf[Right[Response, GetSubStatusListResponseModel]]
    }

  }

}
