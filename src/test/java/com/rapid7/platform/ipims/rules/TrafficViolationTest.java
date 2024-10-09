// /*
//  * Copyright 2021 Red Hat, Inc. and/or its affiliates.
//  *
//  * Licensed under the Apache License, Version 2.0 (the "License");
//  * you may not use this file except in compliance with the License.
//  * You may obtain a copy of the License at
//  *
//  *       http://www.apache.org/licenses/LICENSE-2.0
//  *
//  * Unless required by applicable law or agreed to in writing, software
//  * distributed under the License is distributed on an "AS IS" BASIS,
//  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  * See the License for the specific language governing permissions and
//  * limitations under the License.
//  */
// package com.rapid7.platform.ipims.rules;

// import static org.hamcrest.Matchers.is;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.server.LocalServerPort;
// import org.springframework.test.annotation.DirtiesContext;

// import io.restassured.RestAssured;
// import static io.restassured.RestAssured.given;
// import io.restassured.http.ContentType;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoApplication.class)
// @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // reset spring context after each test method
// public class TrafficViolationTest {

//     @LocalServerPort
//     private int port;

//     @Test
//     public void testEvaluateTrafficViolation() {
//         RestAssured.port = port;
//         given()
//                 .body("""
//                       {
//                           "Driver": {
//                               "Points": 2
//                           },
//                           "Violation": {
//                               "Type": "speed",
//                               "Actual Speed": 120,
//                               "Speed Limit": 100
//                           }
//                       }""")
//                 .contentType(ContentType.JSON)
//                 .when()
//                 .post("/TrafficViolation")
//                 .then()
//                 .statusCode(200)
//                 .body("'Should the driver be suspended?'", is("No"));
//     }
// }
