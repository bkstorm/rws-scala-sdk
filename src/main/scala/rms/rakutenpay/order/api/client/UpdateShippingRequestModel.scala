package rms.rakutenpay.order.api.client

case class UpdateShippingRequestModel(orderNumber: String, BasketidModelList: List[BasketidModel]) extends BaseRequestModel
