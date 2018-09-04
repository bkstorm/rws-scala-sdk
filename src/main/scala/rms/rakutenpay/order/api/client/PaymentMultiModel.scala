package rms.rakutenpay.order.api.client

import org.joda.time.DateTime

case class PaymentMultiModel(
                              eventDatetime: Option[DateTime] = None,
                              rcvgNumber: Option[String] = None,
                              payInfoUrl: Option[String] = None
                            )
