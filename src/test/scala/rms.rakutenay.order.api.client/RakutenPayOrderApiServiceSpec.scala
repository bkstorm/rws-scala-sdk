package rms.rakutenay.order.api.client

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import rms.rakutenpay.order.api.client.{GetOrderRequestModel, GetOrderResponseModel, RakutenPayOrderApiService}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

class RakutenPayOrderApiServiceSpec extends Specification with Mockito {

  lazy val rakutenPayOrderApiService: RakutenPayOrderApiService = new RakutenPayOrderApiService("SP360238_yD3cYM1Gg0nbNB7o", "SL360238_qlhUV6Ll9KQHFIWr")

  "RakutenPayOrderApiService should" >> {

    "get order correctly when it exists" >> {
      Await.result(rakutenPayOrderApiService.getOrder(GetOrderRequestModel(List("360238-20180724-00000810"))), Duration.Inf) must beAnInstanceOf[GetOrderResponseModel]
    }

  }

}
