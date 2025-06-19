# 🔐 8. Spring Boot Security

Spring Boot Security is used to **secure your applications** — from REST APIs to web apps — by handling **authentication**, **authorization**, and common security vulnerabilities.

---

## 🔰 Introduction to Spring Security

**Spring Security** is a powerful and customizable authentication and access control framework for Java applications.

### 🔒 What It Secures:

* **Authentication**: Who are you? (username/password)
* **Authorization**: What are you allowed to do? (roles/permissions)

It also protects against:

* CSRF
* Session fixation
* Clickjacking
* CORS

---

## 🚫 Securing REST Endpoints

By default, Spring Security:

* Secures all endpoints (`/**`)
* Requires authentication for every request

You can override this using a **security config class**.

### 🔧 Secure vs Public Endpoints Example:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/public/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic(); // or formLogin()

        return http.build();
    }
}
```

---

## 🧍 In-Memory Authentication

For quick testing or simple use cases, Spring allows defining users in memory.

```java
@Bean
public UserDetailsService userDetailsService() {
    UserDetails user = User.withUsername("admin")
            .password(passwordEncoder().encode("admin123"))
            .roles("ADMIN")
            .build();

    return new InMemoryUserDetailsManager(user);
}

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

> Now `admin/admin123` can access protected endpoints.

---

## 🗃 JDBC / JPA-based Authentication

You can store user credentials in a database and authenticate using **JDBC or JPA**.

### 🔧 Example JPA Entity:

```java
@Entity
public class AppUser {
    @Id
    private String username;
    private String password;
    private String role;
}
```

### 🔧 Repository:

```java
public interface AppUserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findByUsername(String username);
}
```

---

## 🔐 `UserDetailsService` and `PasswordEncoder`

### ✅ Custom `UserDetailsService`:

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole()) // must match "ROLE_USER", "ROLE_ADMIN"
                .build();
    }
}
```

### ✅ Password Encoder:

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

---

## 🎭 Role-Based Authorization

You can restrict access based on user **roles**.

```java
.authorizeHttpRequests()
.requestMatchers("/admin/**").hasRole("ADMIN")
.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
.anyRequest().authenticated()
```

You can also use `@PreAuthorize` on methods:

```java
@PreAuthorize("hasRole('ADMIN')")
public String adminOnly() {
    return "Welcome Admin";
}
```

---

## 🔐 CSRF (Cross-Site Request Forgery)

By default:

* **Enabled** for web apps (forms)
* **Disabled** or should be disabled for stateless REST APIs

```java
http.csrf().disable(); // ✅ for REST API
```

---

## 🌐 CORS (Cross-Origin Resource Sharing)

CORS allows or blocks cross-origin HTTP requests (e.g., frontend on `localhost:3000` accessing API on `localhost:8080`).

```java
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods("*");
        }
    };
}
```

Or configure via Spring Security:

```java
http.cors().configurationSource(request -> {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("http://localhost:3000"));
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    config.setAllowCredentials(true);
    return config;
});
```

---

## 🪙 JWT (JSON Web Token) Authentication

JWT is a **stateless** way of securing REST APIs using signed tokens.

### 🔁 Flow:

1. Client sends username/password to `/login`
2. Server validates and generates JWT
3. Client sends JWT in `Authorization` header (`Bearer token`)
4. Server verifies JWT and allows access

### 🔧 Add Dependencies:

```xml
<dependency>
  <groupId>io.jsonwebtoken</groupId>
  <artifactId>jjwt-api</artifactId>
  <version>0.11.5</version>
</dependency>
```

### 🔧 Generate JWT:

```java
String jwt = Jwts.builder()
    .setSubject("user")
    .setIssuedAt(new Date())
    .setExpiration(new Date(System.currentTimeMillis() + 864_000_00)) // 1 day
    .signWith(secretKey)
    .compact();
```

### 🔧 Verify JWT in Filter:

```java
String authHeader = request.getHeader("Authorization");

if (authHeader != null && authHeader.startsWith("Bearer ")) {
    String token = authHeader.substring(7);
    String username = Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    // Set Authentication context
}
```

You’ll use a `OncePerRequestFilter` to intercept and validate every request.

---

## ✅ Summary Table

| Feature              | Description                                                        |
| -------------------- | ------------------------------------------------------------------ |
| Spring Security      | Framework for authentication, authorization, and protection        |
| In-Memory Auth       | Quick setup using predefined users                                 |
| JDBC/JPA Auth        | Uses database to fetch and validate users                          |
| `UserDetailsService` | Custom logic to fetch user details from DB                         |
| `PasswordEncoder`    | Encodes and verifies passwords securely using BCrypt               |
| Role-Based Access    | Secure APIs by roles (e.g., USER, ADMIN)                           |
| CSRF                 | Protects against cross-site forgery (disable for REST)             |
| CORS                 | Allows frontend to call backend APIs from other domains            |
| JWT                  | Stateless authentication using signed tokens (common in REST APIs) |

