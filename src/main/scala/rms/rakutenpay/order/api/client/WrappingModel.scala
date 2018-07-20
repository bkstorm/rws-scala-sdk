package rms.rakutenpay.order.api.client

case class WrappingModel(
                          title: Byte,
                          name: String,
                          price: Option[Int],
                          includeTaxFlag: Byte,
                          deleteWrappingFlag: Byte
                        )
