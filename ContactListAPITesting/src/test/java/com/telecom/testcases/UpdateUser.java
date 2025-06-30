package com.telecom.testcases;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class UpdateUser {

    @Test
    public void updateUser() {

        String req_URL = "https://thinking-tester-contact-list.herokuapp.com/users/me";

        String requestBody = "{\n" +
                "  \"firstName\": \"Sandhya\",\n" +
                "  \"lastName\": \"Nanubala\",\n" +
                "  \"email\": \"sandhya@it.com\",\n" +
                "  \"password\": \"Suruchi@123\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + AddNewUser.TokenGenerated)  // Use token from Add User
                .body(requestBody)
            .when()
                .patch(req_URL);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

        String statusLine = response.getStatusLine();
        Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

        response.then().log().all();
    }
}

