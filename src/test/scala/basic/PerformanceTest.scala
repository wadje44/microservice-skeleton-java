//package basic
//
//import io.gatling.core.Predef._
//import io.gatling.http.Predef._
//
//class PerformanceTest extends Simulation {
//
//  val httpConf = http
//    .baseUrl("http://localhost:3000")
//    .header("Content-Type", "application/json")
//    .warmUp("http://www.google.com")
//    .disableWarmUp
//    .maxConnectionsPerHost(10000)
//    .maxConnectionsPerHostLikeFirefox
//
//  val scn = scenario("Hello")
//    .exec(http("Hello request")
//      .post("/data-ingester/publish")
//      .body(StringBody(
//        """
//          {
//            "id": 1,
//            "content": "test-gatling"
//          }
//        """))
//      .check(status.is(200)));
//
//  setUp(
//    scn.inject(atOnceUsers(100))
//  ).protocols(httpConf)
//
//  //  setUp(
//  //    scn.inject(
//  //      nothingFor(4 seconds), // 1
//  //      atOnceUsers(10), // 2
//  //      rampUsers(10) during (5 seconds), // 3
//  //      constantUsersPerSec(20) during (15 seconds), // 4
//  //      constantUsersPerSec(20) during (15 seconds) randomized, // 5
//  //      rampUsersPerSec(10) to 20 during (10 minutes), // 6
//  //      rampUsersPerSec(10) to 20 during (10 minutes) randomized, // 7
//  //      heavisideUsers(1000) during (20 seconds) // 10
//  //    ).protocols(httpConf)
//  //  )
//}