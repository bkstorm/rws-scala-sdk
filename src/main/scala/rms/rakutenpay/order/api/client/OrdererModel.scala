package rms.rakutenpay.order.api.client

case class OrdererModel(
                         zipCode1: String,
                         zipCode2: String,
                         prefecture: String,
                         city: String,
                         subAddress: String,
                         familyName: String,
                         firstName: String,
                         familyNameKana: String,
                         firstNameKana: String,
                         phoneNumber1: String,
                         phoneNumber2: String,
                         phoneNumber3: String,
                         emailAddress: String,
                         sex: Option[String],
                         birthYear: Option[Int],
                         birthMonth: Option[Byte],
                         birthDay: Option[Byte]
                       )