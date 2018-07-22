/*
 * Copyright (C) 2009-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package play.libs.ws.ahc

import java.nio.charset.StandardCharsets
import java.util.Base64

import play.libs.ws._

case class HeaderAppendingFilter(serviceSecret: String, licenseKey: String) extends WSRequestFilter {
  override def apply(executor: WSRequestExecutor): WSRequestExecutor = (request: StandaloneWSRequest) =>
    executor.apply(request
      .addHeader("Authorization", s"ESA ${Base64.getEncoder.encodeToString(s"${serviceSecret}:${licenseKey}".getBytes(StandardCharsets.UTF_8))}")
      .addHeader("Content-Type", "application/json; charset=utf-8")
    )
}
