
### **12. JSP with JDBC**

JSP is primarily designed for presentation (view). However, for learning purposes, connecting JSP directly to a database using JDBC can help understand the basics — though it's bad practice for real applications (logic should be in Servlet or DAO layer).

#### ✅ **1. Connecting JSP to a Database using JDBC**

**Steps:**

1. Load JDBC Driver (for example, MySQL).
2. Create a connection using `DriverManager`.
3. Execute SQL query.
4. Display data using JSP/HTML.

📌 **Example: Display All Students from a Database**

```jsp
<%@ page import="java.sql.*" %>
<html>
<head><title>Student List</title></head>
<body>
<h2>Student List</h2>
<table border="1">
<tr><th>ID</th><th>Name</th><th>Email</th></tr>

<%
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "password");

        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM students");

        while(rs.next()) {
%>
<tr>
    <td><%= rs.getInt("id") %></td>
    <td><%= rs.getString("name") %></td>
    <td><%= rs.getString("email") %></td>
</tr>
<%
        }
    } catch(Exception e) {
        out.println("Error: " + e);
    } finally {
        try { if(rs != null) rs.close(); } catch(Exception e) {}
        try { if(stmt != null) stmt.close(); } catch(Exception e) {}
        try { if(conn != null) conn.close(); } catch(Exception e) {}
    }
%>
</table>
</body>
</html>
```

#### ⚠️ **Why This Is Not Recommended in Real Projects**

* JSP should only handle **presentation**.
* JDBC code belongs in **DAO or Servlet layer** for better maintainability and separation of concerns.

---

#### ✅ **2. Displaying Dynamic Table Content Using JSTL/EL**

Assume you have a list of student objects in a `request` or `session`.

📌 **In Servlet:**

```java
List<Student> students = dao.getAllStudents();
request.setAttribute("studentList", students);
request.getRequestDispatcher("view.jsp").forward(request, response);
```

📌 **In JSP with JSTL:**

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table border="1">
<tr><th>ID</th><th>Name</th><th>Email</th></tr>

<c:forEach var="s" items="${studentList}">
<tr>
    <td>${s.id}</td>
    <td>${s.name}</td>
    <td>${s.email}</td>
</tr>
</c:forEach>
</table>
```

---

### **13. Custom Tag Libraries (Advanced JSP)**

Custom tags allow **reusability** and **abstraction** — like creating your own JSTL.

#### ✅ **Steps to Create a Custom Tag Library**

1. Create a Tag Handler class by extending `TagSupport` or `SimpleTagSupport`.
2. Create a `.tld` (Tag Library Descriptor) file.
3. Use your custom tag in JSP.

#### 📌 Example: Custom Tag to Show Current Date

**Step 1: Create Java Class**

```java
package mytags;
import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class CurrentDateTag extends SimpleTagSupport {
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.print("Current Date: " + new java.util.Date());
    }
}
```

**Step 2: Create TLD File (`mytags.tld`)**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<taglib xmlns="http://java.sun.com/xml/ns/javaee"
        version="2.1">

    <tlib-version>1.0</tlib-version>
    <short-name>MyTags</short-name>
    <uri>http://mytags</uri>

    <tag>
        <name>currentDate</name>
        <tag-class>mytags.CurrentDateTag</tag-class>
        <body-content>empty</body-content>
    </tag>
</taglib>
```

**Step 3: Use in JSP**

```jsp
<%@ taglib prefix="my" uri="http://mytags" %>
<my:currentDate />
```

---

### ✅ Benefits of Custom Tags

* Clean and reusable.
* Reduces Java code in JSP.
* Useful for common UI components (like pagination, tables, etc).

---

### 📌 Best Practice Summary

| Feature     | Recommended Usage                     |
| ----------- | ------------------------------------- |
| JDBC in JSP | ❌ Avoid. Use DAO or Servlet.          |
| JSTL/EL     | ✅ Clean and safe for displaying data. |
| Custom Tags | ✅ For reusable UI logic.              |


