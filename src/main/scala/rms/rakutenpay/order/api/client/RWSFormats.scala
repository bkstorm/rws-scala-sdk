package rms.rakutenpay.order.api.client

import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime, LocalDate}
import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json.Writes._
import play.api.libs.json._

object RWSFormats {
  implicit val dateTimeWriter: Writes[DateTime] = new Writes[DateTime] {
    val df = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")

    def writes(d: DateTime): JsValue = JsString(d.toString(df))
  }
  implicit val dateTimeJsReader: Reads[DateTime] = new Reads[DateTime] {

    val df = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")

    def reads(json: JsValue): JsResult[DateTime] = json match {
      case JsNumber(d) => JsSuccess(new DateTime(d.toLong))
      case JsString(s) => parseDate(s) match {
        case Some(d) => JsSuccess(d)
        case _ => JsError(Seq(JsPath() -> Seq(ValidationError("error.expected.jodadate.format", "yyyy-MM-dd'T'HH:mm:ssZ"))))
      }
      case _ => JsError(Seq(JsPath() -> Seq(ValidationError("error.expected.date"))))
    }

    private def parseDate(input: String): Option[DateTime] =
      scala.util.control.Exception.nonFatalCatch[DateTime] opt (DateTime.parse(input, df))

  }

  implicit val localTimeWriter: Writes[LocalDate] = {
    val df = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd")
    Writes[LocalDate] { d => JsString(d.toString(df)) }
  }

  implicit val localTimeJsReader: Reads[LocalDate] = new Reads[org.joda.time.LocalDate] {

    val df = DateTimeFormat.forPattern("yyyy-MM-dd")

    def reads(json: JsValue): JsResult[LocalDate] = json match {
      case JsString(s) => parseDate(s) match {
        case Some(d) => JsSuccess(d)
        case _ => JsError(Seq(JsPath() -> Seq(ValidationError("error.expected.jodadate.format", "yyyy-MM-dd"))))
      }
      case _ => JsError(Seq(JsPath() -> Seq(ValidationError("error.expected.date"))))
    }

    private def parseDate(input: String): Option[LocalDate] =
      scala.util.control.Exception.nonFatalCatch[LocalDate] opt (LocalDate.parse(input, df))
  }

  implicit def optionFormat[T: Format]: Format[Option[T]] = new Format[Option[T]] {
    override def reads(json: JsValue): JsResult[Option[T]] = json.validateOpt[T]

    override def writes(o: Option[T]): JsValue = o match {
      case Some(t) ⇒ implicitly[Writes[T]].writes(t)
      case None ⇒ JsNull
    }
  }

  implicit val dateTimeFormat: Format[DateTime] = new Format[DateTime] {
    override def reads(json: JsValue): JsResult[DateTime] = dateTimeJsReader.reads(json)

    override def writes(o: DateTime): JsValue = dateTimeWriter.writes(o)
  }
  implicit val localDateTimeFormat: Format[LocalDate] = new Format[LocalDate] {
    override def reads(json: JsValue): JsResult[LocalDate] = localTimeJsReader.reads(json)

    override def writes(o: LocalDate): JsValue = localTimeWriter.writes(o)
  }

  implicit val changeReasonModelFormat: Format[ChangeReasonModel] = Json.format[ChangeReasonModel]
  implicit val couponModelFormat: Format[CouponModel] = Json.format[CouponModel]
  implicit val deliveryCvsModelFormat: Format[DeliveryCvsModel] = Json.format[DeliveryCvsModel]
  implicit val deliveryModelFormat: Format[DeliveryModel] = Json.format[DeliveryModel]
  implicit val itemModelFormat: Format[ItemModel] = Json.format[ItemModel]
  implicit val messageModelFormat: Format[MessageModel] = Json.format[MessageModel]
  implicit val ordererModelFormat: Format[OrdererModel] = Json.format[OrdererModel]

  implicit val senderModelFormat: Format[SenderModel] = Json.format[SenderModel]
  implicit val shippingModelFormat: Format[ShippingModel] = Json.format[ShippingModel]
  implicit val packageModelFormat: Format[PackageModel] = Json.format[PackageModel]
  implicit val pointModelFormat: Format[PointModel] = Json.format[PointModel]
  implicit val settlementModelFormat: Format[SettlementModel] = Json.format[SettlementModel]
  implicit val wrappingModelFormat: Format[WrappingModel] = Json.format[WrappingModel]

