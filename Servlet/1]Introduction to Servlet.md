
## ✅  **Introduction to Servlet**

---

### 📌 **What is a Servlet?**

A **Servlet** is a **Java class** used to build **web applications**. It runs on a **Servlet Container** (like Apache Tomcat) and handles **HTTP requests** from clients (usually browsers) and **generates responses**, often in the form of HTML.

* It's part of the **Java EE (Jakarta EE)** platform.
* Think of a Servlet as the **controller** in a web application: it receives requests, processes them (interacts with databases, applies logic), and sends back responses.

```plaintext
Client (Browser) ---> Servlet (Server-side Java Class) ---> Response (HTML/JSON)
```

---

### 🔁 **Servlet Life Cycle** (Managed by Servlet Container)

1. **Loading & Instantiation** (once per Servlet)
2. **Initialization** – `init()` method
3. **Request Handling** – `service()` method

   * Calls `doGet()`, `doPost()` depending on HTTP method
4. **Destruction** – `destroy()` method

---

### ✅ **Servlet vs JSP vs Spring MVC**

| Feature      | **Servlet**                    | **JSP**                        | **Spring MVC**                                   |
| ------------ | ------------------------------ | ------------------------------ | ------------------------------------------------ |
| Nature       | Pure Java class                | Java + HTML (view layer)       | Framework (Full-stack)                           |
| Focus        | Controller logic               | Presentation logic             | Full MVC (model-view-controller)                 |
| Syntax       | Java code                      | HTML + embedded Java (`<% %>`) | Annotations, Configuration, Dependency Injection |
| Use Case     | Handle requests and responses  | Display results (UI layer)     | Full app with separation of concerns             |
| Ease of Use  | Verbose, more boilerplate      | Easier for UI                  | Clean, modular, scalable                         |
| Modern Usage | Still used, often with JSP/DAO | Used for views in legacy apps  | Preferred in modern enterprise applications      |

---

### 🧠 **Why Do We Need Servlets?**

Before Servlets, Java web development used **CGI scripts** which:

* Created a new process for each request (slow)
* Had platform-specific issues

**Servlets solved this**:

* **Multi-threaded** (no need to spawn a process per request)
* **Platform-independent**
* Runs inside **JVM** → more secure and portable

#### Responsibilities of Servlet:

* **Parse request parameters** (e.g., login form data)
* **Process business logic**
* **Forward to views (like JSPs)** or generate HTML/XML/JSON directly
* **Manage session** and cookies
* **Redirect or forward** to other resources

---

### ⚙️ **Basic Servlet Workflow**

1. Client sends a request to the server.
2. Servlet container receives it and forwards to the appropriate Servlet.
3. Servlet processes it (can read parameters, query DB).
4. Servlet builds and sends a response (HTML or redirects to JSP).

```java
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        // Authentication logic here
        PrintWriter out = response.getWriter();
        out.println("<html><body>Welcome " + username + "</body></html>");
    }
}
```

---

### 🏗️ **Role of Servlet in Web App Architecture**

#### ➤ In Traditional 3-Tier Architecture:

```
Client (Browser)
       |
Controller (Servlet) - Handles HTTP requests, processes logic
       |
Service / DAO Layer - Handles business logic and database
       |
Database (MySQL/PostgreSQL/etc.)
```

* Servlet works as the **entry point** for HTTP requests.
* It **connects** the frontend (JSP/UI) and backend (DB/Service layer).

---

### ✅ Summary

* Servlets are **Java-based request handlers** for dynamic web apps.
* They are **faster, scalable**, and **platform-independent**.
* Used mainly as the **controller layer** in MVC pattern.
* Work closely with JSP (view) and DAO (model/data access) in Java EE architecture.


