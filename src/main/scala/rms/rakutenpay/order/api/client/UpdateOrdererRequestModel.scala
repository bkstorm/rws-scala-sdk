package rms.rakutenpay.order.api.client

case class UpdateOrdererRequestModel(
                                      orderNumber: String,
                                      OrdererModel: OrdererModel
                                    ) extends BaseRequestModel
