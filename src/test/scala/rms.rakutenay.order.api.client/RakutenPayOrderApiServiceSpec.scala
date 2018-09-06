package rms.rakutenay.order.api.client

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import rms.rakutenpay.order.api.client._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class RakutenPayOrderApiServiceSpec extends Specification with Mockito {

  lazy val rakutenPayOrderApiService: RakutenPayOrderApiService = new RakutenPayOrderApiService("SP360238_yD3cYM1Gg0nbNB7o", "SL360238_qlhUV6Ll9KQHFIWr")

  "RakutenPayOrderApiService should" >> {

    "get order correctly when it exists" >> {
      val response = rakutenPayOrderApiService.getOrder(GetOrderRequestModel(List("360238-20180829-00000723")))
      response must beAnInstanceOf[GetOrderResponseModel]
    }

    "get payment " >> {
      Await.result(Future(rakutenPayOrderApiService.getPayment(GetPaymentRequestModel("360238-20180829-00000723"))
      ), Duration.Inf) must beAnInstanceOf[GetPaymentResponseModel]
    }

  }

}
