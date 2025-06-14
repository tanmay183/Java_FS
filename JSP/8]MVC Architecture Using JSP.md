

## 🔷 MVC Architecture Using JSP

### ✅ 1. What is MVC?

* **Model:** Business logic or data (JavaBeans, database access, etc.)
* **View:** JSP – responsible for displaying data to the user
* **Controller:** Servlet – receives requests and decides what to do

---

### ✅ 2. Role of JSP as the View

* JSP files **only handle presentation** (HTML + data using EL like `${user.name}`).
* They **should not** contain business logic or database calls.

---

### ✅ 3. Forwarding Data from Servlet to JSP

We forward the request using:

```java
RequestDispatcher rd = request.getRequestDispatcher("view.jsp");
rd.forward(request, response);
```

Before forwarding, we can **attach data**:

```java
request.setAttribute("userName", "Tanmay");
```

---

### ✅ 4. Full Practical Example (Simple Form ➝ Servlet ➝ JSP)

#### 📁 Folder Structure (without Maven)

```
WebApp/
├── index.html
├── WEB-INF/
│   └── web.xml
├── ControllerServlet.java
└── view.jsp
```

---

### ✅ Step-by-Step Code

#### 📄 `index.html` (Simple Form)

```html
<!DOCTYPE html>
<html>
<head><title>User Form</title></head>
<body>
  <form action="ControllerServlet" method="post">
    Name: <input type="text" name="name" />
    <input type="submit" value="Submit" />
  </form>
</body>
</html>
```

---

#### 📄 `ControllerServlet.java`

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ControllerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get data from the form
        String name = request.getParameter("name");

        // Set data to request scope
        request.setAttribute("userName", name);

        // Forward to JSP (View)
        RequestDispatcher rd = request.getRequestDispatcher("view.jsp");
        rd.forward(request, response);
    }
}
```

---

#### 📄 `view.jsp`

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Welcome Page</title></head>
<body>
  <h2>Hello, ${userName}!</h2>
</body>
</html>
```

---

#### 📄 `web.xml` (Servlet Mapping)

```xml
<web-app>
  <servlet>
    <servlet-name>ControllerServlet</servlet-name>
    <servlet-class>ControllerServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>ControllerServlet</servlet-name>
    <url-pattern>/ControllerServlet</url-pattern>
  </servlet-mapping>
</web-app>
```

---

### 🧠 What You Learn Here

* `index.html`: Takes user input
* `ControllerServlet`: Acts as the Controller to receive input, attach attributes to request
* `view.jsp`: Displays data using `${}` (EL)


