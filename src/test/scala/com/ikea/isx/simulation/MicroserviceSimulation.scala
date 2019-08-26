package com.ikea.isx.simulation

import com.ikea.isx.util._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import com.ikea.isx.scenarios.CustomerMicroservice

class MicroserviceSimulation extends Simulation {

  val httpConf = http.baseUrl(Environment.baseURL)
    .headers(Headers.commonHeader)

  val microserviceScenarios = List(

    CustomerMicroservice.customerMicroservice.inject(
      atOnceUsers(1),
      //rampUsers(100) over(1 seconds),
      //constantUsersPerSec(1000) during(15 seconds)
      rampUsersPerSec(1) to 100 during(30 seconds) // 6
      //rampUsersPerSec(10) to 20 during(10 minutes) randomized, // 7
      //splitUsers(1000) into(rampUsers(10) over(10 seconds)) separatedBy(10 seconds), // 8
      //splitUsers(1000) into(rampUsers(10) over(10 seconds)) separatedBy atOnceUsers(30), // 9
      //heavisideUsers(1000) over(20 seconds) // 10
    ),

//    PostCustomer.postCustomer.inject(atOnceUsers(Environemnt.users.toInt))
//      .throttle(reachRps(600) in (20 seconds), holdFor(60 seconds))
  )

  setUp(microserviceScenarios)
    .protocols(httpConf)
    .maxDuration(1 minutes)
//    .assertions(
//      global.responseTime.max.lte(Environment.maxResponseTime.toInt)
//    )
}