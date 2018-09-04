package rms.rakutenpay.order.api.client

case class UpdateRemarksRequestModel(
                                         orderNumber: String,
                                         giftCheck: Byte,
                                         remarks: String
                                         ) extends BaseRequestModel
