package com.telecom.testcases;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class UpdatePartialContact {

	@Test
	public void updatePartialContact() {

		String req_URL = "https://thinking-tester-contact-list.herokuapp.com/contacts/"
				+ GetContactList.FirstContactID;

		String requestBody = "{\n" + "  \"firstName\": \"Anna\"\n" + "}";

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + LoginUser.LoginToken) // Token from Login
				.body(requestBody).when().patch(req_URL);

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();
		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		String firstName = response.jsonPath().getString("firstName");
		Assert.assertEquals(firstName, "Anna", "Expected updated first name to be 'Anna'");

		response.then().log().all();
	}
}