  implicit val basketidModelFormat: Format[BasketidModel] = Json.format[BasketidModel]

  implicit val paymentCardModelFormat: Format[PaymentCardModel] = Json.format[PaymentCardModel]
  implicit val paymentMultiModel: Format[PaymentMultiModel] = Json.format[PaymentMultiModel]
  implicit val paymentBankModel: Format[PaymentBankModel] = Json.format[PaymentBankModel]
  implicit val refundModel: Format[RefundModel] = Json.format[RefundModel]
  implicit val paymentModelFormat: Format[PaymentModel] = Json.format[PaymentModel]

  implicit val sortModelFormat: Format[SortModel] = Json.format[SortModel]
  implicit val paginationRequestModelFormat: Format[PaginationRequestModel] = Json.format[PaginationRequestModel]
  implicit val paginationResponseModelFormat: Format[PaginationResponseModel] = Json.format[PaginationResponseModel]

  implicit val subStatusModelFormat: Format[SubStatusModel] = Json.format[SubStatusModel]
  implicit val statusModelFormat: Format[StatusModel] = Json.format[StatusModel]

  val orderModelFirstFormat: OFormat[(String, Int, Option[Int], Option[String], DateTime, Option[DateTime], Option[DateTime], Option[DateTime], Option[DateTime], Option[LocalDate])] = (
    (__ \ "orderNumber").format[String] and
      (__ \ "orderProgress").format[Int] and
      (__ \ "subStatusId").format[Option[Int]] and
      (__ \ "subStatusName").format[Option[String]] and
      (__ \ "orderDatetime").format[DateTime] and
      (__ \ "shopOrderCfmDatetime").format[Option[DateTime]] and
      (__ \ "orderFixDatetime").format[Option[DateTime]] and
      (__ \ "shippingInstDatetime").format[Option[DateTime]] and
      (__ \ "shippingCmplRptDatetime").format[Option[DateTime]] and
      (__ \ "cancelDueDate").format[Option[LocalDate]]
    ).tupled

  val orderModelSecondFormat: OFormat[(Option[LocalDate], Option[Int], Option[String], Byte, Byte, Byte, Byte, Byte, Byte, Byte)] = (
    (__ \ "deliveryDate").format[Option[LocalDate]] and
      (__ \ "shippingTerm").format[Option[Int]] and
      (__ \ "remarks").format[Option[String]] and
      (__ \ "giftCheckFlag").format[Byte] and
      (__ \ "severalSenderFlag").format[Byte] and
      (__ \ "equalSenderFlag").format[Byte] and
      (__ \ "isolatedIslandFlag").format[Byte] and
      (__ \ "rakutenMemberFlag").format[Byte] and
      (__ \ "carrierCode").format[Byte] and
      (__ \ "emailCarrierCode").format[Byte]
    ).tupled

  val orderModelThirdFormat: OFormat[(Byte, Option[String], Option[Int], Byte, Byte, Long, Int, Int, Int, Long)] = (
    (__ \ "orderType").format[Byte] and
      (__ \ "reserveNumber").format[Option[String]] and
      (__ \ "reserveDeliveryCount").format[Option[Int]] and
      (__ \ "cautionDisplayType").format[Byte] and
      (__ \ "rakutenConfirmFlag").format[Byte] and
      (__ \ "goodsPrice").format[Long] and
      (__ \ "goodsTax").format[Int] and
      (__ \ "postagePrice").format[Int] and
      (__ \ "deliveryPrice").format[Int] and
      (__ \ "totalPrice").format[Long]
    ).tupled

  val orderModelFourthFormat: OFormat[(Long, Long, Long, Long, Byte, Byte, Byte, Byte, Option[String], Option[String])] = (
    (__ \ "requestPrice").format[Long] and
      (__ \ "couponAllTotalPrice").format[Long] and
      (__ \ "couponShopPrice").format[Long] and
      (__ \ "couponOtherPrice").format[Long] and
      (__ \ "asurakuFlag").format[Byte] and
      (__ \ "drugFlag").format[Byte] and
      (__ \ "dealFlag").format[Byte] and
      (__ \ "membershipType").format[Byte] and
      (__ \ "memo").format[Option[String]] and
      (__ \ "operator").format[Option[String]]
    ).tupled

