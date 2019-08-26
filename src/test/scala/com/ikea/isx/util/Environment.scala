/**
  * Created by Chetan Patil. 20/08/2019
  */

package com.ikea.isx.util

import java.util

object Environment {
  val baseURL = scala.util.Properties.envOrElse("baseURL", "http://localhost:8080")
  val endPoint = scala.util.Properties.envOrElse("parentEndpoint", "/catalog")
  val users = scala.util.Properties.envOrElse("numberOfUsers", "1000")
  val maxResponseTime = scala.util.Properties.envOrElse("maxResponseTime", "2000") // in milliseconds

}
