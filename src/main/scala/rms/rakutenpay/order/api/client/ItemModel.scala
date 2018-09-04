package rms.rakutenpay.order.api.client

case class ItemModel(
                      itemDetailId: Int,
                      itemName: String,
                      itemId: Option[Int] = None,
                      itemNumber: Option[String] = None,
                      manageNumber: String,
                      price: Int,
                      units: Int,
                      includePostageFlag: Byte,
                      includeTaxFlag: Byte,
                      includeCashOnDeliveryPostageFlag: Byte,
                      selectedChoice: Option[String] = None,
                      pointRate: Option[Int] = None,
                      inventoryType: Byte,
                      delvdateInfo: Option[String],
                      restoreInventoryFlag: Option[Byte] = None,
                      deleteItemFlag: Option[Byte] = None
                    )
