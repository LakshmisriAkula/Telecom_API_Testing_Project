# Telecom_API_Testing_Project

Telecom Contact List API Testing Project 🚀

✅ Project Overview:

This project covers end-to-end API testing for the Thinking Tester Contact List Application (Telecom Domain) using:

✅ Postman (Manual API Testing with Chai Assertions)

✅ Java + RestAssured + TestNG (API Automation Testing)

✅ Newman CLI (For command-line Postman execution)

✅ Extent Reports (For automated test reporting)

✅ Folder Structure:

ContactListAPITesting/
├── src/main/java/
│   └── com/telecom/extentreportlistener/
│       └── ExtentTestListener.java           # Extent Report Listener
├── src/test/java/
│   └── com/telecom/testcases/
│       ├── AddNewUser.java
│       ├── Get_User_Profile.java
│       ├── UpdateUser.java
│       ├── LoginUser.java
│       ├── AddContact.java
│       ├── GetContactList.java
│       ├── GetSingleContact.java
│       ├── UpdateFullContact.java
│       ├── UpdatePartialContact.java
│       └── LogoutUser.java
├── Postman_Collection/
│   └── ContactListAPI_TestCollection.json
├── Postman_Environment/
│   └── QA.postman_environment.json
├── Postman_Result_Screenshot/
│   └── Capture.PNG
├── ExtentReport.html                         # TestNG Extent Report Output
├── testng.xml                                # TestNG Suite File
├── pom.xml                                   # Maven Dependencies
└── README.md                                 # Project Overview and Run Instructions
✅ Tools Used:

Postman (Manual API Testing)
Chai Assertions (Postman Tests)
Newman CLI (Postman automation)
Java
RestAssured
TestNG
Extent Reports
Maven

✅ Running Postman Tests with Newman:
▶️ Basic Newman Run:
newman run Postman_Collection/ContactListAPI_TestCollection.json
▶️ Run with Environment File:
newman run Postman_Collection/ContactListAPI_TestCollection.json -e Postman_Environment/QA.postman_environment.json
▶️ Generate HTML Report :
newman run Postman_Collection/ContactListAPI_TestCollection.json -e Postman_Environment/QA.postman_environment.json -r html --reporter-html-export output/NewmanReport.html

✅ Running RestAssured Test Suite (Maven + TestNG):
▶️ From Command Line:
mvn test
(Make sure all your API test classes are included in testng.xml)

✅ Viewing Test Reports:

✅ Postman results → via Newman CLI or Collection Runner screenshots (See /Postman_Result_Screenshot/)

✅ RestAssured execution report → Open ExtentReport.html in browser after Maven run.

✅ Test Coverage Summary:
Test     Case	API Tested
TC01	  Add User
TC02	  Get User Profile
TC03	  Update User
TC04	  Login User
TC05	  Add Contact
TC06	  Get Contact List
TC07	  Get Single Contact
TC08	  Update Full Contact
TC09	  Update Partial Contact
TC10	  Logout User

