
## **HttpServlet Basics**

### ✅ **1. Extending `HttpServlet` Class**

* In Java, a servlet is created by **extending the `HttpServlet` class**, which is provided by the `javax.servlet.http` package.
* `HttpServlet` is an abstract class that provides default implementations for HTTP protocol methods like `doGet()`, `doPost()`, etc.

### 📌 Example:

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {
    // Servlet logic here
}
```

---

### ✅ **2. Overriding `doGet()`, `doPost()`, `doPut()`, `doDelete()`**

* These methods handle specific HTTP request types:

| Method       | Used For                                            |
| ------------ | --------------------------------------------------- |
| `doGet()`    | Handling **GET** requests (e.g., viewing data)      |
| `doPost()`   | Handling **POST** requests (e.g., submitting forms) |
| `doPut()`    | Handling **PUT** requests (updating resources)      |
| `doDelete()` | Handling **DELETE** requests (deleting resources)   |

#### 📌 Example: Overriding `doGet()` and `doPost()`:

```java
public class MyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>This is a GET request</h3>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        String username = request.getParameter("username");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Hello, " + username + "</h3>");
    }
}
```

---

### ✅ **3. `HttpServletRequest` and `HttpServletResponse` Usage**

#### 📌 `HttpServletRequest`:

This object is used to **read incoming data** from the client (like form data, headers, cookies).

| Common Method               | Description                           |
| --------------------------- | ------------------------------------- |
| `getParameter(String name)` | Get form data from request            |
| `getMethod()`               | Get HTTP method (`GET`, `POST`, etc.) |
| `getHeader(String name)`    | Get a specific HTTP header value      |
| `getCookies()`              | Get cookies sent by client            |

#### 📌 `HttpServletResponse`:

This object is used to **send a response** (like HTML, redirect, JSON) to the client.

| Common Method                   | Description                            |
| ------------------------------- | -------------------------------------- |
| `setContentType(String type)`   | Set response content type (e.g., HTML) |
| `getWriter()`                   | Get a `PrintWriter` to send text       |
| `sendRedirect(String location)` | Redirect to another URL                |
| `setHeader(String name, value)` | Set custom response headers            |

#### 📌 Example of Using Request & Response:

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Reading form data
    String name = request.getParameter("name");

    // Setting response content
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<h1>Hello " + name + "!</h1>");
}
```

---

### ✅ Summary:

| Concept                   | Purpose                                              |
| ------------------------- | ---------------------------------------------------- |
| Extending `HttpServlet`   | To build a servlet that handles HTTP requests        |
| Overriding `doGet()` etc. | To process different HTTP methods (GET, POST, etc.)  |
| `HttpServletRequest`      | To read input (form data, headers, etc.) from client |
| `HttpServletResponse`     | To write output (HTML, redirect, etc.) to client     |