  val orderModelFifthFormat: OFormat[(Option[String], Byte, Byte, OrdererModel, Option[SettlementModel], DeliveryModel, Option[PointModel], Option[WrappingModel], Option[WrappingModel], List[PackageModel], Option[List[CouponModel]], Option[List[ChangeReasonModel]], Option[List[PaymentModel]])] = (
    (__ \ "mailPlugSentence").formatNullable[String] and
      (__ \ "modifyFlag").format[Byte] and
      (__ \ "isTaxRecalc").format[Byte] and
      (__ \ "OrdererModel").format[OrdererModel] and
      (__ \ "SettlementModel").formatNullable[SettlementModel] and
      (__ \ "DeliveryModel").format[DeliveryModel] and
      (__ \ "PointModel").formatNullable[PointModel] and
      (__ \ "WrappingModel1").formatNullable[WrappingModel] and
      (__ \ "WrappingModel2").formatNullable[WrappingModel] and
      (__ \ "PackageModelList").format[List[PackageModel]] and
      (__ \ "CouponModelList").formatNullable[List[CouponModel]] and
      (__ \ "ChangeReasonModelList").formatNullable[List[ChangeReasonModel]] and
      (__ \ "PaymentModelList").formatNullable[List[PaymentModel]]
    ).tupled

  implicit val orderModelFormat: Format[OrderModel] = (
    orderModelFirstFormat and
      orderModelSecondFormat and
      orderModelThirdFormat and
      orderModelFourthFormat and
      orderModelFifthFormat
    ).apply({
    case (
      (orderNumber, orderProgress, subStatusId, subStatusName, orderDatetime, shopOrderCfmDatetime, orderFixDatetime, shippingInstDatetime, shippingCmplRptDatetime, cancelDueDate),
      (deliveryDate, shippingTerm, remarks, giftCheckFlag, severalSenderFlag, equalSenderFlag, isolatedIslandFlag, rakutenMemberFlag, carrierCode, emailCarrierCode),
      (orderType, reserveNumber, reserveDeliveryCount, cautionDisplayType, rakutenConfirmFlag, goodsPrice, goodsTax, postagePrice, deliveryPrice, totalPrice),
      (requestPrice, couponAllTotalPrice, couponShopPrice, couponOtherPrice, asurakuFlag, drugFlag, dealFlag, membershipType, memo, operator),
      (mailPlugSentence, modifyFlag, isTaxRecalc, ordererModel, settlementModel, deliveryModel, pointModel, wrappingModel1, wrappingModel2, packageModelList, couponModelList, changeReasonModelList, paymentModelList)
      ) => new OrderModel(
      orderNumber, orderProgress, subStatusId, subStatusName, orderDatetime, shopOrderCfmDatetime, orderFixDatetime, shippingInstDatetime, shippingCmplRptDatetime, cancelDueDate,
      deliveryDate, shippingTerm, remarks, giftCheckFlag, severalSenderFlag, equalSenderFlag, isolatedIslandFlag, rakutenMemberFlag, carrierCode, emailCarrierCode,
      orderType, reserveNumber, reserveDeliveryCount, cautionDisplayType, rakutenConfirmFlag, goodsPrice, goodsTax, postagePrice, deliveryPrice, totalPrice,
      requestPrice, couponAllTotalPrice, couponShopPrice, couponOtherPrice, asurakuFlag, drugFlag, dealFlag, membershipType, memo, operator,
      mailPlugSentence, modifyFlag, isTaxRecalc, ordererModel, settlementModel, deliveryModel, pointModel, wrappingModel1, wrappingModel2, packageModelList, couponModelList, changeReasonModelList, paymentModelList
    )
  }, orderModel => (
    (orderModel.orderNumber, orderModel.orderProgress, orderModel.subStatusId, orderModel.subStatusName, orderModel.orderDatetime, orderModel.shopOrderCfmDatetime, orderModel.orderFixDatetime, orderModel.shippingInstDatetime, orderModel.shippingCmplRptDatetime, orderModel.cancelDueDate),
    (orderModel.deliveryDate, orderModel.shippingTerm, orderModel.remarks, orderModel.giftCheckFlag, orderModel.severalSenderFlag, orderModel.equalSenderFlag, orderModel.isolatedIslandFlag, orderModel.rakutenMemberFlag, orderModel.carrierCode, orderModel.emailCarrierCode),
    (orderModel.orderType, orderModel.reserveNumber, orderModel.reserveDeliveryCount, orderModel.cautionDisplayType, orderModel.rakutenConfirmFlag, orderModel.goodsPrice, orderModel.goodsTax, orderModel.postagePrice, orderModel.deliveryPrice, orderModel.totalPrice),
    (orderModel.requestPrice, orderModel.couponAllTotalPrice, orderModel.couponShopPrice, orderModel.couponOtherPrice, orderModel.asurakuFlag, orderModel.drugFlag, orderModel.dealFlag, orderModel.membershipType, orderModel.memo, orderModel.operator),
    (orderModel.mailPlugSentence, orderModel.modifyFlag, orderModel.isTaxRecalc, orderModel.OrdererModel, orderModel.SettlementModel, orderModel.DeliveryModel, orderModel.PointModel, orderModel.WrappingModel1, orderModel.WrappingModel2, orderModel.PackageModelList, orderModel.CouponModelList, orderModel.ChangeReasonModelList,
      orderModel.PaymentModelList)
  )
  )

