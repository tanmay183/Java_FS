# 🌐 4. Building REST APIs with Spring Boot

This section teaches you **how to create REST APIs using Spring Boot**, the most common backend task in real-world development and interviews.

---

## 🔍 What is a REST API?

**REST (Representational State Transfer)** is an architecture style for designing networked applications using HTTP.

In REST:

* You make requests using standard HTTP methods like `GET`, `POST`, `PUT`, `DELETE`
* You work with **resources** (like `users`, `orders`, `products`)
* Data is typically returned as **JSON** or **XML**

---

## ✅ What is `@RestController`?

### 📘 Explanation:

* `@RestController` is a combination of:

  * `@Controller` → tells Spring that the class handles web requests
  * `@ResponseBody` → tells Spring to automatically serialize return values to JSON/XML

### 🔧 Example:

```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }
}
```

> This API responds to: `GET http://localhost:8080/hello`

---

## 🚏 Request Mappings: `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`

These annotations map HTTP methods to Java methods.

| Annotation        | HTTP Method | Purpose                       |
| ----------------- | ----------- | ----------------------------- |
| `@GetMapping`     | GET         | Read/Retrieve data            |
| `@PostMapping`    | POST        | Create new data               |
| `@PutMapping`     | PUT         | Update existing data          |
| `@DeleteMapping`  | DELETE      | Delete existing data          |
| `@RequestMapping` | Any         | Generic mapping (used rarely) |

---

### 🔧 Examples:

```java
@RestController
@RequestMapping("/api")  // Common prefix for all routes
public class ProductController {

    @GetMapping("/product")
    public String getProduct() {
        return "Product fetched";
    }

    @PostMapping("/product")
    public String createProduct() {
        return "Product created";
    }

    @PutMapping("/product")
    public String updateProduct() {
        return "Product updated";
    }

    @DeleteMapping("/product")
    public String deleteProduct() {
        return "Product deleted";
    }
}
```

---

## 🏷️ `@PathVariable` vs `@RequestParam`

### ✅ `@PathVariable`: Get data from URL path

```java
@GetMapping("/user/{id}")
public String getUser(@PathVariable int id) {
    return "User ID: " + id;
}
```

📍 URL Example: `GET /user/101` → Response: `User ID: 101`

---

### ✅ `@RequestParam`: Get data from query parameters

```java
@GetMapping("/search")
public String search(@RequestParam String keyword) {
    return "You searched for: " + keyword;
}
```

📍 URL Example: `GET /search?keyword=apple` → Response: `You searched for: apple`

---

## 📨 `@RequestBody` – Accepting JSON in POST/PUT

This annotation binds **incoming JSON** to a Java object.

### 🔧 Java Class:

```java
public class Product {
    public int id;
    public String name;
}
```

### 🔧 POST Endpoint:

```java
@PostMapping("/product")
public String addProduct(@RequestBody Product product) {
    return "Added: " + product.name;
}
```

📍 Sample Request:

```json
POST /product
Content-Type: application/json

{
  "id": 101,
  "name": "Laptop"
}
```

> Spring automatically converts the JSON to a Java object.

---

## 📤 `@ResponseBody` – Returning Data as JSON/XML

* `@ResponseBody` tells Spring to convert the return value to **JSON or XML** using Jackson or JAXB.
* It’s automatically applied in `@RestController`, but you can use it manually in `@Controller`.

### 🔧 Example:

```java
@Controller
public class UserController {

    @ResponseBody
    @GetMapping("/user")
    public User getUser() {
        return new User(1, "Tanmay");
    }
}
```

Spring will return:

```json
{
  "id": 1,
  "name": "Tanmay"
}
```

---

## 📄 Returning JSON or XML Automatically

### ✅ JSON (Default)

* Spring Boot uses **Jackson** by default to convert Java objects to JSON.

```java
@RestController
public class UserController {
    @GetMapping("/user")
    public User getUser() {
        return new User(1, "Amit");
    }
}
```

### ✅ XML Support (Optional)

1. Add dependency in `pom.xml`:

```xml
<dependency>
  <groupId>com.fasterxml.jackson.dataformat</groupId>
  <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

2. Annotate your model:

```java
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class User {
    public int id;
    public String name;
}
```

3. Set Accept header in the request:

```
Accept: application/xml
```

Spring will return XML:

```xml
<User>
  <id>1</id>
  <name>Amit</name>
</User>
```

---

## ✅ Real-world Example: Full CRUD API

### 🧾 Model:

```java
public class Student {
    public int id;
    public String name;
}
```

### 🔧 Controller:

```java
@RestController
@RequestMapping("/students")
public class StudentController {

    // GET
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return new Student(id, "Tanmay");
    }

    // POST
    @PostMapping
    public String addStudent(@RequestBody Student s) {
        return "Added: " + s.name;
    }

    // PUT
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student s) {
        return "Updated student with ID " + id + " to " + s.name;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        return "Deleted student with ID: " + id;
    }
}
```

---

## ✅ Summary Table

| Annotation        | Use Case                                             |
| ----------------- | ---------------------------------------------------- |
| `@RestController` | Marks class as REST controller (returns JSON/XML)    |
| `@GetMapping`     | Read data (GET)                                      |
| `@PostMapping`    | Add new data (POST)                                  |
| `@PutMapping`     | Update existing data (PUT)                           |
| `@DeleteMapping`  | Delete data (DELETE)                                 |
| `@RequestMapping` | Generic mapping for all HTTP methods                 |
| `@PathVariable`   | Extract data from URL path                           |
| `@RequestParam`   | Extract query parameters                             |
| `@RequestBody`    | Accept incoming JSON/XML                             |
| `@ResponseBody`   | Return JSON/XML (auto-included in `@RestController`) |

---
