# 🍔 Krabby Patty Food Ordering System

A full-stack **Food Ordering Web Application** built using **Spring Boot, Spring Data JPA, MySQL, HTML, CSS, JavaScript, Bootstrap, and jQuery**.

The application allows customers to browse food categories, add food items to a cart, register/login, and place food orders. Administrators can manage categories, food items, users, and orders through an admin dashboard.

---

## 📌 Project Overview

**Krabby Patty Food Ordering System** is a web-based food ordering application inspired by the Krusty Krab/Krabby Patty theme.

The project demonstrates the implementation of:

* User registration and login
* Role-based user access
* Food category management
* Food management
* Shopping cart functionality
* Order placement
* Delivery information
* Cash on Delivery and Online Payment selection
* Admin dashboard
* Order management
* MySQL database integration
* REST APIs using Spring Boot
* Frontend AJAX communication with backend APIs

---

## 🚀 Features

### 👤 Customer Features

* User registration
* User login
* User logout
* View food categories
* View available food items
* Add food items to cart
* Update/manage cart items
* Remove items from cart
* View cart total
* Place an order
* Enter delivery details
* Select payment method

  * Cash on Delivery
  * Online Payment
* View order information
* User-specific cart storage

### 🔐 Authentication & Authorization

* User authentication
* Admin and customer roles
* Role-based redirection after login
* Admin dashboard access
* Current logged-in user API
* Password encryption using Spring Security Crypto

### 👨‍💼 Admin Features

* Admin dashboard
* Category management
* Food management
* Order management
* User management
* View incoming orders
* Order notification badge
* Manage food categories
* Manage food items

### 🛒 Shopping Cart

The shopping cart uses browser `localStorage`.

For logged-in users, the cart is stored using:

```text
cart_<userId>
```

For guests:

```text
cart_guest
```

This allows users to maintain separate carts.

---

## 🛠️ Technologies Used

### Backend

* Java 17
* Spring Boot 3.3.4
* Spring MVC
* Spring Data JPA
* Spring Security Crypto
* Spring Boot Validation
* MySQL
* Maven

### Frontend

* HTML5
* CSS3
* JavaScript
* jQuery
* Bootstrap 5
* Font Awesome
* AJAX
* Browser LocalStorage

### Development Tools

* Eclipse / Spring Tool Suite
* Git
* GitHub
* Maven Wrapper
* MySQL

---

## 🏗️ Project Structure

```text
krabby-patty-food-ordering-system/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── web/
│   │   │           ├── config/
│   │   │           │   └── SecurityConfig.java
│   │   │           │
│   │   │           ├── controller/
│   │   │           │   ├── CartController.java
│   │   │           │   ├── CategoryController.java
│   │   │           │   ├── FoodController.java
│   │   │           │   ├── FoodOrderController.java
│   │   │           │   ├── OrderController.java
│   │   │           │   ├── UserController.java
│   │   │           │   └── UserOrderController.java
│   │   │           │
│   │   │           ├── model/
│   │   │           │   ├── Cart.java
│   │   │           │   ├── CartItem.java
│   │   │           │   ├── Category.java
│   │   │           │   ├── CreateFoodOrderRequest.java
│   │   │           │   ├── Food.java
│   │   │           │   ├── FoodOrder.java
│   │   │           │   ├── OrderAddress.java
│   │   │           │   ├── OrderFoodItem.java
│   │   │           │   └── User.java
│   │   │           │
│   │   │           ├── repository/
│   │   │           │   ├── CartRepository.java
│   │   │           │   ├── CategoryRepository.java
│   │   │           │   ├── FoodOrderRepository.java
│   │   │           │   ├── FoodRepository.java
│   │   │           │   ├── OrderAddressRepository.java
│   │   │           │   ├── OrderFoodItemRepository.java
│   │   │           │   ├── UserOrderRepository.java
│   │   │           │   └── UserRepository.java
│   │   │           │
│   │   │           ├── service/
│   │   │           │   ├── CartService.java
│   │   │           │   ├── CategoryService.java
│   │   │           │   ├── CategoryServiceImpl.java
│   │   │           │   ├── FoodOrderService.java
│   │   │           │   ├── FoodService.java
│   │   │           │   ├── FoodServiceImpl.java
│   │   │           │   ├── OrderService.java
│   │   │           │   ├── OrderServiceImpl.java
│   │   │           │   ├── UserOrderService.java
│   │   │           │   ├── UserService.java
│   │   │           │   └── UserServiceImpl.java
│   │   │           │
│   │   │           └── KrabbyPattyFoodOrderingSystemApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── js/
│   │       │   ├── img/
│   │       │   ├── index.html
│   │       │   ├── login-form.html
│   │       │   ├── register-form.html
│   │       │   ├── order-table-form.html
│   │       │   └── admin-dashboard.html
│   │       │
│   │       ├── templates/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│
├── uploads/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

## ⚙️ Requirements

Before running the project, make sure you have installed:

* Java 17 or higher
* MySQL 8.x
* Maven (optional because Maven Wrapper is included)
* Git

Check Java:

```bash
java -version
```

Check Maven:

```bash
mvn -version
```

---

## 🗄️ Database Setup

Create a MySQL database:

```sql
CREATE DATABASE krabby_patty;
```

Then configure your database connection in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/krabby_patty
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

> Replace `YOUR_PASSWORD` with your local MySQL password.

**Do not commit your real database password to GitHub.**

For a public repository, it is recommended to keep credentials outside Git and use environment variables.

---

## ▶️ Running the Application

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
```

