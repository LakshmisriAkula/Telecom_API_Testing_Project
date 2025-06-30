package com.telecom.testcases;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class GetSingleContact {

	@Test
	public void getContactById() {

		String req_URL = "https://thinking-tester-contact-list.herokuapp.com/contacts/" + GetContactList.FirstContactID;

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + LoginUser.LoginToken) // Use token from Login
				.when().get(req_URL);

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();
		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		response.then().log().all();
	}
}
