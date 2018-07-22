package rms.rakutenpay.order.api.client

import java.nio.charset.StandardCharsets
import java.util.Base64

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.JsonBodyReadables._
import play.api.libs.ws.JsonBodyWritables._
import play.api.libs.ws.{StandaloneWSClient, WSRequestFilter}
import play.api.libs.ws.ahc.{StandaloneAhcWSClient, StandaloneAhcWSRequest}
import play.libs.ws.ahc.HeaderAppendingFilter

import scala.concurrent.Future

class RakutenPayOrderApiService(serviceSecret: String, licenseKey: String) {

  val requestFilter: WSRequestFilter = HeaderAppendingFilter(serviceSecret, licenseKey)
  implicit val system = ActorSystem()
  system.registerOnTermination {
    System.exit(0)
  }
  implicit val materializer = ActorMaterializer()
  val wsClient = StandaloneAhcWSClient()

  def withClient[T](block: StandaloneWSClient => Future[T]): Future[T] = {
    block(wsClient)
      .andThen { case _ => wsClient.close() }
      .andThen { case _ => system.terminate() }
  }

  def getOrder(getOrderRequestModel: GetOrderRequestModel): Future[GetOrderResponseModel] = withClient { client =>
    //    wsClient => wsClient.url("https://api.rms.rakuten.co.jp/es/2.0/order/getOrder/").post
    val req = client
      .url("https://api.rms.rakuten.co.jp/es/2.0/order/getOrder/")
      .withRequestFilter(requestFilter)
      .withMethod("POST")
      .withBody(Json.toJson(getOrderRequestModel))
      .asInstanceOf[StandaloneAhcWSRequest]

    for {
      response <- req.execute()
      getOrderResponseModel <- Json.fromJson[GetOrderRequestModel](response.body[JsValue])
    } yield getOrderResponseModel
  }
}