### 2. Navigate into the project

```bash
cd YOUR_REPOSITORY
```

### 3. Configure MySQL

Create the database and update:

```text
src/main/resources/application.properties
```

with your local database credentials.

### 4. Run the application

Using Maven Wrapper:

### Windows

```bash
./mvnw.cmd spring-boot:run
```

### Linux / macOS / Git Bash

```bash
./mvnw spring-boot:run
```

Or using Maven:

```bash
mvn spring-boot:run
```

---

## 🌐 Access the Application

Once the application starts, open:

```text
http://localhost:8080/
```

### Main Pages

| Page                | URL                      |
| ------------------- | ------------------------ |
| Home                | `/`                      |
| Login               | `/login-form.html`       |
| Register            | `/register-form.html`    |
| Order               | `/order-table-form.html` |
| Admin Dashboard     | `/admin-dashboard.html`  |
| Category Management | `/category-table.html`   |
| Food Management     | `/food-table.html`       |
| Orders              | `/orders-table.html`     |

---

## 🔑 Application Flow

### Customer Flow

```text
Register
   ↓
Login
   ↓
Browse Categories
   ↓
Browse Foods
   ↓
Add Food to Cart
   ↓
View Cart
   ↓
Place Order
   ↓
Enter Delivery Details
   ↓
Select Payment Type
   ↓
Confirm Order
```

### Admin Flow

```text
Admin Login
    ↓
Admin Dashboard
    ↓
Manage Categories
    ↓
Manage Foods
    ↓
Manage Users
    ↓
View Orders
    ↓
Process Orders
```

---

## 🔌 Important API Endpoints

Some of the application's frontend requests include:

### User

```http
POST /users/register
```

Registers a new user.

```http
GET /users/current
```

Returns the currently logged-in user.

### Orders

```http
POST /api/orders
```

Creates a new food order.

```http
GET /api/orders
```

Retrieves orders for the admin/order management functionality.

---

## 💳 Payment Types

The order form currently supports:

```text
COD
ONLINE
```

### Cash on Delivery

The customer can place an order and pay when the food is delivered.

### Online Payment

The application currently provides an **Online Payment** selection option. A real payment gateway integration such as Razorpay, Stripe, or another payment provider can be added as a future enhancement.

---

## 📸 Screenshots

You can add screenshots of your application here.

Example:

```markdown
## 📸 Screenshots

### Home Page

![Home Page](screenshots/home.png)

### Login Page

![Login Page](screenshots/login.png)

### Food Page

![Food Page](screenshots/foods.png)

### Admin Dashboard

![Admin Dashboard](screenshots/admin-dashboard.png)

### Order Page

![Order Page](screenshots/order.png)
```

Create a folder:

```text
screenshots/
```

and place your screenshots inside it.

---

## 🔒 Security Notes

This project uses Spring Security Crypto for password-related functionality.

For production deployment, additional security improvements should be considered, including:

* Strong password hashing configuration
* Proper authentication/session management
* Server-side role authorization
* CSRF protection where applicable
* Input validation
* Secure HTTP headers
* HTTPS
* Environment variables for secrets
* Proper payment gateway verification
* Protection of admin endpoints

> Frontend checks such as hiding an admin link are not sufficient to secure an admin API. Authorization must also be enforced on the backend.

---

## 🧪 Testing

Run the test suite with:

```bash
./mvnw test
```

On Windows:

```bash
./mvnw.cmd test
```

---

## 📦 Build the Project

Create a production JAR:

```bash
./mvnw clean package
```

The generated JAR will be available inside:

```text
target/
```

Run the JAR:

```bash
java -jar target/krabby-patty-food-ordering-system-0.0.1-SNAPSHOT.jar
```

---

## 🔮 Future Improvements

Possible future enhancements include:

* [ ] Razorpay/Stripe payment integration
* [ ] Order status tracking
* [ ] Email order confirmation
* [ ] SMS notifications
* [ ] Better admin dashboard statistics
* [ ] Food search and filtering
* [ ] Food reviews and ratings
* [ ] Wishlist functionality
* [ ] Pagination
* [ ] Improved responsive design
* [ ] JWT-based authentication
* [ ] Centralized exception handling
* [ ] DTO-based API architecture
* [ ] API documentation using Swagger/OpenAPI
* [ ] Docker support
* [ ] CI/CD pipeline
* [ ] Production deployment

---

## 🤝 Contributing

Contributions are welcome.

1. Fork the repository.
2. Create a new branch.

```bash
git checkout -b feature/my-new-feature
```

3. Make your changes.
4. Commit your changes.

```bash
git add .
git commit -m "Add new feature"
```

5. Push the branch.

```bash
git push origin feature/my-new-feature
```

6. Open a Pull Request.

---

## 📄 License

This project is currently intended for educational and demonstration purposes.

You can add a specific open-source license such as MIT if you decide to distribute the project under that license.

---

## 👨‍💻 Author

**Your Name**

GitHub: `https://github.com/YOUR_USERNAME`

---

## ⭐ Support

If you found this project useful, consider giving the repository a ⭐ on GitHub.
