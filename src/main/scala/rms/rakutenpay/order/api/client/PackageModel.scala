package rms.rakutenpay.order.api.client

case class PackageModel(
                         basketId: Int,
                         postagePrice: Int,
                         deliveryPrice: Int,
                         goodsTax: Int,
                         goodsPrice: Long,
                         totalPrice: Long,
                         noshi: Option[String],
                         packageDeleteFlag: Byte,
                         SenderModel: SenderModel,
                         ItemModelList: List[ItemModel],
                         ShippingModelList: Option[ShippingModel],
                         DeliveryCvsModel: Option[DeliveryCvsModel]
                       )
