# UserData_team5

## Project Overview

The "UserData" project efficiently manages user data with core features including delete, upload, and export functionalities. Leveraging IAM service, Post service, Activity service, and Payment service, this report provides a concise overview of the project's architecture, emphasizing the application of design patterns and the use of external libraries for logging and PDF generation.

## Code Design

### External Libraries

- **Logger Library:**
  - we use  logger to record every action in system.
  - `logger.info()` log an informational message in system.
  - `logger.error()` log an error message.
  - `logger.getAllAppenders()` retrieve all the appenders associated with the logger.
  - [Download Log4j](https://www.apache.org/dyn/closer.cgi/logging/log4j/1.2.17/log4j-1.2.17.zip)

- **PDF Library:**
  - Facilitates seamless PDF generation, used in export and upload functionalities.
  - We used it in the export and the upload for creating pdf files that have all user information.
  - [Download PDFBox](https://www.apache.org/dyn/closer.lua/pdfbox/3.0.1/pdfbox-app-3.0.1.jar)

### Design Patterns

#### Factory Pattern:

- **Upload:**
  - used in the upload/ create a zip file to manage which data have to be upload according to user type.

- **Export:**
  - used to create exporters based on the type of services and create IUserType instances based on the user type

- **Delete:**
  - in HardDeleteStrategy where the appropriate delete method is called by the userType where it implements a OCP (open-closed principle) 

#### Template Pattern:

- **Export:**
  - used to defining exportUserData as a template in IUserType, allowing concrete subclasses to customize exported data types within the fixed export process. 

#### Strategy Pattern:

- **Upload:**
  - used in the upload to make the user select where he want to upload (google drive, or dropbox).

- **Export:**
  - used in the UserType interface establishes a shared set of actions for different user categories, such as NewIUser, RegularIUser, and PremiumIUser. 

- **Delete:**
  - in DeletionStrategy, where one of the deletion types (DeleteUserHardType or SoftDelete) is chosen at runtime without modifying the client code.
    It also made the code encapsulated and easy to replace.

## Class Digram 
  - [Go to Class Digram ](https://drive.google.com/file/d/1mZE7ioiMUvjjc3UjM0p5xxO26OpQxIey/view?usp=sharing)

![exported_from_idea drawio (1)](https://github.com/halaassi/UserData_team5/assets/92643585/37d7c201-f7cc-4520-a690-293c6ad9cee1)

