package rms.rakutenpay.order.api.client

object PaymentMethod extends Enumeration {
  val CREDIT_CARD = Value(1)
  val CASH_ON_DELIVERY = Value(2)
  val POST_PAID = Value(3)
  val SHOPPING_CREDIT = Value(4) // Shopping Credit / Loan
  val AUTO_LOAN = Value(5)
  val LEASE = Value(6)
  val INVOICE_PAYMENT = Value(7)
  val POINT = Value(8)
  val BANK_TRANSFER = Value(9)
  val APPLE_PAY = Value(12)
  val SEVEN_ELEVEN = Value(13) // 7-ELEVEN
  val ETC = Value(14) // Lawson, Post office, ATM etc
}
