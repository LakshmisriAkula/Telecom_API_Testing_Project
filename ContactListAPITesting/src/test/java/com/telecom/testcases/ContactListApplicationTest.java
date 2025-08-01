package com.telecom.testcases;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ContactListApplicationTest {

	String userToken;
	String loginToken;
	String userEmail;
	String firstContactID;

	@Test(priority = 1)
	public void addNewUser() {

		userEmail = "lakshmisri" + System.currentTimeMillis() + "@gmail.com";

		String requestBody = "{\n" + "  \"firstName\": \"Lakshmisri\",\n" + "  \"lastName\": \"Akula\",\n"
				+ "  \"email\": \"" + userEmail + "\",\n" + "  \"password\": \"Heroku@123\"\n" + "}";

		Response response = given().header("Content-Type", "application/json").body(requestBody).when()
				.post("https://thinking-tester-contact-list.herokuapp.com/users");

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 Created");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("Created"), "Expected status line to contain 'Created'");

		userToken = response.jsonPath().getString("token");

	}

	@Test(priority = 2)
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

		String requestBody = "{\n" + "  \"firstName\": \"Lakshmi\",\n" + "  \"lastName\": \"Sri\",\n"
				+ "  \"email\": \"" + userEmail + "\",\n" + "  \"password\": \"Heroku@1234\"\n" + "}";

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + userToken).body(requestBody).when()
				.patch("https://thinking-tester-contact-list.herokuapp.com/users/me");

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		response.then().log().all();
	}

	@Test(priority = 4)
	public void loginUser() {

		String requestBody = "{\n" + "  \"email\": \"" + userEmail + "\",\n" + "  \"password\": \"Heroku@1234\"\n"
				+ "}";

		Response response = given().header("Content-Type", "application/json").body(requestBody).when()
				.post("https://thinking-tester-contact-list.herokuapp.com/users/login");

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		loginToken = response.jsonPath().getString("token");

		response.then().log().all();
	}

	@Test(priority = 5)

	public void addContact() {

		String requestBody = "{\n" + "  \"firstName\": \"John\",\n" + "  \"lastName\": \"Doe\",\n"
				+ "  \"birthdate\": \"1970-01-01\",\n" + "  \"email\": \"jdoe@fake.com\",\n"
				+ "  \"phone\": \"8005555555\",\n" + "  \"street1\": \"1 Main St.\",\n"
				+ "  \"street2\": \"Apartment A\",\n" + "  \"city\": \"Anytown\",\n" + "  \"stateProvince\": \"KS\",\n"
				+ "  \"postalCode\": \"12345\",\n" + "  \"country\": \"USA\"\n" + "}";

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + loginToken).body(requestBody).when()
				.post("https://thinking-tester-contact-list.herokuapp.com/contacts");

		Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 Created");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("Created"), "Expected status line to contain 'Created'");

		response.then().log().all();
	}

	@Test(priority = 6)
	public void getContactList() {

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + loginToken).when()
				.get("https://thinking-tester-contact-list.herokuapp.com/contacts");

		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");

		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");

		firstContactID = response.jsonPath().getString("[0]._id");

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

		String requestBody = "{\n" + "  \"firstName\": \"Amy\",\n" + "  \"lastName\": \"Miller\",\n"
				+ "  \"birthdate\": \"1992-02-02\",\n" + "  \"email\": \"amiller@fake.com\",\n"
				+ "  \"phone\": \"8005554242\",\n" + "  \"street1\": \"13 School St.\",\n"
				+ "  \"street2\": \"Apt. 5\",\n" + "  \"city\": \"Washington\",\n" + "  \"stateProvince\": \"QC\",\n"
				+ "  \"postalCode\": \"A1A1A1\",\n" + "  \"country\": \"Canada\"\n" + "}";

		Response response = given().header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + loginToken).body(requestBody).when()
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
