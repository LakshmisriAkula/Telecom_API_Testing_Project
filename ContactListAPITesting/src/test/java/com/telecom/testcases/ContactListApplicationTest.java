package com.telecom.testcases;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ContactListApplicationTest {

	String userToken;
	String loginToken;
	String userEmail;
	String firstContactID;
	String updatedPwd;
	String updatedEmail;

	@Test(priority = 1)
	public void addNewUser() {

		userEmail = "lakshmisri" + System.currentTimeMillis() + "@gmail.com";

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("firstName", "Lakshmisri");
		data.put("lastName", "Akula");
		data.put("email", userEmail);
		data.put("password", "Heroku@123");

		Response response = given().header("Content-Type", "application/json").body(data).when()
				.post("https://thinking-tester-contact-list.herokuapp.com/users");

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 Created");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("Created"), "Expected status line to contain 'Created'");

		userToken = response.jsonPath().getString("token");

	}

	@Test(priority = 2, dependsOnMethods = "addNewUser")
	public void getUserProfile() {

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + userToken).when()
				.get("https://thinking-tester-contact-list.herokuapp.com/users/me");

		Assert.assertEquals(response.statusCode(), 200);

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

	}

	@Test(priority = 3)
	public void updateUser() {

		updatedEmail = "yamuna" + System.currentTimeMillis() + "@gmail.com";
		updatedPwd = "Heroku@1234";

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("firstName", "Yamuna");
		data.put("lastName", "Anisetti");
		data.put("email", updatedEmail);
		data.put("password", updatedPwd);

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + userToken).body(data).when()
				.patch("https://thinking-tester-contact-list.herokuapp.com/users/me");

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		response.then().log().all();
	}

	@Test(priority = 4)
	public void loginUser() {

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("email", updatedEmail);
		data.put("password", updatedPwd);

		Response response = given().header("Content-Type", "application/json").body(data).when()
				.post("https://thinking-tester-contact-list.herokuapp.com/users/login");

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		loginToken = response.jsonPath().getString("token");

		response.then().log().all();
	}

	@Test(priority = 5, dependsOnMethods = "loginUser")

	public void addContact() {

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("firstName", "Ram");
		data.put("lastName", "nithin");
		data.put("birthdate", "1977-01-31");
		data.put("email", "ram@gmail.com");
		data.put("phone", "9848754623");
		data.put("street1", "Sector 11");
		data.put("street2", "Rahul Enclave");
		data.put("city", "Vijayawada");
		data.put("stateProvince", "Andhra Pradesh");
		data.put("postalCode", "528965");
		data.put("country", "India");

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + loginToken).body(data).when()
				.post("https://thinking-tester-contact-list.herokuapp.com/contacts");

		Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 Created");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("Created"), "Expected status line to contain 'Created'");

		response.then().log().all();
	}

	@Test(priority = 6, dependsOnMethods = "loginUser")
	public void getContactList() {

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + loginToken).when()
				.get("https://thinking-tester-contact-list.herokuapp.com/contacts");

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		firstContactID = response.jsonPath().getString("[0]._id");

		System.out.println("Contact Token is :" + firstContactID);

		response.then().log().all();

	}

	@Test(priority = 7)
	public void getContactById() {

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + loginToken).when()
				.get("https://thinking-tester-contact-list.herokuapp.com/contacts/" + firstContactID);

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		response.then().log().all();
	}

	@Test(priority = 8)

	public void updateFullContact() {

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("firstName", "Amy");
		data.put("lastName", "Miller");
		data.put("birthdate", "1998-07-04");
		data.put("email", "amiller@fake.com");
		data.put("phone", "984888845");
		data.put("street1", "DTS");
		data.put("street2", "Road no 25 MVP Colony");
		data.put("city", "Vizag");
		data.put("stateProvince", "AP");
		data.put("postalCode", "530017");
		data.put("country", "India");

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + loginToken).body(data).when()
				.put("https://thinking-tester-contact-list.herokuapp.com/contacts/" + firstContactID);

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		String email = response.jsonPath().getString("email");

		Assert.assertEquals(email, "amiller@fake.com", "Expected updated email to match");

		response.then().log().all();
	}

	@Test(priority = 9)
	public void updatePartialContact() {

		String requestBody = "{\n" + "  \"firstName\": \"Anna\"\n" + "}";

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + loginToken).body(requestBody).when()
				.patch("https://thinking-tester-contact-list.herokuapp.com/contacts/" + firstContactID);

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		String firstName = response.jsonPath().getString("firstName");

		Assert.assertEquals(firstName, "Anna", "Expected updated first name to be 'Anna'");

		response.then().log().all();
	}

	@Test(priority = 10)
	public void logoutUser() {

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + loginToken).when()
				.post("https://thinking-tester-contact-list.herokuapp.com/users/logout");

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		response.then().log().all();
	}
}
