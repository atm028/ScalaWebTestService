package com.tmorozov

import akka.actor.Actor
import spray.routing._
import spray.http._
import spray.json._
import DefaultJsonProtocol._
import MediaTypes._

class HelloWordActor extends Actor with MyWebService {
  def actorRefFactory = context
  def receive = runRoute(route)
}

trait MyWebService extends HttpService {
  val route = {
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            <html>
              <body>
                <h1> Hello to <i>spray-routing</i> on <i>spray-can</i></h1>
              </body>
            </html>
          }
        }
      }
    } ~
    path("version") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            val tVersion = ("version", ("major", "0.1", "minor", "0.1")).toJson
            val sVersion = tVersion.prettyPrint
            sVersion
          }
        }
      }
    }
  }
}
