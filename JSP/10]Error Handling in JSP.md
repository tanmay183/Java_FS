

### ✅ 1. **Using `errorPage` and `isErrorPage`**

JSP provides a simple mechanism to handle exceptions using two attributes:

* `errorPage` (in the main JSP): Specifies which page to forward to when an error occurs.
* `isErrorPage` (in the error JSP): Set to `true` so it can access the exception object.

---

#### 🔹 Example:

##### `main.jsp`:

```jsp
<%@ page errorPage="error.jsp" %>
<html>
<body>
    <h2>Divide Numbers</h2>
    <%
        int a = 10;
        int b = 0;
        int result = a / b;  // This will cause ArithmeticException
        out.println("Result: " + result);
    %>
</body>
</html>
```

##### `error.jsp`:

```jsp
<%@ page isErrorPage="true" %>
<html>
<body>
    <h2>Oops! An error occurred:</h2>
    <p><b>Error:</b> <%= exception %></p>
</body>
</html>
```

> 🧠 `exception` is an implicit object available only in pages where `isErrorPage="true"`.

---

### ✅ 2. **Global Error Handling with `web.xml`**

You can declare error handlers in your `web.xml` to handle exceptions or specific HTTP status codes for all JSPs/Servlets.

---

#### 🔹 Example (inside `web.xml`):

```xml
<error-page>
    <exception-type>java.lang.ArithmeticException</exception-type>
    <location>/error.jsp</location>
</error-page>

<error-page>
    <error-code>404</error-code>
    <location>/notfound.jsp</location>
</error-page>
```

* `exception-type`: For catching Java exceptions (like `NullPointerException`, etc.)
* `error-code`: For handling HTTP errors (like 404, 500)

---

### ✅ 3. **Custom Error Messages and Exception Types**

In your `error.jsp`, you can display a user-friendly message based on the exception:

```jsp
<%@ page isErrorPage="true" %>
<html>
<body>
    <h2>Something went wrong.</h2>
    <%
        if (exception instanceof ArithmeticException) {
            out.println("Math error: Cannot divide by zero.");
        } else {
            out.println("An unexpected error occurred: " + exception.getMessage());
        }
    %>
</body>
</html>
```

---

### 🧠 Summary:

| Feature                  | Purpose                                                    |
| ------------------------ | ---------------------------------------------------------- |
| `errorPage`              | Redirects to error page if an exception occurs             |
| `isErrorPage`            | Allows a page to handle exceptions with `exception` object |
| `web.xml` error-page tag | Globally handles exceptions or HTTP error codes            |
| Custom error logic       | Helps in showing friendly and specific messages            |


