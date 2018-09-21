package rms.rakutenpay.order.api.client

case class GetSubStatusListResponseModel(MessageModelList: List[MessageModel], StatusModelList: Option[List[StatusModel]]) extends BaseResponseModel
