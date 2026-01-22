# Running Tests

This project uses **TestNG** for test execution. Below are the supported ways to run the test suites.

---

## TestNG Parameters

### Running a Test Suite

```bash
java -cp "libs/*" org.testng.TestNG suite/flight-reservation.xml
```

### Running a test suite with specific thread count
 
 ```bash
 java -cp 'libs/*' org.testng.TestNG -threadcount 2 suite/flight-reservation.xml
 ```

 ### TestNG by default creates test-output directory. You can change it with -d option.

 ```bash
 java -cp 'libs/*' org.testng.TestNG -threadcount 2 -d result suite/flight-reservation.xml
 ```

 ----

 ## System Properties

 ### To pass the browser option

 ```bash
 java -Dbrowser=chrome -cp 'libs/*' org.testng.TestNG suite/flight-reservation.xml
 ```

 ### To run the tests using Selenium Grid

 ```bash
 java -'Dselenium.grid.enabled'=true -'Dselenium.grid.hub-host'=localhost -cp 'libs/*' org.testng.TestNG suite/flight-reservation.xml
 ```

 ### To run the tests using Selenium Grid with specific thread count

 ```bash
 java -'Dselenium.grid.enabled'=true -'Dselenium.grid.hub-host'=localhost -cp 'libs/*' org.testng.TestNG suite/flight-reservation.xml -threadcount 2
 ```