
# Telecom Contact List API Testing Project 🚀

## ✅ Project Overview:

This project demonstrates **end-to-end API testing** for the **Thinking Tester Contact List Application (Telecom Domain)** using two approaches:

- Manual API Testing with **Postman + Chai Assertions**
- API Automation Testing using **Java, RestAssured, TestNG, and Extent Reports**

---

## ✅ Technologies Used:

- Postman
- Chai Assertions
- Newman CLI
- Java
- RestAssured
- TestNG
- Maven
- Extent Reports

---

## ✅ Project Folder Structure:

```
Telecom_API_Testing_Project/
├── src/main/java/
│   └── com/telecom/extentreportlistener/
│       └── ExtentTestListener.java
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
├── ExtentReport.html
├── testng.xml
├── pom.xml
└── README.md
```

---

## ✅ Running Postman Collection using Newman:

### Run Collection Without Environment:

```bash
newman run Postman_Collection/ContactListAPI_TestCollection.json
```

### Run Collection With Environment File:

```bash
newman run Postman_Collection/ContactListAPI_TestCollection.json -e Postman_Environment/QA.postman_environment.json
```

### Generate HTML Report with htmlextra Reporter:

```bash
npm install -g newman-reporter-htmlextra
```

Then:

```bash
newman run Postman_Collection/ContactListAPI_TestCollection.json -e Postman_Environment/QA.postman_environment.json -r htmlextra --reporter-htmlextra-export output/NewmanReport.html
```

---

## ✅ Running RestAssured TestNG Suite:

### Maven Command:

```bash
mvn test
```

After execution:

- ✅ View Extent Report:  
Open `ExtentReport.html` in your browser.

---

## ✅ Test Case Coverage:

| Test Case | API Tested |

| TC01 | Add User |
| TC02 | Get User Profile |
| TC03 | Update User |
| TC04 | Login User |
| TC05 | Add Contact |
| TC06 | Get Contact List |
| TC07 | Get Single Contact |
| TC08 | Update Full Contact |
| TC09 | Update Partial Contact |
| TC10 | Logout User |

---

## ✅ Author:

**Lakshmi Akula**  
GitHub: [https://github.com/LakshmisriAkula](https://github.com/LakshmisriAkula)
