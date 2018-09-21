package rms.rakutenpay.order.api.client

object OrderProgress extends Enumeration {
  val AWAITING_ORDER_CONFIRMATION = Value(100)
  val PROCESSING = Value(200)
  val AWAITING_SHIPMENT = Value(300)
  val AWAITING_CHANGE_CONFIRMATION = Value(400)
  val SHIPPED = Value(500)
  val PAYMENT_IS_IN_PROGRESS = Value(600)
  val PAYMENT_HAS_COMPLETED = Value(700)
  val AWAITING_CANCEL_CONFIRMATION = Value(800)
  val CANCELLATION_CONFIRMED = Value(900)
}
