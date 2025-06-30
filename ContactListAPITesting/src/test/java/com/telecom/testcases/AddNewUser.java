package com.telecom.testcases;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class AddNewUser {

	public static String TokenGenerated;
	@Test
	public void addUser() {

		String req_URL = "https://thinking-tester-contact-list.herokuapp.com/users";

		String requestBody = "{\n" + "  \"firstName\": \"Navya\",\n" + "  \"lastName\": \"Thota\",\n"
				+ "  \"email\": \"navya@it.com\",\n" + //Change email every time you re-run
				"  \"password\": \"Navya@123\"\n" + "}";

		Response response = given().header("Content-Type", "application/json").body(requestBody).when().post(req_URL);

		Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 Created");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("Created"), "Expected status line to contain 'Created'");
		
		TokenGenerated = response.jsonPath().getString("token");
		
		response.then().log().all();

	}

}