  val searchOrderModelFirstFormat: OFormat[(Option[List[Int]], Option[List[Int]], Byte, DateTime, DateTime, Option[List[Byte]], Option[Byte], Option[String], Option[Byte], Option[Byte])] = (
    (__ \ "orderProgressList").formatNullable[List[Int]] and
      (__ \ "subStatusIdList").formatNullable[List[Int]] and
      (__ \ "dateType").format[Byte] and
      (__ \ "startDatetime").format[DateTime] and
      (__ \ "endDatetime").format[DateTime] and
      (__ \ "orderTypeList").formatNullable[List[Byte]] and
      (__ \ "settlementMethod").formatNullable[Byte] and
      (__ \ "deliveryName").formatNullable[String] and
      (__ \ "shippingDateBlankFlag").formatNullable[Byte] and
      (__ \ "shippingNumberBlankFlag").formatNullable[Byte]
    ).tupled

  val searchOrderModelSecondFormat: OFormat[(Option[Byte], Option[String], Option[Byte], Option[String], Option[Byte], Option[String], Option[Byte], Option[Byte], Option[Byte],
    Option[Byte], Option[Byte], Option[Byte], Option[PaginationRequestModel])] = (
    (__ \ "searchKeywordType").formatNullable[Byte] and
      (__ \ "searchKeyword").formatNullable[String] and
      (__ \ "mailSendType").formatNullable[Byte] and
      (__ \ "ordererMailAddress").formatNullable[String] and
      (__ \ "phoneNumberType").formatNullable[Byte] and
      (__ \ "phoneNumber").formatNullable[String] and
      (__ \ "reserveNumber").formatNullable[Byte] and
      (__ \ "purchaseSiteType").formatNullable[Byte] and
      (__ \ "asurakuFlag").formatNullable[Byte] and
      (__ \ "couponUseFlag").formatNullable[Byte] and
      (__ \ "drugFlag").formatNullable[Byte] and
      (__ \ "overseasFlag").formatNullable[Byte] and
      (__ \ "PaginationRequestModel").formatNullable[PaginationRequestModel]
    ).tupled

