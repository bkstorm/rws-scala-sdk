package rms.rakutenpay.order.api.client

import org.joda.time.DateTime

class SearchOrderRequestModel(
                               val orderProgressList: Option[List[Int]] = None,
                               val subStatusIdList: Option[List[Int]] = None,
                               val dateType: Byte,
                               val startDatetime: DateTime,
                               val endDatetime: DateTime,
                               val orderTypeList: Option[List[Byte]] = None,
                               val settlementMethod: Option[Byte] = None,
                               val deliveryName: Option[String] = None,
                               val shippingDateBlankFlag: Option[Byte] = None,
                               val shippingNumberBlankFlag: Option[Byte] = None,

                               val searchKeywordType: Option[Byte] = None,
                               val searchKeyword: Option[String] = None,
                               val mailSendType: Option[Byte] = None,
                               val ordererMailAddress: Option[String] = None,
                               val phoneNumberType: Option[Byte] = None,
                               val phoneNumber: Option[String] = None,
                               val reserveNumber: Option[Byte] = None,
                               val purchaseSiteType: Option[Byte] = None,
                               val asurakuFlag: Option[Byte] = None,
                               val couponUseFlag: Option[Byte] = None,
                               val drugFlag: Option[Byte] = None,
                               val overseasFlag: Option[Byte] = None,
                               val PaginationRequestModel: Option[PaginationRequestModel] = None
                             ) extends BaseRequestModel
