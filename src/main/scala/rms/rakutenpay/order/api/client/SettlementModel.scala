package rms.rakutenpay.order.api.client

case class SettlementModel(
                            settlementMethod: String,
                            cardName: Option[String],
                            cardNumber: Option[String],
                            cardOwner: Option[String],
                            cardYM: Option[String],
                            cardPayType: Option[Byte],
                            cardInstallmentDesc: Option[String]
                          )
