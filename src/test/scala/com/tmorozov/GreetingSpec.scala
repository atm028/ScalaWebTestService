package com.tmorozov

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._


class MyWebServiceSpec extends Specification with Specs2RouteTest with MyWebService {
  def actorRefFactory = system

  "MyWebService" should {
    "return a greeting for GET request to the root path" in {
      Get() ~> route ~> check {
        responseAs[String] must contain("Hello")
      }
    }

    "leave GET request to the other paths unhandled" in {
      Get("/fake") ~> route ~> check {
        handled must beFalse
      }
    }

    "return MethodNotAllowed error for PUT request to the root path" in {
      Put() ~> sealRoute(route) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }

    "return a version for GET request to path /version" in {
      Get("/version") ~> route ~> check {
        responseAs[String] must contain("version")
      }
    }
  }
}
