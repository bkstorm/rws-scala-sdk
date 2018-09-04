package rms.rakutenpay.order.api.client

case class ConfirmOrderRequestModel(orderNumberList: List[String]) extends BaseRequestModel
