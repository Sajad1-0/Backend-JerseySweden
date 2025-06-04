# JerseySweden Backend API

This is a Spring Boot backend for **JerseySweden** – a football jersey webshop. The system handles products, categories, and orders including stock management and validation.

## 🛠️ Build & Run Instructions

### Requirements
- Java 17+
- Maven 3.8+
- Git

### Clone and Run

git clone https://github.com/your-username/jerseysweden-backend.git
cd jerseysweden-backend
./mvnw spring-boot:run

The application will start on http://localhost:8085.

# 📦 API Documentation
## 🔹 Products
### ✅ Create a Product
POST /products
{
  "name": "Arsenal Home Shirt",
  "description": "Official 2024 Arsenal jersey",
  "price": 899.0,
  "imageUrl": "http://ArsenalKit....",
  "category": "Premier League",
  "categoryImageUrl" : "http:Primiersleague...",
  "stock": 10
}

### 🔍 Get All Products
GET /products

### 🔍 Get Products by Category
GET /products/category/{category}

## 🔸 Orders
### ✅ Create an Order
POST /orders
{
   "customerInfo": {
    "name": "John Doe",
    "email": "john@example.com",
    "address": "Test Street 1",
    "postalCode": "12345",
    "city": "Stockholm"
  },
   "orderItems": [
    {
      "productId": "1",
      "quantity": 2
    }
  ],
}

Response:

{
  "orderId": "1001",
  "message": "Order placed Successfully"
}

# ✅ Testing Instructions (Postman)
You can use Postman to test the API endpoints. Below are example requests to import manually:

## 🔹 Create a Product
Method: POST
URL: http://localhost:8080/api/products
Headers:

makefile
Key: Content-Type
Value: application/json
Body (raw, JSON):
{
  "name": "Real Madrid Shirt",
  "description": "Home shirt 2024",
  "price": 950.0,
  "imageUrl": "http://RealMAkdefs93n...",
  "category": "La Liga",
  "stock": 15
}

##🔸 Create an Order
Method: POST
URL: http://localhost:8080/api/orders
Headers:

makefile
Key: Content-Type
Value: application/json
Body (raw, JSON):

{
  "customerInfo": {
    "name": "Anna Svensson",
    "email": "anna@example.com",
    "address": "Test Road 5",
    "postalCode": "11122",
    "city": "Gothenburg"
  },
   "orderItems": [
      {
        "productId": 1,
        "quantity": 1
      }
  ],
}

# 🧪 Unit Tests
Unit tests are located in:
src/test/java/com/example/jerseysweden/serviceTest/

### Run tests using:
./mvnw test

# 📄 License
MIT License. This project was developed as part of a school assignment and is free to use as reference.

---

Let me know if you'd like to add Swagger/OpenAPI instructions or adjust anything for Heroku, Docker, or deployment platforms.
Contact me: Sajjadqaderi00@gmail.com

