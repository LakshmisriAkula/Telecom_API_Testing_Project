
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
├── src/
│ ├── main/
│ │ └── java/
│ │ └── com/telecom/extentreportlistener/
│ │ └── ExtentTestListener.java
│ └── test/
│ ├── java/
│ │ └── com/telecom/testcases/
│ │ └── ContactListApplicationTest.java
│ └── resources/
├── Postman_Collection/
│ └── Telecom_API_Testing_Project.postman_collection.json
├── Postman_Environment/
│ └── Telecom_APIProject.postman_environment.json
├── Postman_Result/
│ └── NewmanReport.html
├── test-output/
├── target/
├── ExtentReport.html
├── testNG.xml
├── pom.xml
└── README.md
```

---

## ✅ Running Postman Collection using Newman:

### Run Collection Without Environment:

```
newman run Postman_Collection/Telecom_API_Testing_Project.postman_collection.json
```

### Run Collection With Environment File:

```bash
newman run Postman_Collection/Telecom_API_Testing_Project.postman_collection.json -e Postman_Environment/Telecom_APIProject.postman_environment.json
```

### Generate HTML Report with htmlextra Reporter:

```
npm install -g newman-reporter-htmlextra
```

Then:

```
newman run Postman_Collection/Telecom_API_Testing_Project.postman_collection.json -e Postman_Environment/Telecom_APIProject.postman_environment.json -r htmlextra --r eporter-htmlextra-export Postman_Result\NewmanReport.html
```
After execution:

✅ View Newman Report:
Open NewmanReport.html in your browser.

---

## ✅ Running RestAssured TestNG Suite:

### Maven Command:

```
mvn test
**OR**
Use the TestNG XML file:
Right-click on testNG.xml → Run As → TestNG Suite
```

After execution:

- ✅ View Extent Report:  
Open `ExtentReport.html` in your browser.

---

✅ Test Case Coverage:
```
**Test Case File**	                          **API Tested**
ContactListApplicationTest.java	              Covers: Add User, Get User Profile, Update User, Login User, Add Contact, Get Contact List, Get Single Contact, Update Full Contact, Update Partial Contact, Logout User
```
---

## 🙋‍♀️ Author

**Lakshmi Akula**  
Automation Tester | 8+ Years QA Experience (Manual & 4+ Years Selenium Automation)  
🌐 [GitHub](https://github.com/LakshmisriAkula)  
🔗 [LinkedIn](https://www.linkedin.com/in/lakshmisri-akula-b78232130/)

---

## 📬 Contact

If you have any questions or feedback, feel free to reach out via:
- GitHub Issues  
- LinkedIn Messaging  
- Email (akulalakshmisri@gmail.com)
