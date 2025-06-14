

**JSP Directives** provide **global instructions** to the JSP container (the server engine that processes JSP files).

They do **not** generate HTML output. Instead, they control **how the JSP file behaves**—such as which libraries it imports, error handling, file inclusion, etc.

---

## 🧱 There are 3 Main JSP Directives:

| Directive | Syntax                      | Purpose                                          |
| --------- | --------------------------- | ------------------------------------------------ |
| `page`    | `<%@ page ... %>`           | Page-level settings (like import, encoding)      |
| `include` | `<%@ include file="..." %>` | Include content from another JSP at compile time |
| `taglib`  | `<%@ taglib ... %>`         | Use custom or standard tag libraries (like JSTL) |

---

## 1️⃣ `<%@ page %>` – Page Configuration

### ✅ Purpose:

Used to define **settings for the entire JSP file**.

### 📌 Common Attributes of `<%@ page %>`:

| Attribute     | Use                                          | Example                                 |
| ------------- | -------------------------------------------- | --------------------------------------- |
| `import`      | Import Java classes (like in Java)           | `import="java.util.*, java.sql.*"`      |
| `contentType` | Define MIME type of output                   | `contentType="text/html;charset=UTF-8"` |
| `errorPage`   | Set another JSP to handle exceptions         | `errorPage="error.jsp"`                 |
| `isErrorPage` | Tells if the page can display exception info | `isErrorPage="true"`                    |
| `session`     | Enable or disable session support            | `session="true"` or `false`             |

---

### 🧪 Example:

```jsp
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<body>
    Current Time: <%= new Date() %>
</body>
</html>
```

### ✅ Output:

Shows current date and time using imported `java.util.Date`.

---

### ⚠️ errorPage / isErrorPage Example

#### page1.jsp:

```jsp
<%@ page errorPage="error.jsp" %>
<%
    int x = 5 / 0;  // This will cause an error
%>
```

#### error.jsp:

```jsp
<%@ page isErrorPage="true" %>
<h1>Error occurred:</h1>
<pre><%= exception %></pre>
```

> ✅ This helps in centralizing error handling.

---

## 2️⃣ `<%@ include %>` – Static Include

### ✅ Purpose:

Includes **another JSP or HTML file** during the **translation phase** (i.e., before the file is compiled).

* This is called a **static include**
* Included content becomes part of the JSP’s final source

---

### 🧪 Example:

```jsp
<%@ include file="header.jsp" %>
<h1>Welcome to Home Page</h1>
<%@ include file="footer.jsp" %>
```

If `header.jsp` contains:

```html
<nav>Navigation Bar</nav>
```

Then the output becomes:

```html
<nav>Navigation Bar</nav>
<h1>Welcome to Home Page</h1>
<footer>...</footer>
```

---

### ✅ When to Use:

* For **reusable HTML parts** like:

  * `header.jsp`
  * `footer.jsp`
  * `navbar.jsp`

🧠 This keeps your code **modular and DRY** (Don’t Repeat Yourself).

---

## 3️⃣ `<%@ taglib %>` – Tag Libraries

### ✅ Purpose:

Used to include **custom tags** or **JSTL (JSP Standard Tag Library)** in a JSP page.

---

### 📚 Common Use Case: JSTL

#### Include core JSTL tags:

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
```

This lets you use:

```jsp
<c:if test="${not empty user}">
    Welcome, ${user.name}!
</c:if>
```

---

### 📌 taglib Attributes:

| Attribute | Purpose                               |
| --------- | ------------------------------------- |
| `uri`     | Unique identifier for the tag library |
| `prefix`  | Short name to use for the tags in JSP |

---

### ✅ Full Example:

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<body>
    <c:choose>
        <c:when test="${not empty user}">
            <h2>Hello, ${user.name}</h2>
        </c:when>
        <c:otherwise>
            <h2>Welcome, Guest!</h2>
        </c:otherwise>
    </c:choose>
</body>
</html>
```

---

## 🧠 Summary Table

| Directive | Use For                           | Example                                |
| --------- | --------------------------------- | -------------------------------------- |
| `page`    | Set page config (import, charset) | `<%@ page import="java.util.*" %>`     |
| `include` | Include reusable code (static)    | `<%@ include file="header.jsp" %>`     |
| `taglib`  | Use custom/JSTL tags              | `<%@ taglib uri="..." prefix="..." %>` |

---

## 🔚 Final Notes for Beginners

* Use `<%@ page %>` for **basic settings** like charset and import.
* Use `<%@ include %>` to keep your JSP pages **modular and readable**.
* Use `<%@ taglib %>` to use **JSTL**, which avoids scriptlets and promotes clean code.


