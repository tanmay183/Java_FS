
### ✅ **7. Session Management in JSP/Servlets**

---

#### 🔹 **What is Session Management?**

Session management helps the server **identify the same user** across multiple HTTP requests, since HTTP is a **stateless protocol**.

---

#### 🔸 **1. `HttpSession` Object**

Used to store **user-specific data** across multiple requests.

##### ✅ Common Methods:

```java
HttpSession session = request.getSession(); // Create or get existing session

session.setAttribute("username", "Tanmay"); // Store value in session

String name = (String) session.getAttribute("username"); // Retrieve value
```

##### 🔍 Example Use Case:

After login, store the user info:

```java
session.setAttribute("userEmail", email);
```

---

#### 🔸 **2. Session Timeout and Invalidation**

* **Timeout**: Set the time the session remains active without user interaction.

```java
session.setMaxInactiveInterval(600); // Session expires after 10 minutes (600 seconds)
```

* **Manual Invalidation**: Logout or force expiry:

```java
session.invalidate(); // Ends session immediately
```

---

#### 🔸 **3. Cookies vs Sessions**

| Feature    | Cookies                       | Sessions                        |
| ---------- | ----------------------------- | ------------------------------- |
| Stored on  | **Client's browser**          | **Server-side**                 |
| Security   | Less secure (can be modified) | More secure                     |
| Size Limit | \~4KB                         | No real limit (based on memory) |
| Use Case   | Track preferences, themes     | Track user login, cart, etc.    |

---

### ✅ **8. RequestDispatcher in JSP/Servlets**

---

#### 🔸 **What is `RequestDispatcher`?**

A server-side object to **forward or include** the request to another resource (like JSP or Servlet) **within the same application**.

---

#### 🔹 **1. `forward()`**

Used to **transfer control** from one Servlet/JSP to another. The **original request and response objects** are passed.

##### ✅ Syntax:

```java
RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
rd.forward(request, response);
```

* **Control transfers** to target resource.
* The **URL in browser doesn't change**.
* **Useful for navigation** after processing logic.

---

#### 🔹 **2. `include()`**

Used to **include content** (like header, footer, menu) from another resource **within the current response**.

##### ✅ Syntax:

```java
RequestDispatcher rd = request.getRequestDispatcher("header.jsp");
rd.include(request, response);
```

* Only **response content** is included.
* **Control returns** to original file after including.
* Common in **modular layouts** (e.g., templates).

---

### ✅ Summary:

| Feature            | `forward()`    | `include()`             |
| ------------------ | -------------- | ----------------------- |
| Transfers control? | Yes            | No                      |
| Content included?  | Whole response | Partial (header/footer) |
| URL changes?       | No             | No                      |
| Use case           | Navigation     | Template inclusion      |

