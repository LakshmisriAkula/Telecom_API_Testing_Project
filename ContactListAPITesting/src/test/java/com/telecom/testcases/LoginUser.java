package com.telecom.testcases;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class LoginUser {

	public static String LoginToken; // To store login token for further tests like Add Contact etc.

	@Test
	public void loginUser() {

		String req_URL = "https://thinking-tester-contact-list.herokuapp.com/users/login";

		String requestBody = "{\n" + "  \"email\": \"suruchi@it.com\",\n" + "  \"password\": \"Suruchi@123\"\n" + "}";

		Response response = given().header("Content-Type", "application/json").body(requestBody).when().post(req_URL);

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();
		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		LoginToken = response.jsonPath().getString("token");
		System.out.println("Login Token Generated: " + LoginToken);

		response.then().log().all();
	}
}
