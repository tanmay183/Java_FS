
# 🧰 17. Spring Boot Best Practices & Project Structure

These best practices will help you **write clean, scalable, and maintainable Spring Boot applications**, especially useful when preparing for interviews or working on real projects.

---

## 🧱 1. Layered Architecture (Controller → Service → Repository)

### ✅ Why Layered Architecture?

* Clean **separation of concerns**
* Easier to test, extend, and maintain

### 📁 Structure:

```
src/main/java/com/example/app/
│
├── controller/      // REST Controllers
├── service/         // Business logic
├── repository/      // JPA Repositories
├── dto/             // Data Transfer Objects
├── entity/          // JPA Entities
├── mapper/          // DTO ↔ Entity mapping
└── exception/       // Custom exceptions and handlers
```

---

### 🧩 Example Layers:

#### 📌 Controller Layer:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
```

#### 🧠 Service Layer:

```java
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public UserDTO getUserById(Long id) {
        User user = repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapper.toDto(user);
    }
}
```

#### 🗃 Repository Layer:

```java
public interface UserRepository extends JpaRepository<User, Long> {
}
```

---

## 📦 2. DTOs (Data Transfer Objects) and Mappers

### ✅ Why Use DTOs?

* Avoid exposing internal entity structures directly in APIs
* Customize what gets serialized (e.g., exclude password)
* Add computed or derived fields

---

### 📌 Example DTO:

```java
public class UserDTO {
    private Long id;
    private String name;
    private String email;
}
```

### 🔁 Mapper Example (Manual or MapStruct)

#### Manual Mapping:

```java
@Component
public class UserMapper {
    public UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
```

#### Using MapStruct (Optional):

```java
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO dto);
}
```

---

## 🌍 3. Environment-Based Configuration

### 📁 application.properties vs application-{profile}.properties

Use different config files for `dev`, `test`, `prod`.

```properties
# application.properties
spring.profiles.active=dev
```

```properties
# application-dev.properties
server.port=8081
db.url=jdbc:h2:mem:devdb
```

```properties
# application-prod.properties
server.port=8080
db.url=jdbc:mysql://localhost:3306/proddb
```

---

### ✅ Use `@Value` or `@ConfigurationProperties` to inject config:

```java
@Value("${app.name}")
private String appName;
```

```java
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String name;
    private String version;
}
```

---

## 📋 4. Logging and Error Handling Standards

### 📌 Logging Best Practices

* Use `SLF4J + Logback` (default in Spring Boot)
* Avoid `System.out.println()`
* Log at appropriate levels: `info`, `debug`, `warn`, `error`

```java
private static final Logger log = LoggerFactory.getLogger(MyClass.class);

log.info("User created with ID: {}", userId);
log.error("Error fetching user", exception);
```

### 🧾 Logging config (`application.properties`)

```properties
logging.level.org.springframework=INFO
logging.level.com.example=DEBUG
logging.file.name=logs/app.log
```

---

### 🛠 Error Handling Best Practices

Use centralized exception handling via:

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
}
```

---

## 🔢 5. API Versioning

Versioning helps when you want to upgrade APIs without breaking old clients.

### ✅ Common strategies:

#### 🔢 1. URI Versioning

```java
@GetMapping("/api/v1/users")
```

#### 📦 2. Request Header Versioning

```java
@GetMapping("/users")
public ResponseEntity<String> getUserV1(@RequestHeader("X-API-VERSION") String version)
```

#### 🛠 3. Content Negotiation (Accept Header)

```http
Accept: application/vnd.myapp.v1+json
```

---

## ✅ Summary Table

| Practice                   | Description                                         |
| -------------------------- | --------------------------------------------------- |
| Layered Architecture       | Separate concerns (Controller, Service, Repository) |
| DTOs                       | Secure and shape API responses                      |
| Mapper                     | Converts between Entity and DTO                     |
| Profiles                   | Configure based on environments (dev/test/prod)     |
| Logging                    | Use SLF4J with proper levels                        |
| Centralized Error Handling | Use `@ControllerAdvice + @ExceptionHandler`         |
| API Versioning             | Ensure backward compatibility in evolving APIs      |


