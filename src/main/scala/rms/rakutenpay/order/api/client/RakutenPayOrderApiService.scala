package rms.rakutenpay.order.api.client

import java.nio.charset.StandardCharsets
import java.util.Base64

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import play.api.libs.json.{JsValue, Json, Reads, Writes}
import play.api.libs.ws.JsonBodyReadables._
import play.api.libs.ws.JsonBodyWritables._
import play.api.libs.ws.StandaloneWSClient
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import play.shaded.ahc.io.netty.handler.codec.http.HttpHeaders
import rms.rakutenpay.order.api.client.RWSFormats._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class RakutenPayOrderApiService(serviceSecret: String, licenseKey: String) {

  private implicit val system = ActorSystem()
  //  system.registerOnTermination {
  //    System.exit(0)
  //  }
  private implicit val materializer = ActorMaterializer()
  val wsClient = StandaloneAhcWSClient()

  private def withClient[T](block: StandaloneWSClient => Future[T]): Future[T] = {
    block(wsClient)
      .andThen { case _ => wsClient.close() }
      .andThen { case _ => system.terminate() }
  }

  private def makeRequest[A <: BaseRequestModel, B <: BaseResponseModel](requestModel: A, url: String)(implicit writes: Writes[A], reads: Reads[B]): Future[B] = withClient { client ⇒
    val req = client
      .url(url)
      .withMethod("POST")
      .withHttpHeaders(
        HttpHeaders.Names.AUTHORIZATION -> s"ESA ${Base64.getEncoder.encodeToString(s"${serviceSecret}:${licenseKey}".getBytes(StandardCharsets.UTF_8))}",
        HttpHeaders.Names.CONTENT_TYPE -> "application/json; charset=utf-8"
      )
      .withBody(Json.toJson[A](requestModel)(writes))
    req.execute().map(response ⇒
      Json.fromJson[B](response.body[JsValue])(reads).get
    )
  }

  def searchOrder(searchOrderRequestModel: SearchOrderRequestModel): Future[SearchOrderResponseModel] =
    makeRequest[SearchOrderRequestModel, SearchOrderResponseModel](searchOrderRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/searchOrder/")

  def getOrder(getOrderRequestModel: GetOrderRequestModel): Future[GetOrderResponseModel] =
    makeRequest[GetOrderRequestModel, GetOrderResponseModel](getOrderRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/getOrder/")

  def confirmOrder(confirmOrderRequestModel: ConfirmOrderRequestModel): Future[ConfirmOrderResponseModel] =
    makeRequest[ConfirmOrderRequestModel, ConfirmOrderResponseModel](confirmOrderRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/confirmOrder/")

  def updateShipping(updateShippingRequestModel: UpdateShippingRequestModel): Future[UpdateShippingResponseModel] =
    makeRequest[UpdateShippingRequestModel, UpdateShippingResponseModel](updateShippingRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderShipping/")

  def updateDelivery(updateDeliveryRequestModel: UpdateDeliveryRequestModel): Future[UpdateDeliveryResponseModel] =
    makeRequest[UpdateDeliveryRequestModel, UpdateDeliveryResponseModel](updateDeliveryRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderDelivery/")

  def updateOrderer(updateOrdererRequestModel: UpdateOrdererRequestModel): Future[UpdateOrdererResponseModel] =
    makeRequest[UpdateOrdererRequestModel, UpdateOrdererResponseModel](updateOrdererRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderOrderer/")

  def updateOrderRemarks(updateRemarksRequestModel: UpdateRemarksRequestModel): Future[UpdateRemarksResponseModel] =
    makeRequest[UpdateRemarksRequestModel, UpdateRemarksResponseModel](updateRemarksRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderRemarks/")

  def updateSender(updateSenderRequestModel: UpdateSenderRequestModel): Future[UpdateSenderResponseModel] =
    makeRequest[UpdateSenderRequestModel, UpdateSenderResponseModel](updateSenderRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderSender/")

  def updateMemo(updateMemoRequestModel: UpdateMemoRequestModel): Future[UpdateMemoResponseModel] =
    makeRequest[UpdateMemoRequestModel, UpdateMemoResponseModel](updateMemoRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderMemo/")

  def getPayment(getPaymentRequestModel: GetPaymentRequestModel): Future[GetPaymentResponseModel] =
    makeRequest[GetPaymentRequestModel, GetPaymentResponseModel](getPaymentRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/getPayment/")

  def cancelOrder(cancelOrderRequestModel: CancelOrderRequestModel): Future[CancelOrderResponseModel] =
    makeRequest[CancelOrderRequestModel, CancelOrderResponseModel](cancelOrderRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/cancelOrder/")
}
