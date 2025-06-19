# 🧾 6. Validation in Spring Boot

Spring Boot supports **data validation** using **Bean Validation (JSR-380)**, allowing you to validate user input at the **controller, service, or entity level** easily.

---

## ✅ What is Bean Validation (JSR-380)?

JSR-380 is the **official Java specification for Bean Validation**, implemented by **Hibernate Validator** (included by default in Spring Boot).

### 🎯 Purpose:

* Validate incoming data in a REST API or form
* Ensure objects meet certain constraints (e.g., non-null, length, range)

---

## 🔍 How Validation Works in Spring Boot

1. Annotate your model/entity fields with validation constraints.
2. Use `@Valid` or `@Validated` to trigger validation.
3. If validation fails, Spring automatically returns a **400 Bad Request** with error messages.

---

## 📦 Maven Dependency (usually auto-included):

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

✅ It includes **Hibernate Validator**.

---

## 🎯 Common Constraint Annotations

| Annotation      | Description                                 |
| --------------- | ------------------------------------------- |
| `@NotNull`      | Field must not be null                      |
| `@Size`         | Defines min and max length of a String/List |
| `@Min` / `@Max` | Minimum / Maximum value (for numbers)       |
| `@Email`        | Validates proper email format               |
| `@Pattern`      | Regex pattern for string                    |
| `@NotBlank`     | Must not be null and trimmed length > 0     |
| `@NotEmpty`     | Must not be null or empty                   |

---

## 🔧 Example: Model with Constraints

```java
public class User {

    @NotNull(message = "ID cannot be null")
    private Integer id;

    @Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Min(value = 18, message = "Minimum age is 18")
    @Max(value = 60, message = "Maximum age is 60")
    private int age;

    // Getters and setters
}
```

---

## ✅ `@Valid` vs `@Validated`

| Annotation   | Use With          | Validates nested beans? | Use Case                             |
| ------------ | ----------------- | ----------------------- | ------------------------------------ |
| `@Valid`     | Javax annotation  | ✅ Yes                   | Method parameters, nested validation |
| `@Validated` | Spring annotation | ❌ No (by default)       | Enables group-based validation       |

### Example with `@Valid` in Controller:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok("User is valid");
    }
}
```

> If validation fails, Spring returns 400 with default or custom messages.

---

## ❌ Default Error Response Example:

```json
{
  "timestamp": "2025-06-18T10:00:00",
  "status": 400,
  "errors": [
    "Name must be between 3 and 15 characters",
    "Email must be valid"
  ]
}
```

---

## 🧠 Custom Error Handling (Optional but Recommended)

You can use `@ControllerAdvice` + `@ExceptionHandler` to format validation errors nicely.

```java
@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(err -> err.getField() + ": " + err.getDefaultMessage())
            .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
```

---

## 🛠️ Custom Validator (Advanced)

If built-in annotations aren’t enough, you can create your **own validator**.

### Step 1: Create custom annotation

```java
@Constraint(validatedBy = EvenNumberValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Even {
    String message() default "Number must be even";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

### Step 2: Create Validator Class

```java
public class EvenNumberValidator implements ConstraintValidator<Even, Integer> {
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value % 2 == 0;
    }
}
```

### Step 3: Use in your model

```java
public class NumberHolder {
    @Even
    private Integer number;
}
```

---

## 📘 Summary Table

| Concept            | Description                                                                  |
| ------------------ | ---------------------------------------------------------------------------- |
| JSR-380            | Official Java validation specification (Bean Validation)                     |
| `@Valid`           | Validates incoming object and nested fields                                  |
| `@Validated`       | Enables group-based or selective validation                                  |
| Common Constraints | `@NotNull`, `@Size`, `@Email`, `@Min`, `@Max`                                |
| Custom Validator   | Define your own validation logic using `@Constraint` + `ConstraintValidator` |
| Error Handling     | Use `@ControllerAdvice` to customize error output                            |

