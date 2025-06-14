
## ✅  Expression Language (EL) in JSP

### 🚀 What is EL?

Expression Language (EL) is a **simpler way** to access data stored in JavaBeans, request parameters, session attributes, etc., without writing Java code or scriptlets (`<% ... %>`).

Instead of:

```jsp
<%= request.getParameter("username") %>
```

With EL:

```jsp
${param.username}
```

---

## 🔹 1. EL **Basic Syntax**

```jsp
${expression}
```

EL automatically searches in the following **scopes (in order)**:

1. **Page scope**
2. **Request scope**
3. **Session scope**
4. **Application scope**

You can refer to variables or bean properties directly without writing Java code.

---

## 🔹 2. Accessing Scope Variables in EL

Suppose we store a variable like this:

```jsp
<%
    request.setAttribute("course", "Java Full Stack");
    session.setAttribute("username", "Tanmay");
    application.setAttribute("appName", "MyWebApp");
%>
```

Now you can access them in EL:

```jsp
<p>Course: ${course}</p>
<p>Username: ${username}</p>
<p>Application Name: ${appName}</p>
```

---

## 🔹 3. EL Implicit Objects

| EL Object                                                       | Description                                     |
| --------------------------------------------------------------- | ----------------------------------------------- |
| `param`                                                         | Gets request parameter (single value)           |
| `paramValues`                                                   | Gets request parameter (multiple values, array) |
| `header`                                                        | Gets request header                             |
| `headerValues`                                                  | Gets multiple header values                     |
| `cookie`                                                        | Accesses cookie values                          |
| `initParam`                                                     | ServletContext init params (from web.xml)       |
| `pageScope`, `requestScope`, `sessionScope`, `applicationScope` | Access scoped variables                         |
| `empty`                                                         | Checks if a value is null or empty              |

---

### 🔸 `param` and `paramValues`

```jsp
<!-- Suppose URL: page.jsp?name=Tanmay&hobby=reading&hobby=music -->
<p>Name: ${param.name}</p>
<p>First Hobby: ${paramValues.hobby[0]}</p>
<p>Second Hobby: ${paramValues.hobby[1]}</p>
```

---

### 🔸 `header` and `cookie`

```jsp
<p>User Agent: ${header["User-Agent"]}</p>
<p>Session ID Cookie: ${cookie.JSESSIONID.value}</p>
```

---

### 🔸 Scope Access

```jsp
<p>Session Username: ${sessionScope.username}</p>
<p>Request Course: ${requestScope.course}</p>
```

---

## 🔹 4. Accessing JavaBeans in EL

Suppose you have a JavaBean:

### ✅ `User.java`:

```java
public class User {
    private String name;
    private String email;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
```

### ✅ JSP Usage:

```jsp
<jsp:useBean id="user" class="com.example.User" scope="session" />
<jsp:setProperty name="user" property="name" value="Tanmay" />
<jsp:setProperty name="user" property="email" value="tanmay@example.com" />
```

### ✅ EL Access:

```jsp
<p>Name: ${user.name}</p>
<p>Email: ${user.email}</p>
```

🧠 **Note:** EL accesses bean properties using their **getter methods** (i.e., `getName()`, `getEmail()`).

---

## 🔸 EL Operators (Bonus)

| Operator             | Use                 | Example               |                 |                                |
| -------------------- | ------------------- | --------------------- | --------------- | ------------------------------ |
| `==`, `!=`           | Equality            | `${param.age == 18}`  |                 |                                |
| `<`, `>`, `<=`, `>=` | Comparison          | `${price > 100}`      |                 |                                |
| `&&`, \`             |                     | \`                    | Logical AND, OR | `${price > 50 && price < 200}` |
| `empty`              | Null or empty check | `${empty param.name}` |                 |                                |

---

## ✅ Summary Table

| What You Want to Do                  | EL Expression                |
| ------------------------------------ | ---------------------------- |
| Get request param `username`         | `${param.username}`          |
| Get session attribute `user`         | `${sessionScope.user}`       |
| Get request header `User-Agent`      | `${header["User-Agent"]}`    |
| Get cookie `JSESSIONID` value        | `${cookie.JSESSIONID.value}` |
| Get property `name` from bean `user` | `${user.name}`               |
| Check if param `age` is empty        | `${empty param.age}`         |

---

## ✅ Final EL Practice Example

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.User" %>

<jsp:useBean id="user" class="com.example.User" scope="session" />
<jsp:setProperty name="user" property="name" value="Tanmay" />
<jsp:setProperty name="user" property="email" value="tanmay@example.com" />

<h2>Hello, ${user.name}!</h2>
<p>Your email is: ${user.email}</p>

<p>Page parameter (username): ${param.username}</p>
<p>Session value (username): ${sessionScope.username}</p>
<p>Browser info: ${header["User-Agent"]}</p>
```

