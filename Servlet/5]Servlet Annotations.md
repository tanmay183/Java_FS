
## **5. Servlet Annotations (Modern Configuration)**

In modern servlet development (Servlet 3.0+), **annotations** are used **instead of web.xml** for simpler and cleaner configuration.

### ✅ `@WebServlet`

This annotation is used to declare a **Servlet** and map it to a **URL pattern**.

### ✅ `@WebInitParam`

Used to pass **initialization parameters** to the servlet, similar to `<init-param>` in `web.xml`.

### ✅ Example:

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(
    urlPatterns = "/hello",
    initParams = {
        @WebInitParam(name = "company", value = "OpenAI")
    }
)
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        
        String companyName = getServletConfig().getInitParameter("company");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Welcome to " + companyName + "</h2>");
    }
}
```

✅ No need to touch `web.xml` — everything is configured via annotations.

---

## ✅ **6. Request and Response Handling**

### ✅ Getting Input from Client (`HttpServletRequest`)

```java
String name = request.getParameter("username");  // From form input
String header = request.getHeader("User-Agent"); // From browser
Object obj = request.getAttribute("userObj");    // Server-side attribute
```

* `getParameter()` → fetches form data (GET or POST)
* `getHeader()` → gets request header info (browser, host, etc.)
* `getAttribute()` → gets server-set attributes (e.g., between filters or servlets)

---

### ✅ Sending Output to Client (`HttpServletResponse`)

```java
response.setContentType("text/html"); // MIME type
PrintWriter out = response.getWriter();
out.println("<h1>Hello World</h1>");
```

* `setContentType()` → tells browser what type of data is sent (HTML, JSON, PDF, etc.)
* `getWriter()` → sends character data (HTML, JSON)
* `sendRedirect("login.jsp")` → sends user to another page

---

### ✅ Passing Data to JSP or Another Servlet

#### ➤ Forward to JSP

```java
request.setAttribute("username", "Tanmay");
RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
rd.forward(request, response);
```

#### ➤ Redirect to Another Servlet

```java
response.sendRedirect("dashboard");  // goes to /dashboard URL
```

---

### 🌟 Summary Table:

| Task                        | Method                             |
| --------------------------- | ---------------------------------- |
| Get form data               | `request.getParameter("key")`      |
| Get browser header info     | `request.getHeader("User-Agent")`  |
| Set attribute (server-side) | `request.setAttribute("key", val)` |
| Forward to JSP/Servlet      | `RequestDispatcher.forward()`      |
| Redirect client             | `response.sendRedirect("url")`     |
| Set output type             | `response.setContentType("type")`  |
| Output content              | `response.getWriter().println()`   |


