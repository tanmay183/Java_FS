
### ✅ **13. JDBC Integration**

**1. Calling JDBC code from Servlets:**
You can write JDBC (Java Database Connectivity) code inside a Servlet to perform database operations like insert, read, update, delete. But it's **not recommended** to do this directly in Servlets.

📌 **Example (Not best practice):**

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("name");
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name) VALUES (?)");
        stmt.setString(1, name);
        stmt.executeUpdate();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    response.getWriter().println("User added!");
}
```

---

**2. Connecting to MySQL/Oracle DB:**

✅ **Steps:**

* Load the JDBC driver.
* Use `DriverManager.getConnection()`.
* Use `PreparedStatement` to avoid SQL injection.

🔧 **Example Connection:**

```java
Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");
```

---

**3. Best Practice: Move JDBC code to DAO (Data Access Object) classes**

🌟 **Why?**

* Separation of concerns
* Easy to maintain and test
* Reusable code

📌 **Servlet ➜ Calls DAO ➜ DAO handles DB logic**

🧱 **Example:**

```java
// In Servlet
UserDAO dao = new UserDAO();
dao.saveUser(name);
```

```java
// UserDAO.java
public class UserDAO {
    public void saveUser(String name) throws SQLException {
        Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name) VALUES (?)");
        stmt.setString(1, name);
        stmt.executeUpdate();
        conn.close();
    }
}
```

---

### ✅ **14. Redirect vs Forward**

| Feature                | `sendRedirect()`                       | `forward()`                                 |
| ---------------------- | -------------------------------------- | ------------------------------------------- |
| **Type**               | Client-side redirection                | Server-side forwarding                      |
| **Class Used**         | `HttpServletResponse`                  | `RequestDispatcher`                         |
| **Browser URL Change** | ✅ Yes                                  | ❌ No                                        |
| **New request?**       | ✅ Yes (new HTTP request from browser)  | ❌ No (same request object is used)          |
| **History recorded?**  | ✅ Yes                                  | ❌ No                                        |
| **Use Case**           | Redirect to another website or servlet | Internal transfer to JSP or another servlet |

---

📌 **sendRedirect Example:**

```java
response.sendRedirect("home.jsp");
```

> Browser will initiate a new request. URL in the browser will change.

📌 **forward Example:**

```java
RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
rd.forward(request, response);
```

> The server forwards the request. URL remains the same.

