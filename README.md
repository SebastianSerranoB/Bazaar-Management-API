Bazaar Management API
A comprehensive REST API for managing inventory, products, customers, suppliers and sales in a bazaar. Built using Spring Boot with a standard MVC architecture, this project leverages JPA/Hibernate for data persistence, Bean Validation for input validation, and dependency injection for clean and modular code.

Features
CRUD Operations: Manage products, customers, suppliers, sales and supply orders.
Analytics: Endpoints for sales trends, top-selling products, and revenue summaries.
Robust Exception Handling: Custom exception handling for clear error reporting.
Functional Programming: Utilizes Java Streams for efficient data processing.

Tech Stack
Spring Boot: Backend framework.
Java: Programming language.
JPA/Hibernate: ORM for database operations.
MySQL: Compatible with relational databases.
Postman: API testing and documentation.

bazaar/
├── src/                       # Source code
│   ├── main/
│   └── test/
├── postman/                   # Postman collections and environments
│   ├── bazaar_app.postman_collection.json
│   
├── sql/                       # Database schema and sample data
│   ├── bazaar.sql
│   
├── pom.xml                    # Maven build configuration
├── .gitignore                 # Ignored files and directories
├── README.md                  # Project documentation


Endpoints Overview
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


Future Improvements
Add user authentication and authorization.
Implement a front-end using Angular or React.
Enhance analytics with more detailed reporting
