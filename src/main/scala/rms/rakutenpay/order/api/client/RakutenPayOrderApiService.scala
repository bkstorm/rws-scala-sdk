package rms.rakutenpay.order.api.client

import java.nio.charset.StandardCharsets
import java.util.Base64

import play.api.libs.json.{Json, Reads, Writes}
import rms.rakutenpay.order.api.client.RWSFormats._

class RakutenPayOrderApiService(serviceSecret: String, licenseKey: String) {

  private def makeRequest[A <: BaseRequestModel, B <: BaseResponseModel](requestModel: A, url: String)(implicit writes: Writes[A], reads: Reads[B]): B = {
    val r = requests.post(
      url,
      headers = Map(
        "Authorization" → s"ESA ${Base64.getEncoder.encodeToString(s"${serviceSecret}:${licenseKey}".getBytes(StandardCharsets.UTF_8))}",
        "Content-Type" → "application/json; charset=utf-8"
      ),
      data = Json.toJson[A](requestModel)(writes).toString()
    )
    Json.fromJson[B](Json.parse(r.text()))(reads).get
  }

  def searchOrder(searchOrderRequestModel: SearchOrderRequestModel): SearchOrderResponseModel =
    makeRequest[SearchOrderRequestModel, SearchOrderResponseModel](searchOrderRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/searchOrder/")

  def getOrder(getOrderRequestModel: GetOrderRequestModel): GetOrderResponseModel =
    makeRequest[GetOrderRequestModel, GetOrderResponseModel](getOrderRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/getOrder/")

  def confirmOrder(confirmOrderRequestModel: ConfirmOrderRequestModel): ConfirmOrderResponseModel =
    makeRequest[ConfirmOrderRequestModel, ConfirmOrderResponseModel](confirmOrderRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/confirmOrder/")

  def updateShipping(updateShippingRequestModel: UpdateShippingRequestModel): UpdateShippingResponseModel =
    makeRequest[UpdateShippingRequestModel, UpdateShippingResponseModel](updateShippingRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderShipping/")

  def updateDelivery(updateDeliveryRequestModel: UpdateDeliveryRequestModel): UpdateDeliveryResponseModel =
    makeRequest[UpdateDeliveryRequestModel, UpdateDeliveryResponseModel](updateDeliveryRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderDelivery/")

  def updateOrderer(updateOrdererRequestModel: UpdateOrdererRequestModel): UpdateOrdererResponseModel =
    makeRequest[UpdateOrdererRequestModel, UpdateOrdererResponseModel](updateOrdererRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderOrderer/")

  def updateOrderRemarks(updateRemarksRequestModel: UpdateRemarksRequestModel): UpdateRemarksResponseModel =
    makeRequest[UpdateRemarksRequestModel, UpdateRemarksResponseModel](updateRemarksRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderRemarks/")

  def updateSender(updateSenderRequestModel: UpdateSenderRequestModel): UpdateSenderResponseModel =
    makeRequest[UpdateSenderRequestModel, UpdateSenderResponseModel](updateSenderRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderSender/")

  def updateMemo(updateMemoRequestModel: UpdateMemoRequestModel): UpdateMemoResponseModel =
    makeRequest[UpdateMemoRequestModel, UpdateMemoResponseModel](updateMemoRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderMemo/")

  def getPayment(getPaymentRequestModel: GetPaymentRequestModel): GetPaymentResponseModel =
    makeRequest[GetPaymentRequestModel, GetPaymentResponseModel](getPaymentRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/getPayment/")

  def cancelOrder(cancelOrderRequestModel: CancelOrderRequestModel): CancelOrderResponseModel =
    makeRequest[CancelOrderRequestModel, CancelOrderResponseModel](cancelOrderRequestModel, "https://api.rms.rakuten.co.jp/es/2.0/order/cancelOrder/")
}
