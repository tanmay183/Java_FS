## ✅ 11. Filters and Listeners (Advanced Topics)

### 📌 Filters in Servlets

#### 🔹 What is a Filter?

* A **Filter** is a reusable piece of code that **intercepts requests** before they reach a Servlet or JSP and/or **processes responses** before they are sent back to the client.
* Filters do **not generate responses** but **modify** or **pre-process/post-process** the request and response objects.

#### 🔹 Use Cases:

* Authentication and Authorization
* Logging and Auditing
* Data Compression
* Input Validation
* Request Encoding

#### 🔹 Filter Interface:

A filter implements the `javax.servlet.Filter` interface.

#### 🔹 Lifecycle Methods:

1. `init(FilterConfig config)` – Called once when the filter is initialized.
2. `doFilter(ServletRequest request, ServletResponse response, FilterChain chain)` – Called for every request.
3. `destroy()` – Called when the filter is taken out of service.

#### 🔹 Example: Logging Filter

```java
import javax.servlet.*;
import java.io.IOException;

public class LoggingFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
        System.out.println("Logging Filter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("Request received at " + new java.util.Date());
        chain.doFilter(request, response); // Pass control to the next filter/servlet
        System.out.println("Response returned at " + new java.util.Date());
    }

    public void destroy() {
        System.out.println("Logging Filter destroyed");
    }
}
```

#### 🔹 Registering Filter in `web.xml`:

```xml
<filter>
    <filter-name>LoggingFilter</filter-name>
    <filter-class>com.example.LoggingFilter</filter-class>
</filter>

<filter-mapping>
    <filter-name>LoggingFilter</filter-name>
    <url-pattern>/*</url-pattern> <!-- Applies to all requests -->
</filter-mapping>
```

---

### 📌 Listeners in Servlets

#### 🔹 What is a Listener?

* A **Listener** listens to **specific events** in a web application's lifecycle, like context creation, session creation, or attribute changes.

#### 🔹 Common Listener Interfaces:

| Listener Interface                | Trigger Event            |
| --------------------------------- | ------------------------ |
| `ServletContextListener`          | App start/stop           |
| `HttpSessionListener`             | Session create/destroy   |
| `ServletRequestListener`          | Request lifecycle        |
| `HttpSessionAttributeListener`    | Session attribute change |
| `ServletContextAttributeListener` | Context attribute change |

#### 🔹 Example: Session Counter Listener

```java
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class SessionCounterListener implements HttpSessionListener {
    private static int activeSessions = 0;

    public void sessionCreated(HttpSessionEvent se) {
        activeSessions++;
        System.out.println("Session Created: " + activeSessions);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        activeSessions--;
        System.out.println("Session Destroyed: " + activeSessions);
    }

    public static int getActiveSessions() {
        return activeSessions;
    }
}
```

#### 🔹 Registering Listener in `web.xml` (if not using annotations):

```xml
<listener>
    <listener-class>com.example.SessionCounterListener</listener-class>
</listener>
```

---

## ✅ 12. Exception Handling in Servlets

### 📌 Ways to Handle Exceptions in Servlets:

---

### 🔹 1. Using `web.xml` Error Page Mapping

You can define specific pages for specific exceptions using `<error-page>` in `web.xml`.

#### Example:

```xml
<error-page>
    <exception-type>java.lang.Exception</exception-type>
    <location>/error.jsp</location>
</error-page>

<error-page>
    <error-code>404</error-code>
    <location>/notfound.jsp</location>
</error-page>
```

#### Explanation:

* If any unhandled exception (like `NullPointerException`) occurs, the request is forwarded to `/error.jsp`.
* If a 404 error (resource not found) occurs, `/notfound.jsp` is displayed.

---

### 🔹 2. Using `response.sendError()`

In a servlet, you can manually trigger an error like:

```java
if (someConditionFails) {
    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input!");
}
```

#### Common Status Codes:

| Code  | Meaning               |
| ----- | --------------------- |
| `400` | Bad Request           |
| `401` | Unauthorized          |
| `403` | Forbidden             |
| `404` | Not Found             |
| `500` | Internal Server Error |

---

### 🔹 3. Using `try-catch` in Servlet Code

You can handle exceptions directly inside servlet logic:

```java
try {
    // risky code
} catch (Exception e) {
    request.setAttribute("errorMsg", e.getMessage());
    request.getRequestDispatcher("/error.jsp").forward(request, response);
}
```

---

### 📌 Sample `error.jsp` Page:

```jsp
<%@ page isErrorPage="true" %>
<html>
<body>
    <h2>Something went wrong!</h2>
    <p>Error: <%= exception %></p>
</body>
</html>
```

* Use `isErrorPage="true"` to access the implicit `exception` object in JSP.

---

## ✅ Summary Table

| Topic         | Description                                         |
| ------------- | --------------------------------------------------- |
| Filter        | Pre/post-processing logic (e.g., logging, auth)     |
| Listener      | Monitors lifecycle events (e.g., sessions, context) |
| Error Mapping | Redirects to custom error pages                     |
| `sendError()` | Manually sends error responses                      |
| `try-catch`   | Handles exceptions within servlet logic             |


