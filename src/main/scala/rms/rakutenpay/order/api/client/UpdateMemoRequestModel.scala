package rms.rakutenpay.order.api.client

import java.time.LocalDate

case class UpdateMemoRequestModel(
                                   orderNumber: String,
                                   subStatusId: Option[Int] = None,
                                   deliveryClass: Option[Byte] = None,
                                   deliveryDate: Option[LocalDate] = None,
                                   shippingTerm: Option[Byte] = None,
                                   memo: Option[String] = None,
                                   operator: Option[String] = None,
                                   mailPlugSentence: Option[String] = None
                                 ) extends BaseRequestModel
