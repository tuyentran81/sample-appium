# sample-appium
This repository contains a sample Appium automation framework using Java, TestNG, and Maven.
It is structured around the Page Object Model (POM) and Arrange-Act-Assert (3A) test pattern,
with ExtentReports for a UI-friendly HTML report.

## Features
- Appium Page Object Model structure
- 3A (Arrange, Act, Assert) test style
- Supports native apps, hybrid apps, and mobile web on Android/iOS
- ExtentReports HTML report output in `test-output/extent-report.html`

## Project Structure
```
src/test/java/com/sample/appium
├── config        # Framework configuration loader
├── driver        # Appium driver factory
├── framework     # Base test/page classes + reporting listener
├── pages         # Page Objects
└── tests         # TestNG test classes
```

## Configuration
Default values live in `src/test/resources/config/framework.properties`. You can override any
property using system properties.

Key properties:
- `platformName`: Android | iOS
- `appType`: native | hybrid | web
- `deviceName`, `platformVersion`, `automationName`, `udid`
- `app`: Path to `.apk`/`.app`/`.ipa` for native/hybrid
- `browserName`: Chrome (Android) | Safari (iOS)
- `remoteUrl`: Appium server URL

Example:
```bash
mvn test -DplatformName=iOS -DappType=web -DbrowserName=Safari -DdeviceName="iPhone 15"
```

## Running Tests
1. Start the Appium server.
2. Ensure the device or simulator is available.
3. Run:
```bash
mvn test
```

## Reporting
After a test run, open:
```
./test-output/extent-report.html
```

## Logging
Framework-level logging is handled by `FrameworkLogger` (SLF4J). It captures configuration load
issues and Appium session startup failures to centralize error handling.

## CI/CD
This project includes a GitHub Actions workflow that builds the Maven project and validates the
TestNG suite configuration on every push and pull request.
