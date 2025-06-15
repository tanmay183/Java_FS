

### ✅**Servlet Lifecycle**

The **Servlet Lifecycle** defines the steps from **creation to destruction** of a Servlet. The lifecycle is managed by the **Servlet container** (like **Apache Tomcat**).

---

### 🚦 Key Methods in Lifecycle:

| Method      | Purpose                                                              |
| ----------- | -------------------------------------------------------------------- |
| `init()`    | Initializes the servlet once when it is created.                     |
| `service()` | Called **every time a request** is sent to the servlet.              |
| `destroy()` | Called **once before the servlet is removed** from memory (cleanup). |

---

### 📌 Lifecycle Flow:

1. **Servlet Created** (only once)

   * Container loads the servlet class.
   * Calls the `init()` method.

2. **Request Handling**

   * For every client request:

     * The container spawns a new thread.
     * Calls the `service()` method.

3. **Servlet Destroyed**

   * When app/server shuts down or servlet is undeployed.
   * `destroy()` method is called to release resources.

---

### 🧠 Example Code:

```java
import javax.servlet.*;
import java.io.IOException;

public class MyServlet implements Servlet {

    ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        System.out.println("Servlet initialized");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        System.out.println("Request handled");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet destroyed");
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public String getServletInfo() {
        return "MyServlet v1.0";
    }
}
```

---

### ⚙️ How the Container (e.g., Tomcat) Manages Lifecycle:

| Action                   | Container's Role                        |
| ------------------------ | --------------------------------------- |
| On first request         | Loads servlet, calls `init()`           |
| For every request        | Creates a thread, calls `service()`     |
| On shutdown/redeployment | Calls `destroy()` to clean up resources |

---

### 🆚 ServletConfig vs ServletContext

| Feature          | `ServletConfig`                       | `ServletContext`                                  |
| ---------------- | ------------------------------------- | ------------------------------------------------- |
| Scope            | One servlet only                      | Entire application                                |
| Purpose          | Servlet-specific config (init params) | App-wide info (e.g., shared attributes, paths)    |
| Accessed via     | `getServletConfig()`                  | `getServletContext()`                             |
| Example Use Case | DB config only for this servlet       | Common logger or file path shared across servlets |

---

### 🔁 Summary:

* `init()` → Called once to initialize.
* `service()` → Handles each request.
* `destroy()` → Called once to clean up.

Understanding this flow helps you manage **state**, **resources**, and **scalability** of your web applications.
