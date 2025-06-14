

## ✅ JSP Action Tags – Explained Simply

JSP action tags are XML-style tags used to control the behavior of the JSP page. They are processed at request time.

### 🧩 1. `<jsp:useBean>`

Used to create or locate a JavaBean.

```jsp
<jsp:useBean id="user" class="com.example.User" scope="session" />
```

* `id`: name of the bean instance
* `class`: full class name
* `scope`: `page`, `request`, `session`, or `application`

---

### 🧩 2. `<jsp:setProperty>`

Used to set a property value of a JavaBean.

```jsp
<jsp:setProperty name="user" property="username" value="Tanmay" />
```

OR set all request parameters automatically:

```jsp
<jsp:setProperty name="user" property="*" />
```

---

### 🧩 3. `<jsp:getProperty>`

Used to retrieve (display) a property from the bean.

```jsp
<jsp:getProperty name="user" property="username" />
```

---

### 🧩 4. `<jsp:include>`

Used to include content from another JSP at request time.

```jsp
<jsp:include page="header.jsp" />
```

---

### 🧩 5. `<jsp:forward>`

Forwards request to another resource (JSP or servlet).

```jsp
<jsp:forward page="home.jsp" />
```

---

### 🧩 6. `<jsp:param>`

Used inside `<jsp:include>` or `<jsp:forward>` to pass parameters.

```jsp
<jsp:include page="profile.jsp">
  <jsp:param name="id" value="101" />
</jsp:include>
```

---

## 🌐 Example Using All Action Tags

Assume this setup:

* A JavaBean class: `User.java`
* JSP pages: `index.jsp`, `userDetails.jsp`, `header.jsp`

---

### 🔹 `User.java` (Bean)

```java
package com.example;

public class User {
    private String username;
    private String email;

    // Getters and Setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
```

---

### 🔹 `index.jsp`

```jsp
<jsp:useBean id="user" class="com.example.User" scope="session" />
<jsp:setProperty name="user" property="username" value="Tanmay" />
<jsp:setProperty name="user" property="email" value="tanmay@example.com" />

<jsp:include page="header.jsp" />

<h2>User Info:</h2>
Username: <jsp:getProperty name="user" property="username" /><br>
Email: <jsp:getProperty name="user" property="email" /><br>

<jsp:include page="userDetails.jsp">
    <jsp:param name="country" value="India" />
</jsp:include>
```

---

### 🔹 `header.jsp`

```jsp
<h1>Welcome to JSP Demo</h1>
```

---

### 🔹 `userDetails.jsp`

```jsp
<%
    String country = request.getParameter("country");
%>
<p>Country: <%= country %></p>

<%-- Forward user to a new page based on country --%>
<% if ("India".equals(country)) { %>
    <jsp:forward page="indiaPage.jsp" />
<% } %>
```

---

### 🔹 `indiaPage.jsp`

```jsp
<h2>You are from India. Namaste!</h2>
```

---

## 📌 Summary of Tag Usage:

| Tag                 | Purpose                                     |
| ------------------- | ------------------------------------------- |
| `<jsp:useBean>`     | Instantiate JavaBean                        |
| `<jsp:setProperty>` | Set bean values                             |
| `<jsp:getProperty>` | Show bean values                            |
| `<jsp:include>`     | Include other JSP at runtime                |
| `<jsp:param>`       | Pass parameters to included/forwarded pages |
| `<jsp:forward>`     | Redirect to another JSP based on logic      |


