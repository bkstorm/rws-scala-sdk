package rms.rakutenpay.order.api.client

case class UpdateSenderRequestModel(
                                   orderNumber: String,
                                   reductionReason: Option[Byte] = None,
                                   taxRecalcFlag: Option[Byte] = None,
                                   WrappingModel1: Option[WrappingModel] = None,
                                   WrappingModel2: Option[WrappingModel] = None,
                                   PackageModelList: List[PackageModel],
                                   CouponModelList: Option[List[CouponModel]] = None
                                   ) extends BaseRequestModel
