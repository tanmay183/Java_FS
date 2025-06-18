# 🛠 2. Spring Boot Project Setup (In-Depth Theory)

---

## 📦 1. Setting Up Project Using Spring Initializr

**Spring Initializr** is an official web-based project generator for Spring Boot applications.

🔗 Website: [https://start.spring.io](https://start.spring.io)

### 💡 Why Use Spring Initializr?

* Automatically generates a pre-configured Spring Boot project.
* Lets you choose the **Java version, dependencies, packaging (JAR/WAR), and build tool (Maven/Gradle)**.
* Saves time and avoids manual setup.

### 🧭 How to Use:

1. Go to [https://start.spring.io](https://start.spring.io)
2. Choose:

   * Project: Maven / Gradle
   * Language: Java
   * Spring Boot Version: (Latest stable)
   * Group: e.g., `com.example`
   * Artifact: e.g., `demo`
   * Dependencies: e.g., Web, JPA, MySQL, Lombok
3. Click **Generate**, and a `.zip` file will download.
4. Unzip and import into your IDE.

---

## 🧰 2. Maven vs Gradle for Spring Boot

Spring Boot supports two major build tools:

### 🔧 Maven

* **XML-based** build tool
* Well-documented, widely used
* Follows a strict project structure

📁 File: `pom.xml`

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

🟢 Use when:

* You need stability and standardization
* You're familiar with XML

---

### ⚡ Gradle

* **Groovy/Kotlin DSL-based**
* Faster and more flexible than Maven
* Concise and less verbose

📁 File: `build.gradle`

```groovy
dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

🟢 Use when:

* You want faster builds
* You’re using Kotlin or Android
* You prefer script-style configs

---

## 🖥 3. Setting Up in IDEs (IntelliJ / Eclipse)

### ✅ IntelliJ IDEA

Steps:

1. File → New → Project from Existing Sources → Select downloaded Spring Boot folder
2. Select `pom.xml` or `build.gradle`
3. IntelliJ will import and auto-detect Spring Boot
4. Add required plugins: Spring Boot, Lombok (if used)
5. Right-click `DemoApplication.java` → Run

### ✅ Eclipse / STS (Spring Tool Suite)

Steps:

1. File → Import → Existing Maven or Gradle Project
2. Choose the unzipped Spring Boot folder
3. Eclipse will auto-build and import it
4. Install Spring IDE plugin for better support
5. Run using `@SpringBootApplication` main class

---

## 📁 4. Spring Boot Project Structure Overview

When generated from Spring Initializr, your project will look like this:

```
demo/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   └── DemoApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/       <-- for static files (HTML, CSS, JS)
│   │       ├── templates/    <-- for Thymeleaf templates
│   │       └── application.yml (optional)
│   └── test/
│       └── java/...
├── pom.xml or build.gradle
```

### 🔍 Explanation:

* `src/main/java`: Your main Java code
* `src/main/resources`: Configuration files, templates, static files
* `application.properties` or `application.yml`: External config
* `DemoApplication.java`: Entry point of the Spring Boot application

---

## ⚙️ 5. Application Properties vs YAML

### ✅ application.properties

* Key-value format
* Simpler syntax for small config

```properties
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/testdb
spring.datasource.username=root
spring.datasource.password=pass
```

### ✅ application.yml (YAML format)

* Hierarchical
* More readable for complex structures

```yaml
server:
  port: 8081
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/testdb
    username: root
    password: pass
```

### 🤔 Which to Choose?

* Use `.properties` for small projects or beginners
* Use `.yml` for complex configs or microservices

**Note**: You can have both files, but Spring Boot prioritizes `application.properties` if both exist.

---

## ▶️ 6. Running Spring Boot Applications

You can run Spring Boot apps in several ways:

### 🔘 Method 1: From IDE

* Locate the `@SpringBootApplication` main class
* Right-click → Run

```java
@SpringBootApplication
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
```

### 🔘 Method 2: Using Maven

```bash
mvn spring-boot:run
```

### 🔘 Method 3: Using Gradle

```bash
./gradlew bootRun
```

### 🔘 Method 4: Running JAR file

After packaging:

```bash
mvn clean install
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

---

## ✅ Summary Table

| Feature            | Explanation                                                                  |
| ------------------ | ---------------------------------------------------------------------------- |
| Spring Initializr  | Online tool to generate Spring Boot starter projects                         |
| Maven vs Gradle    | Maven = XML-based, Gradle = script-based                                     |
| IDE Setup          | IntelliJ and Eclipse both support Spring Boot well                           |
| Project Structure  | Standard layout with Java files in `src/main/java` and config in `resources` |
| Properties vs YAML | `.properties` = simple key-value; `.yml` = hierarchical and readable         |
| Running the App    | Can run via IDE, Maven, Gradle, or compiled `.jar`                           |

