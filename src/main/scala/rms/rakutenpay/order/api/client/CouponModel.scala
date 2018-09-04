package rms.rakutenpay.order.api.client

import org.joda.time.LocalDate

case class CouponModel(
                        couponCode: String,
                        itemId: Int,
                        couponName: Option[String] = None,
                        couponSummary: Option[String] = None,
                        couponCapital: Option[String] = None,
                        expiryDate: Option[LocalDate] = None,
                        couponPrice: Int,
                        couponUnit: Int,
                        couponTotalPrice: Option[Long] = None
                      )
