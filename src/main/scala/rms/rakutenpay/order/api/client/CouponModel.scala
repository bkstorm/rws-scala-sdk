package rms.rakutenpay.order.api.client

import org.joda.time.LocalDate

case class CouponModel(
                        couponCode: String,
                        itemId: Int,
                        couponName: String,
                        couponSummary: String,
                        couponCapital: String,
                        expiryDate: LocalDate,
                        couponPrice: Int,
                        couponUnit: Int,
                        couponTotalPrice: Long
                      )
