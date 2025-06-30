package com.telecom.testcases;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
public class Get_User_Profile {
	
	@Test
	public void getProfile() {
		String req_URL = "https://thinking-tester-contact-list.herokuapp.com/users/me";
		
		Response response = given().header("Content-Type", "application/json").header("Authorization" , "Bearer " + AddNewUser.TokenGenerated).when().get(req_URL);
		
		Assert.assertEquals(response.statusCode(), 200);
		
		String statusLine = response.getStatusLine();

		Assert.assertTrue(statusLine.contains("OK"), "Expected status line to contain 'OK'");
		
		
		
	}

}
