package rms.rakutenpay.order.api.client

case class PaginationRequestModel(
                                   requestRecordsAmount: Short = 30,
                                   requestPage: Int = 1,
                                   SortModelList: Option[List[SortModel]] = None
                                 )
