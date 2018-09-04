package rms.rakutenpay.order.api.client

import org.joda.time.{DateTime, LocalDate}

case class RefundModel(
                        paymentAmount: Int,
                        paymentStatus: Int,
                        paymentProcCmplDatetime: Option[DateTime] = None,
                        paymentReqDatetime: Option[DateTime] = None,
                        paymentFixDatetime: Option[DateTime] = None,
                        payExpectDate: Option[LocalDate] = None
                      )
