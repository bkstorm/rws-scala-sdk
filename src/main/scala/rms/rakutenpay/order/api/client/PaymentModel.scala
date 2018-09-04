package rms.rakutenpay.order.api.client

import org.joda.time.{DateTime, LocalDate}

case class PaymentModel(
                         orderPaymentNumber: String,
                         paymentMethod: Byte,
                         paymentAmount: Option[Int] = None,
                         paymentStatus: Int,
                         paymentProcReqDatetime: Option[DateTime] = None,
                         paymentProcNgDatetime: Option[DateTime] = None,
                         paymentProcCmplDatetime: Option[DateTime] = None,
                         paymentProcCancelNgDate: Option[DateTime] = None,
                         paymentProcCancelCmplDatetime: Option[DateTime] = None,
                         paymentReqDatetime: Option[DateTime] = None,
                         paymentNgDatetime: Option[DateTime] = None,
                         paymentFixDatetime: Option[DateTime] = None,
                         paymentCancelNgDatetime: Option[DateTime] = None,
                         paymentCancelCmplDatetime: Option[DateTime] = None,
                         paymentCancelFixDatetime: Option[DateTime] = None,
                         payExpectDate: Option[LocalDate] = None,
                         minusPayExpectDate: Option[LocalDate] = None,
                         paymentOrganizationLinkageNumberList: Option[List[String]],
                         PaymentCardModel: Option[PaymentCardModel],
                         PaymentMultiModel: Option[PaymentMultiModel],
                         PaymentBankModel: Option[PaymentBankModel],
                         RefundModelList: Option[List[RefundModel]]
                       )
