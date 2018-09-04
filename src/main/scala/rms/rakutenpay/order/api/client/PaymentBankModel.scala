package rms.rakutenpay.order.api.client

import org.joda.time.LocalDate

case class PaymentBankModel (
                              transferDate: Option[LocalDate] = None,
                              transferNameKana: Option[String] = None
                            )
