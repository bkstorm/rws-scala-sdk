package rms.rakutenpay.order.api.client

import org.joda.time.DateTime

case class ChangeReasonModel(
                              changeId: Int,
                              changeType: Option[Byte],
                              changeTypeDetail: Option[Byte],
                              changeReason: Option[Byte],
                              changeReasonDetail: Option[Byte],
                              changeApplyDatetime: Option[DateTime],
                              changeFixDatetime: Option[DateTime],
                              changeCmplDatetime: Option[DateTime]
                            )
