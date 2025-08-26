# Selenium TestNG Login Automation Project

## Project Description

This is a sample project for automating login page testing using Selenium WebDriver and TestNG.  
The project uses the [Heroku App Login](https://the-internet.herokuapp.com/login) as a test resource, which supports both valid and invalid login scenarios.

The project demonstrates:

- Positive and negative login test cases
- Using TestNG DataProvider for multiple data combinations
- Explicit waits with WebDriverWait
- Compatibility with the latest Chrome and ChromeDriver

---

## Project Structure

```
selenium-testng-project/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       └── java/
│           └── com/example/LoginTest.java
```

- `LoginTest.java` — main test class for positive and negative login scenarios
- `pom.xml` — Maven configuration with Selenium and TestNG dependencies

---

## Technologies Used

- Java 21
- Maven
- Selenium WebDriver 4.25
- TestNG 7.x
- Chrome 139 + ChromeDriver 139

---

## Project Setup

1. Clone the repository:

```bash
git clone https://github.com/yourusername/selenium-testng-project.git
```

2. Make sure you have JDK 17+ and Maven installed.
3. Ensure Chrome is installed (version matches ChromeDriver 139).

---

## Running Tests

- Using Maven:

```bash
mvn clean test
```

- Using IntelliJ IDEA:  
  Run `LoginTest.java` or the entire test package as a TestNG Test.

---

## Test Cases

- **Positive tests:** verify successful login with valid credentials
- **Negative tests:** verify error messages for invalid username, invalid password, and empty fields
- **DataProvider** is used to easily add more test scenarios

---

## Notes

- Tests run on **Heroku App**, which does not have a captcha, ensuring stable execution.
- Automating real websites with captcha may not be possible without third-party captcha solving services.

