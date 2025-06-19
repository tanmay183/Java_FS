# 🗃 5. Spring Boot with Database (Spring Data JPA)

This topic helps you **integrate your Spring Boot applications with databases** (like MySQL, PostgreSQL, H2), and perform powerful CRUD and custom queries using **Spring Data JPA**.

---

## 🔍 Introduction to Spring Data JPA

**Spring Data JPA** is a part of the **Spring Data** project that simplifies database operations using **JPA (Java Persistence API)**.

### 🧠 What is JPA?

* JPA is a Java specification for **mapping Java objects to database tables** (ORM – Object Relational Mapping).
* Spring Data JPA provides **abstractions** and boilerplate removal on top of JPA.

### ✅ Key Features:

* Auto-generated CRUD operations (save, findById, delete, etc.)
* Auto-creation of queries from method names
* Pagination and sorting support
* Easy integration with relational databases

---

## ⚙️ Setting Up JPA and Database

You can use:

* ✅ H2 (in-memory, great for testing)
* ✅ MySQL (popular relational database)
* ✅ PostgreSQL (open-source enterprise-grade)

### 🧾 Maven Dependencies (example for MySQL):

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <scope>runtime</scope>
</dependency>
```

### 🗂 `application.properties` Example (MySQL):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 🔁 `ddl-auto` options:

* `none`: Don't touch schema
* `update`: Update schema (default for dev)
* `create`: Drops and creates schema on every start
* `create-drop`: Creates schema and drops on shutdown
* `validate`: Validates schema, doesn't modify

---

## 🧱 Entity, Repository, and DAO Patterns

---

### 🧱 `@Entity`: Maps a Java class to a DB table

```java
import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;

    // Getters and Setters
}
```

* `@Entity` → This class maps to a table
* `@Id` → Primary key
* `@GeneratedValue` → Auto-increment

---

### 📦 Repository Interface: `JpaRepository`

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
```

> This gives you all CRUD operations out of the box!

---

## 🔁 CRUD Operations Example

### ✅ 1. Save Entity:

```java
Student s = new Student();
s.setName("Tanmay");
s.setAge(22);
repository.save(s);
```

### ✅ 2. Get All:

```java
List<Student> all = repository.findAll();
```

### ✅ 3. Get by ID:

```java
Optional<Student> optional = repository.findById(1);
```

### ✅ 4. Update:

```java
Student s = repository.findById(1).get();
s.setName("Updated Name");
repository.save(s);
```

### ✅ 5. Delete:

```java
repository.deleteById(1);
```

---

## 🔍 Custom Queries Using:

---

### ✨ 1. JPQL (Java Persistence Query Language)

JPQL uses **entity names**, not table names.

```java
@Query("SELECT s FROM Student s WHERE s.name = ?1")
List<Student> findByName(String name);
```

---

### ✨ 2. Native SQL Query

Use actual SQL with `nativeQuery = true`.

```java
@Query(value = "SELECT * FROM student WHERE age > ?1", nativeQuery = true)
List<Student> findByAgeGreaterThan(int age);
```

---

### ✨ 3. Query Methods (Derived Queries)

Spring can generate queries just from method names.

```java
List<Student> findByName(String name);
List<Student> findByAgeGreaterThan(int age);
List<Student> findByNameAndAge(String name, int age);
```

> You don’t need to write `@Query` in most cases!

---

## 📚 Pagination and Sorting

### ✅ Pagination Support:

Spring provides the `Pageable` interface.

```java
Page<Student> findAll(Pageable pageable);
```

### 🧪 Example:

```java
Pageable page = PageRequest.of(0, 5); // page 0, size 5
Page<Student> studentPage = repository.findAll(page);
List<Student> students = studentPage.getContent();
```

---

### ✅ Sorting Support:

```java
List<Student> findAll(Sort.by("name").ascending());
```

---

## ✅ Summary Table

| Component                | Description                                    |
| ------------------------ | ---------------------------------------------- |
| `@Entity`                | Maps a Java class to a table                   |
| `@Id`, `@GeneratedValue` | Denote primary key and auto-increment strategy |
| `JpaRepository`          | Interface for CRUD and pagination methods      |
| `@Query`                 | Custom JPQL or native SQL queries              |
| Query Method Naming      | Auto-generated queries from method names       |
| `Pageable`               | Enables pagination with `PageRequest`          |
| `Sort`                   | Enables sorting on fields                      |

---

### 🧱 Folder Structure:

```
src/
├── main/
│   └── java/
│       └── com.example.demo/
│           ├── entity/
│           │   └── Student.java
│           ├── repository/
│           │   └── StudentRepository.java
│           ├── controller/
│           │   └── StudentController.java
│           └── DemoApplication.java
│
└── resources/
    └── application.properties
```