  implicit val searchOrderModelFormat: Format[SearchOrderRequestModel] = (
    searchOrderModelFirstFormat and searchOrderModelSecondFormat
    ).apply({
    case ((orderProgressList, subStatusIdList, dateType, startDatetime, endDatetime, orderTypeList, settlementMethod, deliveryName, shippingDateBlankFlag, shippingNumberBlankFlag),
    (searchKeywordType, searchKeyword, mailSendType, ordererMailAddress, phoneNumberType, phoneNumber, reserveNumber, purchaseSiteType, asurakuFlag, couponUseFlag, drugFlag, overseasFlag, paginationRequestModel)
      ) ⇒ new SearchOrderRequestModel(orderProgressList, subStatusIdList, dateType, startDatetime, endDatetime, orderTypeList, settlementMethod, deliveryName, shippingDateBlankFlag, shippingNumberBlankFlag,
      searchKeywordType, searchKeyword, mailSendType, ordererMailAddress, phoneNumberType, phoneNumber, reserveNumber, purchaseSiteType, asurakuFlag, couponUseFlag, drugFlag, overseasFlag, paginationRequestModel)
  }, searchOrderModel ⇒ (
    (searchOrderModel.orderProgressList, searchOrderModel.subStatusIdList, searchOrderModel.dateType, searchOrderModel.startDatetime,
      searchOrderModel.endDatetime, searchOrderModel.orderTypeList, searchOrderModel.settlementMethod, searchOrderModel.deliveryName,
      searchOrderModel.shippingDateBlankFlag, searchOrderModel.shippingNumberBlankFlag),
    (searchOrderModel.searchKeywordType, searchOrderModel.searchKeyword, searchOrderModel.mailSendType, searchOrderModel.ordererMailAddress,
      searchOrderModel.phoneNumberType, searchOrderModel.phoneNumber, searchOrderModel.reserveNumber, searchOrderModel.purchaseSiteType,
      searchOrderModel.asurakuFlag, searchOrderModel.couponUseFlag, searchOrderModel.drugFlag, searchOrderModel.overseasFlag, searchOrderModel.PaginationRequestModel)
  ))

  implicit val searchOrderResponseModelReads: Reads[SearchOrderResponseModel] = Json.reads[SearchOrderResponseModel]
  implicit val getOrderRequestModelWrites: Writes[GetOrderRequestModel] = Json.writes[GetOrderRequestModel]
  implicit val getOrderResponseModelReads: Reads[GetOrderResponseModel] = Json.reads[GetOrderResponseModel]
  implicit val confirmOrderRequestModelWrites: Writes[ConfirmOrderRequestModel] = Json.writes[ConfirmOrderRequestModel]
  implicit val confirmOrderResponseModelReads: Reads[ConfirmOrderResponseModel] = Json.reads[ConfirmOrderResponseModel]
  implicit val updateOrderShippingRequestModelWrites: Writes[UpdateShippingRequestModel] = Json.writes[UpdateShippingRequestModel]
  implicit val updateOrderShippingResponseModelReads: Reads[UpdateShippingResponseModel] = Json.reads[UpdateShippingResponseModel]
  implicit val updateOrderDeliveryRequestModelWrites: Writes[UpdateDeliveryRequestModel] = Json.writes[UpdateDeliveryRequestModel]
  implicit val updateOrderDeliveryResponseModelReads: Reads[UpdateDeliveryResponseModel] = Json.reads[UpdateDeliveryResponseModel]
  implicit val updateOrdererRequestModelWrites: Writes[UpdateOrdererRequestModel] = Json.writes[UpdateOrdererRequestModel]
  implicit val updateOrdererResponseModelReads: Reads[UpdateOrdererResponseModel] = Json.reads[UpdateOrdererResponseModel]
  implicit val updateOrderRemarksModelWrites: Writes[UpdateRemarksRequestModel] = Json.writes[UpdateRemarksRequestModel]
  implicit val updateOrderRemarksResponseModelReads: Reads[UpdateRemarksResponseModel] = Json.reads[UpdateRemarksResponseModel]
  implicit val updateSenderRequestModelWrites: Writes[UpdateSenderRequestModel] = Json.writes[UpdateSenderRequestModel]
  implicit val updateSenderResponseModelReads: Reads[UpdateSenderResponseModel] = Json.reads[UpdateSenderResponseModel]
  implicit val updateMemoRequestModelWrites: Writes[UpdateMemoRequestModel] = Json.writes[UpdateMemoRequestModel]
  implicit val updateMemoResponseModelReads: Reads[UpdateMemoResponseModel] = Json.reads[UpdateMemoResponseModel]
  implicit val getPaymentRequestModelWrites: Writes[GetPaymentRequestModel] = Json.writes[GetPaymentRequestModel]
  implicit val getPaymentResponseModelReads: Reads[GetPaymentResponseModel] = Json.reads[GetPaymentResponseModel]
  implicit val cancelOrderRequestModelWrites: Writes[CancelOrderRequestModel] = Json.writes[CancelOrderRequestModel]
  implicit val cancelOrderResponseModelReads: Reads[CancelOrderResponseModel] = Json.reads[CancelOrderResponseModel]
  implicit val getSubStatusListResponseModelReads: Reads[GetSubStatusListResponseModel] = Json.reads[GetSubStatusListResponseModel]

}
