
# Selenium + Java Mini Project – Luma Automation Suite

This project is a Test Automation suite for the [Luma eCommerce Demo Website](https://magento.softwaretestingboard.com/), built using **Selenium WebDriver** in **Java**, organized with the **Page Object Model (POM)** design pattern, and tested using **TestNG** framework.

---

## Table of Contents

1. [Project Overview](#project-overview)  
2. [Technologies Used](#technologies-used)  
3. [Project Structure](#project-structure)  
4. [Setup Instructions](#setup-instructions)  
5. [Test Scenarios Implemented](#test-scenarios-implemented)  
6. [Features Implemented](#features-implemented)  
7. [Known Limitations](#known-limitations)

---

## Project Overview

This mini-project includes **6 automated end-to-end test scenarios** that simulate a user’s interaction with the Luma Demo eCommerce platform. Scenarios include registration, login, filtering products, managing wishlists, shopping carts, and verifying price sums.

---

## Technologies Used

- **Java 17+**
- **Selenium WebDriver**
- **TestNG** (for test execution)
- **Page Object Model (POM)**
- **Maven** (for dependency management)
- **Screenshot capture on failure**

---

## Project Structure

```
src/
├── main/
│   └── java/
│       └── pages/               # Page Object classes for each web page
│       └── utils/               # Utility classes (e.g., wait, config, screenshots)
├── test/
│   └── java/
│       └── tests/               # TestNG test classes for each scenario
│       └── base/                # BaseTest class for setup and teardown
screenshots/                     # Screenshots saved on failure
pom.xml                          # Maven dependencies
testng.xml                       # TestNG test suite
README.md                        
```

---

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/rigelHadushiDev/LumaTestSuite.git
   ```

2. **Open the project** in IntelliJ IDEA or Eclipse.

3. **Ensure Maven is installed and configured**.

4. **Update browser driver path** in `BaseTest.java` if needed.

5. **Run the tests**:
   - From IDE using `testng.xml`
   - Or via command line:
     ```bash
     mvn test
     ```

---

## Test Scenarios Implemented

### 🔹 Test 1: Create an Account
- Navigate to registration page
- Fill and submit the form
- Verify success message and user icon
- Sign out

### 🔹 Test 2: Sign In
- Log in with created credentials
- Verify username is shown
- Sign out

### 🔹 Test 3: Check Page Filters
- Navigate to Women > Tops > Jackets
- Filter by color and price
- Validate displayed products match selected filters

### 🔹 Test 4: Wish List Test
- Remove price filter
- Add first two items to Wish List
- Verify success message and item count in My Wish List

### 🔹 Test 5: Shopping Cart Test
- Add all filtered items to cart
- Verify success messages
- Navigate to cart
- Validate item price sum equals order total

### 🔹 Test 6: Empty Shopping Cart Test
- Remove all items from cart
- Verify cart is empty message

---

## Features Implemented

- Selenium WebDriver for browser automation  
- TestNG for structured test execution  
- Page Object Model for maintainable code  
- Explicit waits using WebDriverWait  
- Assertions for verification  
- Screenshot capture on failure (saved in `screenshots/` folder)

---

## Author

Developed by [@rigelHadushiDev](https://github.com/rigelHadushiDev)
