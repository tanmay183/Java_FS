

## 🔹 JSP Implicit Objects (Auto-Available in JSP Pages)

JSP provides **9 implicit objects**, which are automatically created by the JSP container and accessible without explicit declaration. These objects represent core web components like requests, responses, sessions, etc.

---

### 1. **`request`** – *HttpServletRequest Object*

* Represents the **incoming client request**.
* Useful to fetch form data, request parameters, headers, cookies, etc.

**Example Uses:**

```jsp
<%= request.getParameter("username") %>
<%= request.getMethod() %>
<%= request.getHeader("User-Agent") %>
```

---

### 2. **`response`** – *HttpServletResponse Object*

* Used to **modify the response** sent to the client.
* Can set headers, status codes, cookies, content type, etc.

**Example Uses:**

```jsp
<%
    response.setContentType("text/html");
    response.setHeader("Cache-Control", "no-cache");
%>
```

---

### 3. **`session`** – *HttpSession Object*

* Stores **user data across multiple pages (sessions)**.
* Automatically created when a JSP page is accessed.

**Example Uses:**

```jsp
<%
    session.setAttribute("user", "Tanmay");
    String user = (String) session.getAttribute("user");
%>
```

---

### 4. **`application`** – *ServletContext Object*

* Shared across the entire web application (all users, all sessions).
* Used for **global variables**, counters, config data, etc.

**Example Uses:**

```jsp
<%
    application.setAttribute("siteName", "MyBlog");
    String site = (String) application.getAttribute("siteName");
%>
```

---

### 5. **`out`** – *JspWriter Object*

* Used to **write content** to the response.
* Similar to `System.out`, but outputs to the client browser.

**Example Uses:**

```jsp
<%
    out.println("Welcome to JSP!");
%>
```

---

### 6. **`config`** – *ServletConfig Object*

* Contains **initialization parameters** for the servlet (configured in `web.xml`).
* Rarely used directly in JSP.

**Example Uses:**

```jsp
<%
    String param = config.getInitParameter("supportEmail");
%>
```

---

### 7. **`pageContext`** – *PageContext Object*

* Provides access to **all JSP scopes** (page, request, session, application).
* Preferred over `request.setAttribute()` because it’s more flexible.

**Example Uses:**

```jsp
<%
    pageContext.setAttribute("msg", "Hello", PageContext.SESSION_SCOPE);
    String msg = (String) pageContext.getAttribute("msg", PageContext.SESSION_SCOPE);
%>
```

---

### 8. **`page`** – *this Reference (Current Servlet Instance)*

* Refers to the **current JSP page’s servlet object**.
* Rarely used directly.

**Example Use:**

```jsp
<%= page.getClass().getName() %>
```

---

### 9. **`exception`** – *Throwable Object*

* Only available in **error pages** (`<%@ page isErrorPage="true" %>`).
* Holds the exception object that caused the error.

**Example Use in error page:**

```jsp
<%@ page isErrorPage="true" %>
<h2>Error occurred: <%= exception.getMessage() %></h2>
```

---

## 🔸 When to Use Which Object?

| Object        | Use Case Example                                |
| ------------- | ----------------------------------------------- |
| `request`     | Reading form inputs, headers, query params      |
| `response`    | Modifying HTTP response (headers, content-type) |
| `session`     | Storing user login data                         |
| `application` | App-wide counters, config                       |
| `out`         | Printing output to browser                      |
| `config`      | Reading servlet init params                     |
| `pageContext` | Managing scope & attributes                     |
| `page`        | Accessing the servlet instance                  |
| `exception`   | Handling errors in error pages                  |

---


### ✅ **Practice JSP Page Using All 9 Implicit Objects**

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%
    // 1. request - to get a parameter from the URL
    String username = request.getParameter("username");

    // 2. response - to set content type
    response.setContentType("text/html");

    // 3. session - to store user data across multiple pages
    session.setAttribute("user", username);

    // 4. application - to store global data shared across sessions
    application.setAttribute("appName", "MyJSPApp");

    // 5. out - to print output to browser (can also use <%= %>)
    out.println("<h3>Welcome, " + username + "!</h3>");

    // 6. config - to access servlet config
    String jspName = config.getServletName();
    out.println("<p>JSP Name from config: " + jspName + "</p>");

    // 7. pageContext - to set/get scoped attributes
    pageContext.setAttribute("message", "Hello from pageContext!");
    out.println("<p>" + pageContext.getAttribute("message") + "</p>");

    // 8. page - reference to this JSP page (i.e., this)
    out.println("<p>Class Name of this JSP (page): " + page.getClass().getName() + "</p>");

    // 9. exception - only available in error pages, demo in error.jsp
    // Not used here because this page is not marked as isErrorPage="true"
%>

<!-- Use Expression syntax to print session and app attributes -->
<p>Session User: <%= session.getAttribute("user") %></p>
<p>Application Name: <%= application.getAttribute("appName") %></p>
```

---

### 🧪 **How to Test**

1. Save this as `practice.jsp`
2. Deploy in your server (e.g., Apache Tomcat)
3. Visit the URL like this:

   ```
   http://localhost:8080/yourApp/practice.jsp?username=Tanmay
   ```
4. Create a second file called `error.jsp` to catch errors:

```jsp
<%@ page isErrorPage="true" %>
<h2>Error Occurred:</h2>
<p><%= exception.getMessage() %></p>
```

---

### ⚠️ Note:

* `exception` object is only available in pages with `isErrorPage="true"`.
* `page` and `pageContext` are rarely used in real apps but useful in special cases.
* In modern JSPs with JSTL/EL, usage of Java code (scriptlets) is **discouraged**.


