/*
 * Copyright (C) 2009-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package rms.rakutenay.order.api.client

import akka.stream.Materializer
import org.specs2.execute.Result
import play.api.libs.ws.ahc.{AhcWSClientConfig, AhcWSClientConfigFactory, StandaloneAhcWSClient}

trait StandaloneWSClientSupport {

  def materializer: Materializer

  def withClient(config: AhcWSClientConfig = AhcWSClientConfigFactory.forConfig())(block: StandaloneAhcWSClient => Result): Result = {
    val client = StandaloneAhcWSClient(config)(materializer)
    try {
      block(client)
    } finally {
      client.close()
    }
  }
}
