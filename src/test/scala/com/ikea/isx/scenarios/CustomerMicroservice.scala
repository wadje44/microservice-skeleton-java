package com.ikea.isx.scenarios

import com.ikea.isx.util._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object CustomerMicroservice {

  val customerMicroservice = scenario("Fetching Customer with customerId")
    .exec(http("Status Check")
      .get(Environment.endPoint+"/one")
      .check(status.is(200)));
}
