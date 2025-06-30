package com.telecom.testcases;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class GetContactList {

	public static String FirstContactID; // Save first contact ID for next test cases (like Get Contact, Update Contact)

	@Test
	public void getContactList() {

		String req_URL = "https://thinking-tester-contact-list.herokuapp.com/contacts";

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + LoginUser.LoginToken) // Token from Login User
				.when().get(req_URL);

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();
		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		FirstContactID = response.jsonPath().getString("[0]._id");

		System.out.println("First Contact ID saved for next tests: " + FirstContactID);

		response.then().log().all();

	}
}
