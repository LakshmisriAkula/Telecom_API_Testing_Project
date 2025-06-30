# Telecom_API_Testing_Project

Telecom Contact List API Testing Project 🚀
✅ Project Overview:
This project demonstrates end-to-end API testing for the Thinking Tester Contact List Application (Telecom Domain) using two approaches:

✅ Manual API Testing with Postman + Chai Assertions

✅ API Automation Testing using Java, RestAssured, TestNG, and Extent Reports

✅ Technologies Used:
Area	                                      Tools
API Manual Testing	                        Postman
API Automation	                            Java, RestAssured, TestNG
Reporting	                                  Extent Reports
Command-line API Testing	                  Newman
Build Management	                          Maven

✅ Project Folder Structure:
bash
Copy
Edit
Telecom_API_Testing_Project/
├── src/main/java/
│   └── com/telecom/extentreportlistener/        # Extent Report Listener
├── src/test/java/
│   └── com/telecom/testcases/                   # 10 RestAssured API Test Classes
├── Postman_Collection/
│   └── ContactListAPI_TestCollection.json       # Exported Postman Collection
├── Postman_Environment/
│   └── QA.postman_environment.json              # Postman Environment Variables (if used)
├── Postman_Result_Screenshot/
│   └── Capture.PNG                              # Screenshot of Postman Test Results (Runner)
├── ExtentReport.html                            # Extent Report Output (after automation run)
├── testng.xml                                   # TestNG Suite File
├── pom.xml                                      # Maven Dependencies
└── README.md                                    # Project Summary and Instructions
✅ How to Run Postman Collection using Newman:
1. Run Collection Without Environment File:
newman run Postman_Collection/ContactListAPI_TestCollection.json

2. Run Collection With Environment File:
newman run Postman_Collection/ContactListAPI_TestCollection.json -e Postman_Environment/QA.postman_environment.json

4. Generate HTML Report using htmlextra Reporter:
(First, install htmlextra if not done)
npm install -g newman-reporter-htmlextra
Then:
newman run Postman_Collection/ContactListAPI_TestCollection.json -e Postman_Environment/QA.postman_environment.json -r htmlextra

✅ How to Run RestAssured Automation Suite:
1. Using Maven Command:
mvn test

3. Output Report:
After execution, open: ExtentReport.html in your browser for full API execution report.

✅ API Test Cases Covered (10 Test Cases):

Test Case No	      Test Case Description
TC01	              Add User
TC02	              Get User Profile
TC03	              Update User
TC04	              Login User
TC05	              Add Contact
TC06	              Get Contact List
TC07	              Get Single Contact
TC08	              Update Full Contact
TC09	              Update Partial Contact
TC10	              Logout User

✅ Author:
Lakshmi Akula
[GitHub Profile](https://github.com/LakshmisriAkula)
