### ✅ **File Name: `IlpApplication.java`**

This file is the **entry point** for a Spring Boot application.

---

### 📘 **Explanation of Code:**

```java
package com.tcs.ilp;
```

* This defines the **package name**.
* It's a way to organize your classes.
* This class belongs to the package `com.tcs.ilp`.

---

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
```

* These lines import necessary Spring Boot classes:

  * `SpringApplication`: Class used to launch the Spring Boot app.
  * `@SpringBootApplication`: A combination of:

    * `@Configuration`: Marks the class as a source of bean definitions.
    * `@EnableAutoConfiguration`: Tells Spring Boot to auto-configure the application based on dependencies.
    * `@ComponentScan`: Automatically detects components (like @RestController, @Service, etc.) in the package and subpackages.

---

```java
@SpringBootApplication
public class IlpApplication {
```

* `@SpringBootApplication`: Tells Spring Boot to begin auto-configuration and component scanning.
* `IlpApplication` is the **main class** where the application starts running.

---

```java
	public static void main(String[] args) {
		SpringApplication.run(IlpApplication.class, args);
	}
```

* This is the **main method** — the starting point of any Java application.
* `SpringApplication.run(...)` bootstraps the application:

  * It starts the embedded **Tomcat server**.
  * Initializes the **Spring context**.
  * Scans the components and loads beans.
  * Launches your application.

---


### ✅ **File Name: `MenuController.java`**

This file is a **Spring Boot REST controller** that handles HTTP requests related to the **Menu** entity.

---

### 📘 **Explanation of the Code:**

```java
package com.tcs.ilp.controller;
```

* This class belongs to the package `com.tcs.ilp.controller`, which typically contains all controller classes that expose APIs.

---

### 📦 **Imports**

```java
import com.tcs.ilp.dto.MenuRequest;
import com.tcs.ilp.model.Menu;
import com.tcs.ilp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
```

* Importing:

  * DTO (`MenuRequest`): A data transfer object to carry request data.
  * Model (`Menu`): Entity representing menu items.
  * Service (`MenuService`): Business logic layer.
  * REST annotations (`@RestController`, `@RequestMapping`, etc.)
  * `List`: Java utility class for handling collections.

---

### 🎮 **Class Declaration and Annotations**

```java
@RestController
@RequestMapping("/api/menus")
public class MenuController {
```

* `@RestController`: Marks this class as a controller where every method returns a domain object instead of a view.
* `@RequestMapping("/api/menus")`: All endpoints in this class will start with `/api/menus`.

---

### 🧠 **Dependency Injection**

```java
@Autowired
private MenuService menuService;
```

* Injects `MenuService` so the controller can delegate business logic to the service layer.

---

### ✨ **Endpoints**

#### 1. **Create Menu**

```java
@PostMapping
public Menu createMenu(@RequestBody MenuRequest menuRequest) {
    return menuService.addMenu(menuRequest);
}
```

* `@PostMapping`: Handles `POST` requests to `/api/menus`.
* Takes input from request body as `MenuRequest` and adds a new menu item.

---

#### 2. **Get All Veg Menus**

```java
@GetMapping("/veg")
public List<MenuRequest> getVegMenus() {
    return menuService.getVegMenus();
}
```

* `@GetMapping("/veg")`: Handles `GET` requests to `/api/menus/veg`.
* Returns a list of vegetarian menus.

---

#### 3. **Get All Non-Veg Menus**

```java
@GetMapping("/nonveg")
public List<MenuRequest> getNonVegMenus() {
    return menuService.getNonVegMenus();
}
```

* Returns a list of non-vegetarian menus.

---

#### 4. **Update Menu by ID**

```java
@PutMapping("/{menuId}")
public String updateMenu(@PathVariable String menuId, @RequestBody MenuRequest menuRequest) {
    return menuService.updateMenuByMenuId(menuId, menuRequest);
}
```

* `@PutMapping("/{menuId}")`: Handles `PUT` requests to `/api/menus/{menuId}`.
* Uses `menuId` from the path and `MenuRequest` from body to update the menu.

---

#### 5. **Delete Menu by ID**

```java
@DeleteMapping("/{menuId}")
public String updateMenu(@PathVariable String menuId) {
    return menuService.deleteMenuByMenuId(menuId);
}
```

* `@DeleteMapping("/{menuId}")`: Handles `DELETE` requests to `/api/menus/{menuId}`.
* Deletes a menu by its ID.

> ⚠️ Note: Method name `updateMenu` for delete endpoint is misleading — should be renamed to `deleteMenu`.

---

### ✅ **File Name: `MenuRequest.java`**

This file is a **DTO (Data Transfer Object)** used to carry data between the client and server for menu-related operations.

---

### 📘 **Explanation of the Code**

```java
package com.tcs.ilp.dto;
```

* This class belongs to the `com.tcs.ilp.dto` package.
* DTOs are typically placed in this package because they are used to **transfer data** between layers (Controller ↔ Service).

---

### 🔧 **Class Declaration**

```java
public class MenuRequest {
```

* A plain Java class (POJO) that holds form/input data related to a **menu item**.

---

### 🧾 **Private Fields**

```java
private String menuId;
private String name;
private String description;
private String category;
private String type;
private Double cost;
private String status;
```

Each field represents a **menu attribute**:

| Field         | Type     | Meaning                                |
| ------------- | -------- | -------------------------------------- |
| `menuId`      | `String` | Unique ID of the menu item             |
| `name`        | `String` | Name of the dish                       |
| `description` | `String` | Description of the dish                |
| `category`    | `String` | e.g. "Starter", "Main Course"          |
| `type`        | `String` | e.g. "Veg" or "Non-Veg"                |
| `cost`        | `Double` | Price of the dish                      |
| `status`      | `String` | Availability status (e.g. "Available") |

---

### 🛠️ **Getters and Setters**

These methods allow external classes to **read and modify** the private fields in a controlled way:

```java
public String getName() { return name; }
public void setName(String name) { this.name = name; }
// similarly for all other fields...
```

These are necessary when using tools like Spring, Jackson, or Hibernate, which use reflection to set and get values.

---

### 📦 **Typical Use in Application:**

#### ✅ In Controller:

```java
@PostMapping
public Menu createMenu(@RequestBody MenuRequest menuRequest) {
    return menuService.addMenu(menuRequest);
}
```

* Spring will automatically bind incoming JSON request to `MenuRequest` object using setters.

#### 📤 Example JSON sent by frontend:

```json
{
  "menuId": "M101",
  "name": "Paneer Butter Masala",
  "description": "Creamy cottage cheese curry",
  "category": "Main Course",
  "type": "Veg",
  "cost": 180.0,
  "status": "Available"
}
```

---

### ✅ **File Name: `Menu.java`**

This file represents the **JPA entity class** mapped to a database table. It is the **Model** part of the MVC (Model-View-Controller) pattern in a Spring Boot application.

---

### 📘 **Explanation of the Code**

```java
package com.tcs.ilp.model;
```

* The class belongs to the `com.tcs.ilp.model` package, typically used for entity/model classes that map to database tables.

---

### 🧱 **Class Annotations and Mapping**

```java
@Entity
@Table(name = "Menu_2825491")
public class Menu {
```

* `@Entity`: Tells Spring JPA that this class should be mapped to a **database table**.
* `@Table(name = "Menu_2825491")`: Specifies the exact name of the database table. Here, it's `"Menu_2825491"`.

---

### 🔑 **Primary Key Configuration**

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

* `@Id`: Declares `id` as the **primary key** of the table.
* `@GeneratedValue(strategy = GenerationType.IDENTITY)`: The database will automatically generate the primary key (auto-increment).

---

### 📊 **Fields and Column Mapping**

```java
@Column(name = "menu_id", unique = true)
private String menuId;
```

* Mapped to a column named `menu_id`.
* `unique = true` ensures no two rows can have the same `menuId`.

```java
@Column(nullable = false)
private String name;
```

* Column must not be null. Similar constraints are applied to `category`, `type`, `cost`, and `status`.

```java
private String description;
```

* Not marked with `@Column`, so JPA will use the **field name (`description`) as column name** by default.
* Nullable by default.

---

### 📦 **Field Summary**

| Field         | Data Type | Description                | Constraint        |
| ------------- | --------- | -------------------------- | ----------------- |
| `id`          | Long      | Auto-generated primary key | Primary key, Auto |
| `menuId`      | String    | Unique menu identifier     | Unique            |
| `name`        | String    | Name of the menu item      | Not null          |
| `description` | String    | Description of the item    | Optional          |
| `category`    | String    | e.g. "Starter", "Main"     | Not null          |
| `type`        | String    | "Veg"/"Non-Veg"            | Not null          |
| `cost`        | Double    | Price of the item          | Not null          |
| `status`      | String    | e.g. "Available" or "Out"  | Not null          |

---

### 🔄 **Getters and Setters**

* Standard **JavaBean-style** methods to get and set each field.
* Used by Spring and JPA to automatically populate fields during requests and database operations.

---

### 💡 **Use in Application:**

* This class gets **mapped to the database table**.
* Used in the **repository layer** for CRUD operations.
* It is **populated from `MenuRequest` DTO** in service layer and **returned to controller** as a response object.

---

### ✅ **File Name: `MenuRepository.java`**

This file defines the **repository interface** for the `Menu` entity. It provides the database access layer in a Spring Boot application using Spring Data JPA.

---

### 📘 **Explanation of the Code**

```java
package com.tcs.ilp.repository;
```

* This class belongs to the `com.tcs.ilp.repository` package — where all JPA repositories (data access interfaces) are placed.

---

### 🧩 **Repository Interface Declaration**

```java
import com.tcs.ilp.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
```

* Imports the `Menu` entity and Spring Data JPA's `JpaRepository` interface.

```java
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
```

#### 🔍 What's Happening Here?

* You are **extending** `JpaRepository<Menu, Long>`:

  * `Menu` = the entity class.
  * `Long` = the type of the primary key (`id` field in `Menu.java`).

---

### 🚀 **What You Get Automatically from `JpaRepository`**

Without writing any code, Spring Data JPA gives you full CRUD operations:

| Method                | Description                          |
| --------------------- | ------------------------------------ |
| `findAll()`           | Get all menu records                 |
| `findById(Long id)`   | Find a menu by ID                    |
| `save(Menu menu)`     | Insert or update a menu              |
| `deleteById(Long id)` | Delete a menu by ID                  |
| `count()`             | Get total number of records          |
| `existsById(Long id)` | Check if a menu with given ID exists |

---

### 🔧 **When to Add Custom Methods**

If you want to query menus by `menuId`, `type`, or `category`, you can define custom methods like:

```java
List<Menu> findByType(String type);
Menu findByMenuId(String menuId);
void deleteByMenuId(String menuId);
```

Spring Data JPA will **auto-implement** these based on method names.

---

### ✅ **File Name: `MenuService.java`**

This file contains the **business logic** for handling operations on the `Menu` entity. It connects the **controller layer** with the **repository (database)** layer.

---

### 📘 **Explanation of the Code**

```java
package com.tcs.ilp.service;
```

This class belongs to the `service` package, where business logic is written.

---

### 📦 **Class and Dependency Setup**

```java
@Service
public class MenuService {
```

* `@Service`: Marks the class as a **service component**, managed by Spring’s container.

```java
@Autowired
private MenuRepository menuRepository;
```

* Spring will **inject** an instance of `MenuRepository` so we can perform database operations.

---

### 🔨 1. **Add Menu**

```java
public Menu addMenu(MenuRequest menuRequest) {
    Menu menu = new Menu();
    ...
    Menu savedMenu = menuRepository.save(menu);
    String generatedMenuId = "MENU_" + (100 + savedMenu.getId());
    savedMenu.setMenuId(generatedMenuId);
    return menuRepository.save(savedMenu);
}
```

* Converts `MenuRequest` to a `Menu` object.
* Saves it first to generate the auto-incremented `id`.
* Then creates a custom `menuId` like `MENU_101`, sets it, and saves again.
* Returns the saved menu.

---

### 🍃 2. **Get Veg Menus**

```java
public List<MenuRequest> getVegMenus() {
    return menuRepository.findAll().stream()
           .filter(menu -> menu.getType().equalsIgnoreCase("veg"))
           .map(this::convertToRequest)
           .collect(Collectors.toList());
}
```

* Fetches all menus, filters only `veg` type.
* Converts each to `MenuRequest` DTO using `convertToRequest()`.

---

### 🍗 3. **Get Non-Veg Menus**

```java
public List<MenuRequest> getNonVegMenus() {
    return menuRepository.findAll().stream()
           .filter(menu -> menu.getType().equalsIgnoreCase("non veg"))
           .map(this::convertToRequest)
           .collect(Collectors.toList());
}
```

* Same as veg, but filters for `"non veg"` type.

---

### 📝 4. **Update Menu by `menuId`**

```java
public String updateMenuByMenuId(String menuId, MenuRequest menuRequest) {
    Optional<Menu> optionalMenu = menuRepository.findAll().stream()
            .filter(menu -> menu.getMenuId().equalsIgnoreCase(menuId))
            .findFirst();
```

* Searches for a menu by `menuId` using `findAll().stream()` (**less efficient** than using a repository method).
* If found, updates all fields.
* Saves the updated menu and returns a message.

---

### ❌ 5. **Delete Menu by `menuId` if status is "Not Available"**

```java
public String deleteMenuByMenuId(String menuId) {
    Optional<Menu> optionalMenu = menuRepository.findAll().stream()
            .filter(menu -> menu.getMenuId().equalsIgnoreCase(menuId))
            .findFirst();
```

* First, finds the menu by `menuId`.
* If found and status is **not** `"Available"`, deletes it.
* Otherwise, returns `"N/A"` or not found message.

---

### 🔄 6. **Convert Entity to DTO**

```java
private MenuRequest convertToRequest(Menu menu)
```

* Converts `Menu` entity to `MenuRequest` DTO.
* Used while sending data to the frontend.

---

### 📋 **Summary of Responsibilities**

| Method                 | Description                                       |
| ---------------------- | ------------------------------------------------- |
| `addMenu()`            | Adds a new menu and generates `menuId`            |
| `getVegMenus()`        | Returns all menus of type `"veg"`                 |
| `getNonVegMenus()`     | Returns all menus of type `"non veg"`             |
| `updateMenuByMenuId()` | Updates menu fields based on `menuId`             |
| `deleteMenuByMenuId()` | Deletes menu if status is not `"Available"`       |
| `convertToRequest()`   | Converts `Menu` to `MenuRequest` for response use |

---

### ✅ **File Name: `application.properties`**

This file contains **Spring Boot configuration settings** for your application.

---

### 📘 **Explanation of Each Property**

---

#### 1. ✅ **Basic Application Info**

```properties
spring.application.name=ilp
```

* Sets the **application name** to `ilp`.
* Useful in logs and in microservices for service discovery (if using Eureka, etc).

---

#### 2. 🌐 **Server Configuration**

```properties
server.port=8701
```

* Runs the application on **port 8701** instead of default port 8080.
* You access the app at `http://localhost:8701`.

---

#### 3. 💾 **H2 Database Configuration**

```properties
spring.datasource.url=jdbc:h2:mem:testdb
```

* Uses **H2 in-memory database** named `testdb`.
* Data is stored in **RAM** and **reset on every restart**.

```properties
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

* Default H2 DB credentials.
* `sa` is the default user and password is empty.

---

#### 4. 🛠️ **JPA & Hibernate Configuration**

```properties
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

* Tells Hibernate how to generate SQL compatible with H2 database.

```properties
spring.jpa.hibernate.ddl-auto=update
```

* Automatically updates database schema based on your entity classes.
* Other options: `create`, `create-drop`, `validate`, `none`.

```properties
spring.jpa.show-sql=true
```

* Shows SQL queries in the console (helpful for debugging).

---

#### 5. 📊 **H2 Console Settings**

```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

* Enables the **H2 Web Console** at `http://localhost:8701/h2-console`.
* You can use this to interact with your in-memory database in a UI.

> ✅ Use JDBC URL as `jdbc:h2:mem:testdb` and user as `sa` to connect.

---

### 🏁 **Summary Table**

| Setting                   | Purpose                                |
| ------------------------- | -------------------------------------- |
| `spring.application.name` | Name of the app                        |
| `server.port`             | Custom port number                     |
| `spring.datasource.*`     | H2 DB connection details               |
| `spring.jpa.*`            | Auto schema generation and SQL logging |
| `spring.h2.console.*`     | Enable and configure H2 web console    |

---




