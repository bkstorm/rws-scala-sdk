package rms.rakutenpay.order.api.client

case class MessageModel(
                         messageType: String,
                         messageCode: String,
                         message: String,
                         orderNumber: Option[String] = None,
                         dataNumber: Option[Int] = None,
                         shippingDetailId: Option[Int] = None
                       )
