package rms.rakutenpay.order.api.client

case class GetPaymentResponseModel(
                                    MessageModelList: List[MessageModel],
                                    OrderModel: Option[OrderModel]
                                  ) extends BaseResponseModel
