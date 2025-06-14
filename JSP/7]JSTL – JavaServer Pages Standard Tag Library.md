
## 📘 7. JSTL – JavaServer Pages Standard Tag Library

**JSTL** is a set of pre-defined tags in JSP that helps you perform common tasks like **conditions**, **loops**, **output**, **formatting**, and even **internationalization** without using Java code directly in JSP.

### 🔹 Why use JSTL?

* Makes JSP **cleaner and more readable**.
* Avoids mixing Java code inside HTML.
* Standardized and widely supported.

---

### 🔹 Core Tags (`c:`) – Control Flow & Variables

These are the most commonly used JSTL tags.

#### 1. `<c:if>` – Conditional logic (like if-statement)

```jsp
<c:if test="${user.age >= 18}">
  You are an adult.
</c:if>
```

#### 2. `<c:choose>`, `<c:when>`, `<c:otherwise>` – Like if-else-if ladder

```jsp
<c:choose>
  <c:when test="${marks >= 90}">A Grade</c:when>
  <c:when test="${marks >= 75}">B Grade</c:when>
  <c:otherwise>Needs Improvement</c:otherwise>
</c:choose>
```

#### 3. `<c:forEach>` – Looping (like `for` loop)

```jsp
<c:forEach var="item" items="${productList}">
  <li>${item.name} - ₹${item.price}</li>
</c:forEach>
```

#### 4. `<c:set>` – Set a variable

```jsp
<c:set var="message" value="Welcome to JSP!" />
```

#### 5. `<c:out>` – Output a value safely (prevents XSS)

```jsp
<c:out value="${user.name}" />
```

#### 6. `<c:import>` – Import content from another page or URL

```jsp
<c:import url="header.jsp" />
```

---

### 🔹 Formatting Tags (`fmt:`) – Numbers & Dates

These tags help format dates, numbers, and currencies.

#### 1. `<fmt:formatNumber>` – Format numbers

```jsp
<fmt:formatNumber value="12345.6789" type="currency" />
<!-- ₹12,345.68 -->
```

#### 2. `<fmt:formatDate>` – Format dates

```jsp
<fmt:formatDate value="${now}" pattern="dd-MM-yyyy" />
<!-- 14-06-2025 -->
```

---

### 🔹 Internationalization Tags (`fmt:`)

Used for multi-language (i18n) support by loading property files.

#### 1. `<fmt:setBundle>` – Load property file (e.g., messages\_en.properties)

```jsp
<fmt:setBundle basename="messages" />
```

#### 2. `<fmt:message>` – Get a message by key from the property file

```jsp
<fmt:message key="welcome.message" />
<!-- If welcome.message=Welcome to My App -->
```

---

### 🔹 SQL Tags (`sql:`) – **⚠️ Not Recommended in Real Apps**

Used to connect and run SQL queries directly from JSP. **Not secure or maintainable**, but useful for learning.

```jsp
<sql:setDataSource var="ds" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/test" user="root" password="1234" />

<sql:query dataSource="${ds}" var="result">
  SELECT * FROM users
</sql:query>

<c:forEach var="row" items="${result.rows}">
  <p>${row.username}</p>
</c:forEach>
```

> ✅ **Best Practice:** Use JDBC or JPA in Servlets or Controllers (MVC), not directly in JSP.

---

### ✅ Summary Table

| Tag                  | Purpose                       |
| -------------------- | ----------------------------- |
| `<c:if>`             | Conditional logic             |
| `<c:choose>`         | Multiple conditional branches |
| `<c:forEach>`        | Loops                         |
| `<c:set>`            | Set a variable                |
| `<c:out>`            | Print variable safely         |
| `<fmt:formatNumber>` | Format numbers/currency       |
| `<fmt:formatDate>`   | Format dates                  |
| `<fmt:setBundle>`    | Load language file            |
| `<fmt:message>`      | Display i18n message          |
| `<sql:query>` (⚠️)   | Run SQL queries (not advised) |

---

Here's a **JSTL Practical Example Project** with **Maven setup**, including a sample use case using `core`, `fmt`, and `sql` tags.

---

## ✅ Project Title: **Student Information Display using JSTL**

### 📁 Project Structure

```
JSTL-Demo/
│
├── src/
│   └── main/
│       ├── java/
│       ├── webapp/
│       │   ├── WEB-INF/
│       │   │   └── web.xml
│       │   └── index.jsp
│       │   └── students.jsp
├── pom.xml
```

---

## 1️⃣ Maven `pom.xml` – Include JSTL Dependency

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.example</groupId>
  <artifactId>jstl-demo</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <dependencies>
    <!-- JSTL -->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>

    <!-- Servlet API (provided by server like Tomcat) -->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
    </dependency>
  </dependencies>
</project>
```

---

## 2️⃣ `web.xml` – Basic Web App Configuration

```xml
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
                             http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">

  <welcome-file-list>
    <welcome-file>index.jsp</welcome-file>
  </welcome-file-list>
</web-app>
```

---

## 3️⃣ `index.jsp` – Redirect to Students Page

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:forward page="students.jsp"/>
```

---

## 4️⃣ `students.jsp` – Practical JSTL Example

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Student List</title>
</head>
<body>
    <h2>Student Information</h2>

    <c:set var="students" scope="page">
        <c:out value="${null}" default="[]" />
    </c:set>

    <c:set var="students" value="${pageScope.students}" scope="page"/>
    <c:set var="students">
        <c:out value="${null}" />
    </c:set>

    <c:set var="students">
        <c:out value="${null}" />
    </c:set>

    <!-- Create a list using c:set and c:forEach -->
    <c:set var="studentList">
        <c:out value="${[
            {'id': 1, 'name': 'Alice', 'dob': '2000-01-15'},
            {'id': 2, 'name': 'Bob', 'dob': '1999-06-30'},
            {'id': 3, 'name': 'Charlie', 'dob': '2001-12-20'}
        ]}" />
    </c:set>

    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>DOB</th>
            <th>Formatted DOB</th>
        </tr>
        <c:forEach var="student" items="${studentList}">
            <tr>
                <td><c:out value="${student.id}"/></td>
                <td><c:out value="${student.name}"/></td>
                <td><c:out value="${student.dob}"/></td>
                <td><fmt:formatDate value="${student.dob}" pattern="dd MMM yyyy"/></td>
            </tr>
        </c:forEach>
    </table>

    <!-- Conditional Display -->
    <c:if test="${fn:length(studentList) > 2}">
        <p>Total Students: <c:out value="${fn:length(studentList)}"/></p>
    </c:if>
</body>
</html>
```

---

## ✅ Features Demonstrated

| JSTL Feature     | Description                       |
| ---------------- | --------------------------------- |
| `c:forEach`      | Loops over student data           |
| `c:out`          | Safely prints values              |
| `c:if`           | Conditional rendering of messages |
| `fmt:formatDate` | Formats date of birth nicely      |

---

## ▶️ To Run the Project

1. Import the project into **IntelliJ / Eclipse** as a Maven project.
2. Use **Apache Tomcat** as the servlet container.
3. Right-click project → Run on Server.


