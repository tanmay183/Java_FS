# 🔁 14. Spring Boot and Scheduling

Spring Boot allows you to **run tasks automatically at fixed intervals or schedules**, just like a cron job.

---

## 🕒 `@Scheduled` Annotation

The `@Scheduled` annotation is used to mark a method as a **scheduled task**.

```java
@Scheduled(fixedDelay = 5000)
public void runEvery5Sec() {
    System.out.println("Running every 5 seconds...");
}
```

> ✅ The method must return `void`, and **cannot accept arguments.**

---

## ⚙️ Enable Scheduling

To use `@Scheduled`, you **must enable it** in your main config class:

```java
@SpringBootApplication
@EnableScheduling
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```

---

## ⏲️ Scheduling Options

### 1. `fixedDelay`

Runs **after** the previous execution **completes**.

```java
@Scheduled(fixedDelay = 5000) // 5 seconds after last task finished
```

### 2. `fixedRate`

Runs every X milliseconds **regardless of completion** of the last run.

```java
@Scheduled(fixedRate = 5000) // Run every 5 seconds
```

### 3. `cron`

Provides **cron expression-based scheduling**.

```java
@Scheduled(cron = "0 0/1 * * * *") // Every minute
```

> Format: `second minute hour day month day-of-week`

| Field        | Allowed values  |
| ------------ | --------------- |
| Seconds      | 0–59            |
| Minutes      | 0–59            |
| Hours        | 0–23            |
| Day of month | 1–31            |
| Month        | 1–12 or JAN–DEC |
| Day of week  | 0–6 or SUN–SAT  |

### 4. Cron Example:

```java
@Scheduled(cron = "0 30 10 * * ?") // At 10:30 AM every day
```

---

## ✅ Summary: Scheduling

| Annotation          | Use Case                                |
| ------------------- | --------------------------------------- |
| `@Scheduled`        | Mark method for scheduled execution     |
| `@EnableScheduling` | Enables scheduling in Spring Boot app   |
| `fixedRate`         | Executes every X milliseconds           |
| `fixedDelay`        | Executes X ms after last execution ends |
| `cron`              | Use cron format for flexible schedules  |

---

# 🧪 15. Testing in Spring Boot

Spring Boot supports powerful testing using **JUnit**, **Mockito**, and **Spring Test annotations**.

---

## ✅ 1. Unit Testing with JUnit & Mockito

### Example: Service Layer Unit Test

**Service Class:**

```java
@Service
public class CalculatorService {
    public int add(int a, int b) {
        return a + b;
    }
}
```

**Test Class:**

```java
@SpringBootTest
public class CalculatorServiceTest {

    @Autowired
    private CalculatorService service;

    @Test
    void testAdd() {
        assertEquals(5, service.add(2, 3));
    }
}
```

---

## ✅ 2. Mocking with Mockito

Use Mockito to **mock dependencies** in unit tests.

**Controller + Service:**

```java
@RestController
public class MyController {

    @Autowired
    MyService service;

    @GetMapping("/hello")
    public String sayHello() {
        return service.getHello();
    }
}
```

```java
@Service
public class MyService {
    public String getHello() {
        return "Hello!";
    }
}
```

**Test with Mockito:**

```java
@ExtendWith(MockitoExtension.class)
public class MyControllerTest {

    @InjectMocks
    MyController controller;

    @Mock
    MyService service;

    @Test
    void testSayHello() {
        when(service.getHello()).thenReturn("Mocked Hello");
        assertEquals("Mocked Hello", controller.sayHello());
    }
}
```

---

## ✅ 3. `@SpringBootTest`

* Loads **full application context**
* Useful for **integration tests**

```java
@SpringBootTest
class FullContextTest {

    @Test
    void contextLoads() {
        // Test if app starts without issues
    }
}
```

---

## ✅ 4. `@WebMvcTest` – Controller Layer Only

* Loads **only web layer**
* Does **not load full context or database**

```java
@WebMvcTest(MyController.class)
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyService service;

    @Test
    void testSayHello() throws Exception {
        when(service.getHello()).thenReturn("Hi");

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hi"));
    }
}
```

> 🔧 `MockMvc` is used to simulate HTTP requests and verify response.

---

## ✅ 5. Testcontainers (Advanced – Optional)

**Testcontainers** allow you to spin up **real Docker containers** for:

* MySQL/PostgreSQL
* Redis
* Kafka
* RabbitMQ

🔧 Example: Start real MySQL DB for integration tests:

```java
@Testcontainers
@SpringBootTest
public class MySQLContainerTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8")
            .withDatabaseName("testdb")
            .withUsername("user")
            .withPassword("pass");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Test
    void testWithRealMySQL() {
        // test logic here
    }
}
```

---

## ✅ Summary: Testing in Spring Boot

| Tool / Annotation | Purpose                                         |
| ----------------- | ----------------------------------------------- |
| `@SpringBootTest` | Full context integration test                   |
| `@WebMvcTest`     | Controller layer test using `MockMvc`           |
| `MockMvc`         | Simulate HTTP requests                          |
| `@MockBean`       | Mock Spring Bean dependencies                   |
| JUnit             | Test framework                                  |
| Mockito           | Mocking framework                               |
| Testcontainers    | Dockerized real DB/test environments (advanced) |

