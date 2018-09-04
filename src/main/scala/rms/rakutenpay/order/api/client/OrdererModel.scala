package rms.rakutenpay.order.api.client

case class OrdererModel(
                         zipCode1: String,
                         zipCode2: String,
                         prefecture: String,
                         city: String,
                         subAddress: String,
                         familyName: String,
                         firstName: Option[String] = None,
                         familyNameKana: Option[String] = None,
                         firstNameKana: Option[String] = None,
                         phoneNumber1: String,
                         phoneNumber2: String,
                         phoneNumber3: String,
                         emailAddress: String,
                         sex: Option[String] = None,
                         birthYear: Option[Int] = None,
                         birthMonth: Option[Byte] = None,
                         birthDay: Option[Byte] = None
                       )