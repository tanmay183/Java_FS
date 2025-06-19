
# 📄 11. Spring Boot Actuator

## ✅ What is Spring Boot Actuator?

Spring Boot Actuator is a powerful tool that helps you **monitor and manage your application** in production without writing custom code.

It provides **built-in REST endpoints** to expose:

* Application health
* Metrics
* Environment settings
* Thread dumps
* Custom info
* Log levels

> 🧠 Think of it as a health-monitoring dashboard via HTTP endpoints.

---

## 🔌 Enable Actuator

### ✅ Maven Dependency:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

---

## 🚪 Common Built-in Endpoints

| Endpoint            | Purpose                           |
| ------------------- | --------------------------------- |
| `/actuator/health`  | Application health status         |
| `/actuator/info`    | App info (name, version, etc.)    |
| `/actuator/metrics` | Performance metrics (memory, CPU) |
| `/actuator/env`     | All environment properties        |
| `/actuator/beans`   | All Spring beans loaded           |
| `/actuator/loggers` | Configure log levels at runtime   |

### 🔧 Enable endpoints in `application.properties`:

```properties
management.endpoints.web.exposure.include=health,info,metrics,env
```

Or allow **all**:

```properties
management.endpoints.web.exposure.include=*
```

---

## 🛠 Custom Info Endpoint

You can add custom data to `/info`:

```properties
management.endpoint.info.enabled=true

info.app.name=My Spring Boot App
info.app.version=1.0.0
```

Or via Java:

```java
@Bean
public InfoContributor customInfo() {
    return builder -> builder.withDetail("custom", Map.of("team", "Tanmay Devs"));
}
```

---

## 🧱 Create Custom Endpoint

```java
@Component
@Endpoint(id = "greet")
public class GreetEndpoint {

    @ReadOperation
    public String sayHello() {
        return "Hello from custom actuator endpoint!";
    }
}
```

📍 Access via: `/actuator/greet`

---

## 🔒 Securing Actuator Endpoints

By default, **`/actuator/health` and `/actuator/info`** are public.

To secure others:

```properties
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=when-authorized
```

### In Security Config:

```java
http
  .authorizeHttpRequests()
  .requestMatchers("/actuator/**").hasRole("ADMIN")
  .anyRequest().permitAll();
```

---

## ✅ Summary: Spring Boot Actuator

| Feature             | Benefit                                            |
| ------------------- | -------------------------------------------------- |
| Built-in endpoints  | Health, metrics, info, env, etc.                   |
| `/actuator/health`  | Check if app is up                                 |
| `/actuator/info`    | Show custom info from config                       |
| `/actuator/metrics` | Memory, JVM, CPU stats                             |
| Custom endpoints    | Build your own with `@Endpoint` + `@ReadOperation` |
| Securing Actuator   | Limit access to admins using Spring Security       |

---

# 📦 12. Spring Boot Profiles and Configuration

## 🎯 What Are Spring Profiles?

Spring Profiles allow you to define **multiple configurations** for different environments like:

* `dev` – Development
* `test` – Testing
* `prod` – Production

> Spring will load only the beans/config for the active profile.

---

## 🧪 Why Use Profiles?

To **isolate environment-specific settings** like:

* DB credentials
* Logging levels
* Email server config
* Debug flags

---

## ✅ `@Profile` Annotation

Used to conditionally load a bean based on the active profile.

```java
@Component
@Profile("dev")
public class DevMailService implements MailService {
    public void send() {
        System.out.println("Development Mail Service");
    }
}
```

```java
@Component
@Profile("prod")
public class ProdMailService implements MailService {
    public void send() {
        System.out.println("Production Mail Service");
    }
}
```

---

## 🗂 application.properties vs application-{profile}.properties

### 📘 Default file:

```properties
# application.properties (shared across all profiles)
app.name=GenericApp
```

### 📘 Profile-specific file:

```properties
# application-dev.properties
app.name=DevApp

# application-prod.properties
app.name=ProdApp
```

### ✅ Activate Profile (ways):

#### 1. In `application.properties`:

```properties
spring.profiles.active=dev
```

#### 2. Command line:

```bash
java -jar app.jar --spring.profiles.active=prod
```

#### 3. In environment variable:

```bash
SPRING_PROFILES_ACTIVE=prod
```

---

## 🧾 Externalized Configuration

Spring supports configs from multiple sources:

* `.properties` or `.yml` files
* Command-line arguments
* Environment variables
* `@Value("${property}")` injection

---

## 🧂 YAML Configuration Example

### 📁 application.yml

```yaml
spring:
  profiles:
    active: dev

---
spring:
  config:
    activate:
      on-profile: dev
  datasource:
    url: jdbc:h2:mem:devdb
    username: dev
    password: dev

---
spring:
  config:
    activate:
      on-profile: prod
  datasource:
    url: jdbc:mysql://prod-db-url
    username: prod
    password: prod
```

---

## ✅ Summary: Spring Profiles

| Feature                            | Description                                               |
| ---------------------------------- | --------------------------------------------------------- |
| `@Profile("dev")`                  | Bean active only if `dev` profile is active               |
| `application-{profile}.properties` | Config for each profile                                   |
| `spring.profiles.active`           | Used to set the current active profile                    |
| YAML Config                        | Alternative to `.properties` with hierarchical format     |
| External Config Support            | Allows config from env vars, CLI args, system props, etc. |

