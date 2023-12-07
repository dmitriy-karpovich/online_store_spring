<h2 align="center">Online Store</h2>
Spring version of the 

[project](https://github.com/dmitriy-karpovich/online-store-epam-project)



### Description
The Online store system.
The administrator oversees the product catalog, ensuring that a diverse range of electronic devices are available. 
The customer browses, selects, checks out selected products and pays for them.
By creating an account and logging in, customers have access to the following functions:

* Browsing the catalogue.
* Managing the shopping cart.
* Placing and cancelling orders.
* Reviewing their profile.
* Accessing store contact information.
* Switching the application’s language between English (EN) and Russian (RU).

The administrator, in addition to the functions of a normal user, also has access to the following:

* Adding new products to the catalogue.
* Editing product details.
* Viewing and processing all active orders.
* Viewing and processing all completed orders.

#### Main components
* The MVC architecture.
* User registration and basic authentication.
* Role-based authorization.
* Data Transfer Object (DTO) to transfer data between application subsystems.
* Validation of incoming data from the user.
* Exception handling mechanism.
* Logging events during the application execution.
* Thymeleaf for processing and creating HTML pages.
* Local MySQL database server.
* Tests of the application layers.

### Technologies
* Java 17
* Spring Boot 3.1.3
* Spring Web MVC
* Java Persistence API (Hibernate)
* Spring Security
* Java Bean Validation (Hibernate Validator)
* Thymeleaf
* JUnit 5 and Mockito
* Logback logger
* Build tool: Maven
* Database: MySQL 8.0.33
* Bootstrap 4.5.2
