package com.telecom.testcases;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class UpdateFullContact {

    @Test
    public void updateFullContact() {

        String req_URL = "https://thinking-tester-contact-list.herokuapp.com/contacts/" + GetContactList.FirstContactID;

        String requestBody = "{\n" +
                "  \"firstName\": \"Amy\",\n" +
                "  \"lastName\": \"Miller\",\n" +
                "  \"birthdate\": \"1992-02-02\",\n" +
                "  \"email\": \"amiller@fake.com\",\n" +
                "  \"phone\": \"8005554242\",\n" +
                "  \"street1\": \"13 School St.\",\n" +
                "  \"street2\": \"Apt. 5\",\n" +
                "  \"city\": \"Washington\",\n" +
                "  \"stateProvince\": \"QC\",\n" +
                "  \"postalCode\": \"A1A1A1\",\n" +
                "  \"country\": \"Canada\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + LoginUser.LoginToken)  // Use token from Login
                .body(requestBody)
            .when()
                .put(req_URL);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

        String statusLine = response.getStatusLine();
        Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

        String email = response.jsonPath().getString("email");
        Assert.assertEquals(email, "amiller@fake.com", "Expected updated email to match");

        response.then().log().all();
    }
}

