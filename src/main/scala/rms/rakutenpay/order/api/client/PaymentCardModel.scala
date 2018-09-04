package rms.rakutenpay.order.api.client

case class PaymentCardModel(
                             cardName: String,
                             cardYm: String,
                             cardOwner: String,
                             cardNumber: String,
                             cardPayType: Byte,
                             cardInstallmentDesc: Int

                           )
