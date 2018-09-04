package rms.rakutenpay.order.api.client

import org.joda.time.{DateTime, LocalDate}

class OrderModel(
                  val orderNumber: String,
                  val orderProgress: Int,
                  val subStatusId: Option[Int] = None,
                  val subStatusName: Option[String] = None,
                  val orderDatetime: DateTime,
                  val shopOrderCfmDatetime: Option[DateTime] = None,
                  val orderFixDatetime: Option[DateTime] = None,
                  val shippingInstDatetime: Option[DateTime] = None,
                  val shippingCmplRptDatetime: Option[DateTime] = None,
                  val cancelDueDate: Option[LocalDate] = None,
                  val deliveryDate: Option[LocalDate] = None,

                  val shippingTerm: Option[Int] = None,
                  val remarks: Option[String] = None,
                  val giftCheckFlag: Byte,
                  val severalSenderFlag: Byte,
                  val equalSenderFlag: Byte,
                  val isolatedIslandFlag: Byte,
                  val rakutenMemberFlag: Byte,
                  val carrierCode: Byte,
                  val emailCarrierCode: Byte,

                  val orderType: Byte,
                  val reserveNumber: Option[String] = None,
                  val reserveDeliveryCount: Option[Int] = None,
                  val cautionDisplayType: Byte,
                  val rakutenConfirmFlag: Byte,
                  val goodsPrice: Long,
                  val goodsTax: Int,
                  val postagePrice: Int,
                  val deliveryPrice: Int,
                  val totalPrice: Long,

                  val requestPrice: Long,
                  val couponAllTotalPrice: Long,
                  val couponShopPrice: Long,
                  val couponOtherPrice: Long,
                  val asurakuFlag: Byte,
                  val drugFlag: Byte,
                  val dealFlag: Byte,
                  val membershipType: Byte,
                  val memo: Option[String] = None,
                  val operator: Option[String] = None,

                  val mailPlugSentence: Option[String] = None,
                  val modifyFlag: Byte,
                  val isTaxRecalc: Byte,
                  val OrdererModel: OrdererModel,
                  val SettlementModel: Option[SettlementModel] = None,
                  val DeliveryModel: DeliveryModel,
                  val PointModel: Option[PointModel] = None,
                  val WrappingModel1: Option[WrappingModel] = None,
                  val WrappingModel2: Option[WrappingModel] = None,
                  val PackageModelList: List[PackageModel],

                  val CouponModelList: Option[List[CouponModel]] = None,
                  val ChangeReasonModelList: Option[List[ChangeReasonModel]] = None,
                  val PaymentModelList: Option[List[PaymentModel]] = None
                )
