# OrangeHRM Automation (POM-based)

## Tools used
- Java 11+
- Maven
- Selenium WebDriver
- TestNG
- WebDriverManager
- REST Assured

## Setup
1. Install Java 11+ and Maven.
2. Unzip the project folder.
3. From the project root run:
```
mvn clean test
```

## What is automated
- Login to https://opensource-demo.orangehrmlive.com
- Navigate to Admin and search for the Admin user
- Assert number of results, username, role, and status
- Attempt to delete the Admin user and assert deletion is not allowed
- Add a candidate using the API and assert HTTP response status

