# Bazaar Management API
A comprehensive REST API for managing inventory, products, customers, suppliers and sales in a bazaar. 
Built using Spring Boot with a standard MVC architecture, this project leverages JPA/Hibernate for data persistence, Bean Validation for input validation, and dependency injection for clean and modular code.


## Features
CRUD Operations: Manage products, customers, suppliers, sales and supply orders.
Analytics: Endpoints for sales trends, top-selling products, and revenue summaries.
Robust Exception Handling: Custom exception handling for clear error reporting.
Functional Programming: Utilizes Java Streams for efficient data processing.

## Tech Stack
Spring Boot: Backend framework.
Java: Programming language.
JPA/Hibernate: ORM for database operations.
MySQL: Compatible with relational databases.
Postman: API testing and documentation.


## Installation

Follow these steps to set up and run the Bazaar Management API on your local machine:

Prerequisites
- XAMPP: Download and install XAMPP to manage Apache and MySQL.
- Java: Install JDK 21 or later.
- Maven: Install Apache Maven for building the application.
- Postman: Install Postman for API testing.

1) Clone the Repository

2) Start Apache and MySQL Services
   - Open XAMPP Control Panel.
   - Start Apache and MySQL services.

3) Import the SQL File

  - Open phpMyAdmin in your browser.
  - Create a new database (e.g., bazaar_db).
  - Import the SQL file (sql/bazaar.sql) to set up the database schema and initial data:
    Go to the "Import" tab.
    Upload the SQL file located in the sql/ folder.

4) Configure application.properties

Since application.properties includes environment variables (such as database credentials), it was not included in the repository for security reasons.
Set the following environment variables inside an application.properties file:

spring.application.name=app_bazaar
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER_NAME}
spring.datasource.password=${DB_PASSWORD}

- Create a .env file in the root directory and set the actual values for the environment variables
   dotenv:
   DB_URL=jdbc:mysql://localhost:3306/your_database_name
   DB_USER_NAME=your_db_username
   DB_PASSWORD=your_db_password


5) Import Postman Collection

Open Postman and import the collection file from the postman/ folder:
Go to "File" > "Import."
Select the file bazaar-collection.json.

Test API Endpoints
Use the imported Postman collection to interact with the API.



## Endpoints Overview
Here are some key endpoints from the Postman collection:

Product
POST /product/create: Add a new product.
GET /product/getAll: Fetch all products.
GET /product/findOne/{id}: Get product details by ID.
PUT /product/update: Update product details.
DELETE /product/delete/{id}: Delete a product by ID.

Customer
POST /customer/create: Add a new customer.
GET /customer/getAll: Fetch all customers.
GET /customer/findById/{id}: Get customer details by ID.
PUT /customer/update: Update customer details.
DELETE /customer/delete/{id}: Delete a customer by ID.

Sales Analytics
GET /analytics/mostSoldProduct: Get the most sold product within a date range.
GET /analytics/totalRevenue: Calculate total revenue within a date range.


## Future Improvements
Add user authentication and authorization.
Implement a front-end using Angular or React.
Enhance analytics with more detailed reporting
