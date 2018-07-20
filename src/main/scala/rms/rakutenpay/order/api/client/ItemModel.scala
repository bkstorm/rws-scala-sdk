package rms.rakutenpay.order.api.client

case class ItemModel(
                      itemDetailId: Int,
                      itemName: String,
                      itemId: Int,
                      itemNumber: Option[String],
                      manageNumber: String,
                      price: Int,
                      units: Int,
                      includePostageFlag: Byte,
                      includeTaxFlag: Byte,
                      includeCashOnDeliveryPostageFlag: Byte,
                      selectedChoice: Option[String],
                      pointRate: Int,
                      inventoryType: Byte,
                      delvdateInfo: Option[String],
                      restoreInventoryFlag: Byte,
                      deleteItemFlag: Byte
                    )
