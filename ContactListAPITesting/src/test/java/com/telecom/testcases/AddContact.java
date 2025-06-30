package com.telecom.testcases;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class AddContact {

    @Test
    public void addContact() {

        String req_URL = "https://thinking-tester-contact-list.herokuapp.com/contacts";

        String requestBody = "{\n" +
                "  \"firstName\": \"John\",\n" +
                "  \"lastName\": \"Doe\",\n" +
                "  \"birthdate\": \"1970-01-01\",\n" +
                "  \"email\": \"jdoe@fake.com\",\n" +
                "  \"phone\": \"8005555555\",\n" +
                "  \"street1\": \"1 Main St.\",\n" +
                "  \"street2\": \"Apartment A\",\n" +
                "  \"city\": \"Anytown\",\n" +
                "  \"stateProvince\": \"KS\",\n" +
                "  \"postalCode\": \"12345\",\n" +
                "  \"country\": \"USA\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + LoginUser.LoginToken)  // Token from Login User test
                .body(requestBody)
            .when()
                .post(req_URL);

        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 Created");

        String statusLine = response.getStatusLine();
        Assert.assertTrue(statusLine.contains("Created"), "Expected status line to contain 'Created'");

        response.then().log().all();
    }
}

