package rms.rakutenpay.order.api.client

case class SenderModel(
                        zipCode1: String,
                        zipCode2: String,
                        prefecture: String,
                        city: String,
                        subAddress: String,
                        familyName: String,
                        firstName: Option[String],
                        familyNameKana: Option[String],
                        firstNameKana: Option[String],
                        phoneNumber1: String,
                        phoneNumber2: String,
                        phoneNumber3: String
                      )
