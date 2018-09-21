package rms.rakutenpay.order.api.client

import java.nio.charset.StandardCharsets
import java.util.Base64

import play.api.libs.json.{Json, Reads, Writes}
import requests.Response
import rms.rakutenpay.order.api.client.RWSFormats._

class RakutenPayOrderApiService(serviceSecret: String, licenseKey: String) {

  private def makeRequest[A <: BaseRequestModel, B <: BaseResponseModel](requestModel: A, url: String)(implicit writes: Writes[A], reads: Reads[B]): Either[Response, B] = {
    val r = requests.post(
      url,
      headers = Map(
        "Authorization" → s"ESA ${Base64.getEncoder.encodeToString(s"${serviceSecret}:${licenseKey}".getBytes(StandardCharsets.UTF_8))}",
        "Content-Type" → "application/json; charset=utf-8"
      ),
      data = Json.toJson[A](requestModel)(writes).toString()
    )
    r.statusCode match {
      case 200 ⇒ Right(Json.fromJson[B](Json.parse(r.text))(reads).get)
      case _ ⇒ Left(r)
    }
  }

  private def makeRequest[A <: BaseResponseModel](url: String)(implicit reads: Reads[A]): Either[Response, A] = {
    val r = requests.post(
      url,
      headers = Map(
        "Authorization" → s"ESA ${Base64.getEncoder.encodeToString(s"${serviceSecret}:${licenseKey}".getBytes(StandardCharsets.UTF_8))}",
        "Content-Type" → "application/json; charset=utf-8"
      ),
      data = ""
    )
    r.statusCode match {
      case 200 ⇒ Right(Json.fromJson[A](Json.parse(r.text()))(reads).get)
      case _ ⇒ Left(r)
    }
  }

  def searchOrder(searchOrderRequestModel: SearchOrderRequestModel): Either[Response, SearchOrderResponseModel] =
    makeRequest[SearchOrderRequestModel, SearchOrderResponseModel](
      searchOrderRequestModel,
      "https://api.rms.rakuten.co.jp/es/2.0/order/searchOrder/"
    )

  def getOrder(getOrderRequestModel: GetOrderRequestModel): Either[Response, GetOrderResponseModel] =
    makeRequest[GetOrderRequestModel, GetOrderResponseModel](
      getOrderRequestModel,
      "https://api.rms.rakuten.co.jp/es/2.0/order/getOrder/"
    )

  def confirmOrder(confirmOrderRequestModel: ConfirmOrderRequestModel): Either[Response, ConfirmOrderResponseModel] =
    makeRequest[ConfirmOrderRequestModel, ConfirmOrderResponseModel](
      confirmOrderRequestModel,
      "https://api.rms.rakuten.co.jp/es/2.0/order/confirmOrder/"
    )

  def updateShipping(updateShippingRequestModel: UpdateShippingRequestModel): Either[Response, UpdateShippingResponseModel] =
    makeRequest[UpdateShippingRequestModel, UpdateShippingResponseModel](
      updateShippingRequestModel,
      "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderShipping/"
    )

  def updateDelivery(updateDeliveryRequestModel: UpdateDeliveryRequestModel): Either[Response, UpdateDeliveryResponseModel] =
    makeRequest[UpdateDeliveryRequestModel, UpdateDeliveryResponseModel](
      updateDeliveryRequestModel,
      "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderDelivery/"
    )

  def updateOrderer(updateOrdererRequestModel: UpdateOrdererRequestModel): Either[Response, UpdateOrdererResponseModel] =
    makeRequest[UpdateOrdererRequestModel, UpdateOrdererResponseModel](
      updateOrdererRequestModel,
      "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderOrderer/"
    )

  def updateOrderRemarks(updateRemarksRequestModel: UpdateRemarksRequestModel): Either[Response, UpdateRemarksResponseModel] =
    makeRequest[UpdateRemarksRequestModel, UpdateRemarksResponseModel](
      updateRemarksRequestModel,
      "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderRemarks/"
    )

  def updateSender(updateSenderRequestModel: UpdateSenderRequestModel): Either[Response, UpdateSenderResponseModel] =
    makeRequest[UpdateSenderRequestModel, UpdateSenderResponseModel](
      updateSenderRequestModel,
      "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderSender/"
    )

  def updateMemo(updateMemoRequestModel: UpdateMemoRequestModel): Either[Response, UpdateMemoResponseModel] =
    makeRequest[UpdateMemoRequestModel, UpdateMemoResponseModel](
      updateMemoRequestModel,
      "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderMemo/"
    )

  def getPayment(getPaymentRequestModel: GetPaymentRequestModel): Either[Response, GetPaymentResponseModel] =
    makeRequest[GetPaymentRequestModel, GetPaymentResponseModel](
      getPaymentRequestModel,
      "https://api.rms.rakuten.co.jp/es/2.0/order/getPayment/"
    )

  def cancelOrder(cancelOrderRequestModel: CancelOrderRequestModel): Either[Response, CancelOrderResponseModel] =
    makeRequest[CancelOrderRequestModel, CancelOrderResponseModel](
      cancelOrderRequestModel,
      "https://api.rms.rakuten.co.jp/es/2.0/order/cancelOrder/"
    )

  def getSubStatusList(): Either[Response, GetSubStatusListResponseModel] =
    makeRequest[GetSubStatusListResponseModel](
      url = "https://api.rms.rakuten.co.jp/es/2.0/order/getSubStatusList/"
    )
}
