package rms.rakutenpay.order.api.client

case class SearchOrderResponseModel(
                                     MessageModelList: List[MessageModel],
                                     orderNumberList: Option[List[String]],
                                     PaginationResponseModel: Option[PaginationResponseModel]
                                   ) extends BaseResponseModel
