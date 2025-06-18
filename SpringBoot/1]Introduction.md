
## 🔍 What is Spring Boot?

**Spring Boot** is a **Java-based framework** built on top of the **Spring Framework**.
Its goal is to **simplify** the development of production-ready Spring applications by:

* Reducing boilerplate code
* Eliminating complex XML configurations
* Providing **auto-configuration** and **starter dependencies**

📌 **Think of Spring Boot like an automatic car** — you don't have to manually change gears (XML, configurations); Spring Boot does it for you.

---

## ✅ Advantages Over Traditional Spring Framework

| Spring Framework             | Spring Boot                        |
| ---------------------------- | ---------------------------------- |
| Manual configuration needed  | Auto-configuration via annotations |
| Need to set up servlet, JARs | Comes with embedded Tomcat/Jetty   |
| Verbose XML configurations   | Annotation-based + YAML/Properties |
| Steep learning curve         | Easy to set up and quick to deploy |

### 🔥 Key Advantages

* 💡 **Auto Configuration** — Spring Boot configures itself based on the dependencies you add.
* 🚀 **Faster Development** — Create production-ready apps in minutes.
* 📦 **Embedded Servers** — No need to deploy WAR files manually.
* 🔁 **Microservice Ready** — Easy to build REST APIs and microservices.
* 📊 **Actuator** — Built-in health check and metrics support.

---

## ⚔️ Differences Between Spring and Spring Boot

| Feature           | Spring Framework         | Spring Boot                     |
| ----------------- | ------------------------ | ------------------------------- |
| Setup             | Manual                   | Auto                            |
| Deployment        | Requires external server | Embedded servers (Tomcat/Jetty) |
| XML Config        | Required                 | Not required                    |
| Starter Templates | Not available            | Yes (`spring-boot-starter-*`)   |
| REST API setup    | Manual                   | Auto with few lines             |

---

## 🏗️ Spring Boot Architecture

```plaintext
            +-----------------------+
            |   Spring Boot App     |
            +-----------------------+
                     |
         +-----------+-----------+
         |                       |
 Auto Configuration        Starter Dependencies
         |
   Spring Boot Modules
         |
   Core Spring Framework
```

### 🔑 Components:

1. **Spring Boot Starters** – Auto bundles of dependencies
2. **Spring Boot AutoConfigurator** – Automatically configures beans
3. **Spring Boot CLI** – Command line tool to run groovy-based apps
4. **Spring Boot Actuator** – Provides health, metrics, logs
5. **Embedded Web Server** – Comes with Tomcat/Jetty/Undertow

---

## 📦 Starter Dependencies

Starter dependencies = **Predefined dependency sets** to avoid manual JAR handling.

### ✨ Common Starters:

| Starter                        | Purpose                             |
| ------------------------------ | ----------------------------------- |
| `spring-boot-starter-web`      | Web app + REST API support          |
| `spring-boot-starter-data-jpa` | Database access using JPA           |
| `spring-boot-starter-security` | Add Spring Security to app          |
| `spring-boot-starter-test`     | Testing frameworks (JUnit, Mockito) |

### 🧪 Example (`pom.xml`):

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
</dependencies>
```

No need to manually add Spring MVC, Tomcat, Jackson, etc. — all are included automatically.

---

## ⚙️ Auto-Configuration

Spring Boot automatically configures your app based on the libraries present in the classpath.

🧠 **Example**:
If `spring-boot-starter-web` is present, Spring Boot:

* Sets up `DispatcherServlet`
* Registers `Jackson` for JSON
* Adds `Tomcat` as a server

### 🔍 How it works:

* Uses `@EnableAutoConfiguration` (usually via `@SpringBootApplication`)
* Reads `application.properties` or `application.yml`
* Uses `spring.factories` under the hood

### 👨‍💻 Code Example:

```java
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```

---

## 💻 Spring Boot CLI (Command Line Interface)

**Spring Boot CLI** lets you run Groovy scripts without compiling or creating a project.

### 🛠️ Features:

* Rapid prototyping
* Auto-imports common Spring classes
* Zero configuration needed

### 🧪 Example CLI App:

```groovy
@RestController
class HelloController {
    @GetMapping("/")
    String home() {
        "Hello from Spring Boot CLI"
    }
}
```

### ▶️ Run it via terminal:

```bash
spring run HelloController.groovy
```

---

## ✅ Summary Table

| Feature              | Spring Boot Advantage                    |
| -------------------- | ---------------------------------------- |
| Setup                | Auto-configured, minimal setup           |
| Deployment           | Embedded Tomcat/Jetty, no WAR needed     |
| Dependencies         | Starter templates simplify configuration |
| Production Readiness | Actuator for health checks & metrics     |
| Microservice Support | Lightweight, ideal for RESTful services  |

---

## 📘 Mini Spring Boot App Example

```java
@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}
```

📄 `application.properties`:

```properties
server.port=8081
```

