package rms.rakutenpay.order.api.client

import play.api.libs.json.Json

object RWSJsonFormatPool {
  implicit val changeReasonModelFormat = Json.format[ChangeReasonModel]
  implicit val couponModelFormat = Json.format[CouponModel]
  implicit val deliveryCvsModelFormat = Json.format[DeliveryCvsModel]
  implicit val deliveryModelFormat = Json.format[DeliveryModel]
  implicit val itemModelFormat = Json.format[ItemModel]
  implicit val messageModelFormat = Json.format[MessageModel]
  implicit val ordererModelFormat = Json.format[OrdererModel]
  implicit val orderModelFormat = Json.format[OrderModel]
  implicit val packageModelFormat = Json.format[PackageModel]
  implicit val pointModelFormat = Json.format[PointModel]
  implicit val senderModelFormat = Json.format[SenderModel]
  implicit val settlementModelFormat = Json.format[SettlementModel]
  implicit val shippingModelFormat = Json.format[ShippingModel]
  implicit val wrappingModelFormat = Json.format[WrappingModel]
}
