package rms.rakutenpay.order.api.client

case class DeliveryModel(
                          deliveryName: String,
                          deliveryClass: Option[Byte]
                        )
