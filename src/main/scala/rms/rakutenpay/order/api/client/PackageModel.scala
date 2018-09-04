package rms.rakutenpay.order.api.client

case class PackageModel(
                         basketId: Int,
                         postagePrice: Option[Int] = None,
                         deliveryPrice: Option[Int] = None,
                         goodsTax: Option[Int] = None,
                         goodsPrice: Option[Long] = None,
                         totalPrice: Option[Long] = None,
                         noshi: Option[String] = None,
                         packageDeleteFlag: Option[Byte] = None,
                         SenderModel: SenderModel,
                         ItemModelList: List[ItemModel],
                         ShippingModelList: Option[List[ShippingModel]] = None,
                         DeliveryCvsModel: Option[DeliveryCvsModel] = None
                       )
