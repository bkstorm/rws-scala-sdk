package rms.rakutenpay.order.api.client

import org.joda.time.LocalDate

case class ShippingModel(
                          shippingDetailId: Int,
                          shippingNumber: Option[String],
                          deliveryCompany: Option[String],
                          deliveryCompanyName: Option[String],
                          shippingDate: Option[LocalDate]
                        )
