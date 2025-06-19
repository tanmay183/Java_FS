# 🔗 18. Spring Boot Microservices (Advanced)

Microservices architecture is used to build **scalable**, **independent**, and **resilient** services that work together to form a complete application.

---

## 📦 Basics of Microservices

### ✅ What are Microservices?

Microservices are a **collection of small, independent services** that:

* Have their own **business logic**
* Use their own **database**
* Communicate over **HTTP (REST)** or **messaging (Kafka, RabbitMQ)**

### ✅ Benefits:

* **Scalability:** Each service scales independently
* **Fault Isolation:** One service crash doesn't bring down the whole system
* **Technology Flexibility:** Services can be written in different languages
* **Faster Deployment:** Small changes can be deployed independently

---

## 🔁 Communication Between Microservices

There are two common ways:

### ✅ 1. **REST (Manual with `RestTemplate` or `WebClient`)**

```java
@RestTemplate
public class UserClient {

    public String getUserById(Long id) {
        return restTemplate.getForObject("http://localhost:8081/users/" + id, String.class);
    }
}
```

> ❌ Hardcoded URLs are not scalable in large systems.

---

### ✅ 2. **Feign Client (Declarative REST Client)**

Better alternative to `RestTemplate`. Works with service discovery.

```java
@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);
}
```

> ✅ Feign + Eureka makes inter-service communication scalable and declarative.

---

## 🔍 Eureka Server (Service Discovery)

Spring Cloud Eureka helps **register** and **discover services dynamically**.

### ✅ Eureka Server Setup:

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp.class, args);
    }
}
```

`application.yml`:

```yaml
server:
  port: 8761

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

📍 Visit: `http://localhost:8761` to see the dashboard

---

### ✅ Eureka Client (Any Microservice)

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

`application.yml`:

```yaml
spring:
  application:
    name: user-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

---

## 🛠 Spring Cloud Config (Centralized Configuration)

### ✅ Problem:

Each microservice has its own `application.yml`, hard to manage.

### ✅ Solution:

**Spring Cloud Config Server** provides centralized configuration via Git, file system, or vault.

### 🏗 Config Server Setup:

1. **Add dependency:**

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

2. **Enable Config Server:**

```java
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApp.class, args);
    }
}
```

3. **application.yml:**

```yaml
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/myorg/config-repo
```

> Clients read config from: `http://localhost:8888/{application}-{profile}.yml`

---

## 🧰 API Gateway (Routing Microservices)

API Gateway acts as a **single entry point** for all microservices.

### ✅ Options:

* **Spring Cloud Gateway (modern)**
* **Netflix Zuul (legacy)**

---

### 🔧 Spring Cloud Gateway Example:

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

```yaml
server:
  port: 8080

spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/users/**
```

> Now all calls to `/users/**` are routed to the `user-service`

✅ Gateway also supports:

* Load balancing
* Path rewriting
* Authentication/Authorization filters

---

## 💣 Circuit Breaker (Resilience4j / Hystrix)

**Purpose:** Prevents cascading failures when a microservice is down or slow.

---

### 🔧 Resilience4j (Recommended)

```xml
<dependency>
  <groupId>io.github.resilience4j</groupId>
  <artifactId>resilience4j-spring-boot2</artifactId>
</dependency>
```

```java
@CircuitBreaker(name = "userService", fallbackMethod = "fallbackGetUser")
public UserDTO getUserById(Long id) {
    return userClient.getUserById(id);
}

public UserDTO fallbackGetUser(Long id, Throwable t) {
    return new UserDTO(id, "Default", "user@example.com");
}
```

✅ Features:

* Circuit Breaker
* Retry
* Rate Limiter
* Bulkhead

---

## ✅ Summary Table

| Component              | Purpose                                           |
| ---------------------- | ------------------------------------------------- |
| Microservices          | Independent services with single responsibility   |
| Feign                  | Declarative REST client for service communication |
| Eureka Server          | Service registry and discovery                    |
| Spring Cloud Config    | Centralized externalized config                   |
| Spring Cloud Gateway   | Route and filter requests to services             |
| Resilience4j / Hystrix | Prevent cascading failures, fallback mechanism    |


