package rms.rakutenpay.order.api.client

case class GetOrderResponseModel(MessageModelList: List[MessageModel], OrderModelList: Option[List[OrderModel]] = None) extends BaseResponseModel