
## 🔐Session Management in JSP

Session Management allows a JSP or Servlet to maintain **user-specific data** across multiple requests (like login sessions or shopping carts).

---

### ✅ 1. **Storing / Retrieving Data in `HttpSession`**

#### 📌 What is `HttpSession`?

* A **session** represents a single user’s interaction with the web application.
* Each user gets a **unique session** identified by a session ID (stored in a cookie called `JSESSIONID`).

#### ✅ How to Use:

```jsp
<%
    // Get the session object (creates one if it doesn't exist)
    HttpSession session = request.getSession();

    // Store data in session
    session.setAttribute("username", "Tanmay");

    // Retrieve data
    String user = (String) session.getAttribute("username");

    out.println("Hello, " + user);
%>
```

---

### ⌛ 2. **Session Timeout and Invalidation**

#### ⏱️ Set Timeout in `web.xml`

```xml
<session-config>
    <session-timeout>30</session-timeout> <!-- in minutes -->
</session-config>
```

#### ❌ Invalidate Session (logout logic)

```jsp
<%
    session.invalidate(); // Removes all attributes and ends session
%>
```

---

### 🔄 3. **Scope Comparison: Application vs Session vs Request**

| Scope           | Object                       | Lifetime                                   | Accessible In             | Use Case Example                   |
| --------------- | ---------------------------- | ------------------------------------------ | ------------------------- | ---------------------------------- |
| **Request**     | `request.setAttribute()`     | Single HTTP request                        | Same page/forwarded pages | Form data, errors                  |
| **Session**     | `session.setAttribute()`     | User session (until logout or timeout)     | All JSPs/Servlets of user | Logged-in user info, shopping cart |
| **Application** | `application.setAttribute()` | Whole app lifetime (until server shutdown) | All users, all JSPs       | App-wide config, global counters   |

#### 🧪 Example:

```jsp
<%
    request.setAttribute("r", "request-scope");
    session.setAttribute("s", "session-scope");
    application.setAttribute("a", "application-scope");
%>

<p>Request: <%= request.getAttribute("r") %></p>
<p>Session: <%= session.getAttribute("s") %></p>
<p>Application: <%= application.getAttribute("a") %></p>
```

---

### ⚠️ Note:

* Always invalidate session during **logout** to prevent unauthorized access.
* Do not store sensitive data (like passwords) in session.

---

### ✅ Summary:

| Feature                   | Purpose                         |
| ------------------------- | ------------------------------- |
| `session.getAttribute()`  | Get session variable            |
| `session.setAttribute()`  | Set session variable            |
| `session.invalidate()`    | Destroy session                 |
| `web.xml` session-timeout | Set global timeout for sessions |


