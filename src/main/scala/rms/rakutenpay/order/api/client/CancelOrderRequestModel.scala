package rms.rakutenpay.order.api.client

case class CancelOrderRequestModel(orderNumber: String, inventoryRestoreType: Byte = 0, changeReasonDetailApply: Byte = 1) extends BaseRequestModel
