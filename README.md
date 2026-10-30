# Chicken Sales Management System (Backend) - Part A

- **Student:** Iradukunda James
- **Student ID:** 27544
- **Project:** Chicken Sales Management System
- **Course:** INSY 8322 Web Technology and Internet (AUCA)

## How to run (simple steps)
1. Install Java 17 (or newer), Maven and PostgreSQL.
2. In PostgreSQL create an empty database: `CREATE DATABASE chicken_sales_db;`
3. Open `src/main/resources/application.properties` and write your PostgreSQL password.
4. In the project folder run: `mvn spring-boot:run`
5. The API runs on http://localhost:8080 . Test it in Postman.

## What Part A contains
- 5 entities: Breed, Stall, SalesAgent, Customer, ChickenOrder + enum OrderStatus
- CRUD for each: `POST /api/{path}/save`, `GET /api/{path}/all`, `GET /api/{path}/{id}`,
  `PUT /api/{path}/update/{id}`, `DELETE /api/{path}/delete/{id}`
- `{path}` = breeds, stalls, sales-agents, customers, chicken-orders

## Seed data (Step 0), enter in this order
breeds -> stalls -> sales-agents -> customers -> chicken-orders
(copy each id from `GET /api/{path}/all` when the next one needs it)
