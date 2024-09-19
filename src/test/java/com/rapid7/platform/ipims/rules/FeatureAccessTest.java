package com.rapid7.platform.ipims.rules;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // reset spring context after each test method
public class FeatureAccessTest {

    @LocalServerPort
    private int port;

    @Test
    public void testEvaluateTrafficViolation() {
        RestAssured.port = port;
        given()
                .body("""
                    {
                        "Org Products": [
                            {
                              "Organization Id": "org1",
                              "Product Codes": [
                                "ICS", "OPS", "IVM"
                              ]
                            },
                            {
                              "Organization Id": "org2",
                              "Product Codes": [
                                "ICS", "OPS"
                              ]
                            },
                            {
                              "Organization Id": "org3",
                              "Product Codes": [
                                "ICS", "IVM"
                              ]
                            }
                          ],
                          "Org Feature Permissions": [
                            {
                              "Organization Id": "org1",
                              "Feature Permissions": [
                                  {
                                    "Feature": "IVM_EXECUTIVE_VIEW",
                                    "Permission": "ADMINISTER"
                                  },
                                  {
                                    "Feature": "ICS_EXECUTIVE_VIEW",
                                    "Permission": "ADMINISTER"
                                  }
                              ]
                            },
                            {
                              "Organization Id": "org2",
                              "Feature Permissions": [
                                  {
                                    "Feature": "IVM_EXECUTIVE_VIEW",
                                    "Permission": "ADMINISTER"
                                  }
                              ]
                            },
                            {
                              "Organization Id": "org3",
                              "Feature Permissions": [
                                  {
                                    "Feature": "ICS_EXECUTIVE_VIEW",
                                    "Permission": "ADMINISTER"
                                  }
                              ]
                            }
                          ]
                    }""")
                .contentType(ContentType.JSON)
                .when()
                .post("/FeatureAccess")
                .then()
                .statusCode(200)
                .body("'Org with required products'", hasItems("org1", "org3"));
    }
}
