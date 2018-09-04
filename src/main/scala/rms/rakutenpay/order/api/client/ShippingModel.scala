package rms.rakutenpay.order.api.client

import org.joda.time.LocalDate

case class ShippingModel(
                          shippingDetailId: Option[Int] = None,
                          shippingNumber: Option[String] = None,
                          deliveryCompany: Option[String] = None,
                          deliveryCompanyName: Option[String] = None,
                          shippingDate: Option[LocalDate] = None
                        )
