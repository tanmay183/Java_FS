# 🔗 3. Dependency Injection (DI) and Inversion of Control (IoC)

This concept is **fundamental** to Spring Boot and is heavily used in every Spring application. Let’s break it down in simple terms with code examples.

---

## 🔄 What is Inversion of Control (IoC)?

### 🧠 Definition:

**Inversion of Control (IoC)** is a design principle where control of object creation and dependency management is **inverted** — instead of the developer manually creating objects, the **framework (Spring)** does it for you.

### 💡 In Plain English:

Normally, you write code like:

```java
Engine engine = new Engine();
Car car = new Car(engine);
```

With **IoC**, you tell Spring:

> "Hey Spring, I need a Car. You figure out how to create the Engine and wire it for me."

Spring will **create**, **manage**, and **inject** these objects automatically.

---

## 🧩 What is Dependency Injection (DI)?

### 🧠 Definition:

**Dependency Injection** is a specific implementation of IoC, where Spring automatically **injects** required dependencies (objects) into another object.

Instead of:

```java
Car car = new Car(new Engine());
```

You write:

```java
@Component
class Car {
  @Autowired
  Engine engine;
}
```

Spring will inject the `Engine` object **into** the `Car` class.

---

## 🎯 Common Annotations in DI

---

### ✅ `@Component`

Marks a **generic Spring-managed component**.

```java
@Component
public class Engine {
    public String start() {
        return "Engine started";
    }
}
```

> Spring will scan this class and manage it as a **bean** in the container.

---

### ✅ `@Service`

Used for **service layer classes** (business logic). It behaves like `@Component`.

```java
@Service
public class CarService {
    public String getService() {
        return "Car is being serviced";
    }
}
```

> Semantically clearer for services than using `@Component`.

---

### ✅ `@Repository`

Used for **data access layer** (DAO classes). It also behaves like `@Component` but adds **exception translation** for database exceptions.

```java
@Repository
public class CarRepository {
    public String saveCar() {
        return "Car saved to DB";
    }
}
```

---

## 🎯 `@Autowired`: Injecting Dependencies Automatically

### ✅ Field Injection (not recommended for testing):

```java
@Component
public class Car {

    @Autowired
    private Engine engine;

    public void drive() {
        System.out.println(engine.start());
    }
}
```

> Spring looks for an `Engine` bean and injects it into `Car`.

---

## 🛠️ Constructor vs Setter Injection

### ✅ 1. **Constructor Injection** (Recommended)

```java
@Component
public class Car {
    private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        System.out.println(engine.start());
    }
}
```

* ✅ Recommended because it ensures that the dependency is **never null**.
* ✅ Great for testing and immutability.

---

### ✅ 2. **Setter Injection**

```java
@Component
public class Car {
    private Engine engine;

    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
```

* ➕ Optional dependencies
* ➖ Less preferred for mandatory dependencies

---

## ⚖️ `@Qualifier` vs `@Primary`: Handling Multiple Beans

### 💥 Problem:

If you have **two beans** of the same type, Spring doesn’t know which one to inject.

```java
@Component
public class PetrolEngine implements Engine {}

@Component
public class DieselEngine implements Engine {}
```

### 🛠️ Solution 1: `@Qualifier`

Use `@Qualifier` to specify which bean to use.

```java
@Component
public class Car {

    private Engine engine;

    @Autowired
    public Car(@Qualifier("dieselEngine") Engine engine) {
        this.engine = engine;
    }
}
```

### 🛠️ Solution 2: `@Primary`

Mark one bean as the **default** choice.

```java
@Primary
@Component
public class DieselEngine implements Engine {}
```

Then Spring uses `DieselEngine` unless told otherwise.

---

## 🔍 What is Component Scanning?

Spring needs to **scan** your classes to find annotations like `@Component`, `@Service`, etc.

### ✅ How It Works:

`@SpringBootApplication` includes:

```java
@ComponentScan(basePackages = "com.example")
```

It tells Spring:

> “Scan all classes in `com.example` and below for annotations.”

So if your classes are in the correct package, Spring **automatically finds and registers them** as beans.

---

## ✅ Summary Table

| Concept                    | Description                                                            |
| -------------------------- | ---------------------------------------------------------------------- |
| IoC (Inversion of Control) | Framework controls object creation and wiring                          |
| DI (Dependency Injection)  | Spring injects required dependencies (via constructor/setter/field)    |
| `@Component`               | Marks a class as a generic Spring-managed bean                         |
| `@Service`                 | Indicates a service layer component                                    |
| `@Repository`              | Indicates a data access layer component (adds exception handling)      |
| `@Autowired`               | Tells Spring to inject the dependency automatically                    |
| `@Qualifier`               | Used to choose the correct bean when multiple of the same type exist   |
| `@Primary`                 | Marks a bean as the default when multiple beans of the same type exist |
| Constructor Injection      | Recommended, safe, and testable                                        |
| Setter Injection           | Useful for optional dependencies                                       |
| Component Scanning         | Automatically detects and registers beans in specified packages        |

---

