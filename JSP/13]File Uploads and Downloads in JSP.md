

## ✅ 14. File Uploads and Downloads in JSP

---

### 🔹 A. File Upload in JSP (Handling `multipart/form-data`)

JSP alone can't handle file uploads directly. You need:

* Apache Commons FileUpload library
* `multipart/form-data` form
* Java code to parse uploaded files

> ✅ **Recommended:** Use a **Servlet** to process file upload; JSP should only be used to show the UI.

---

### ✅ Example: Upload a File

#### 1️⃣ **Step 1: JSP Form**

```jsp
<!-- upload.jsp -->
<form action="uploadServlet" method="post" enctype="multipart/form-data">
    Select file: <input type="file" name="file" />
    <input type="submit" value="Upload" />
</form>
```

#### 2️⃣ **Step 2: Servlet (uploadServlet.java)**

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,  // 1MB
    maxFileSize = 1024 * 1024 * 5,    // 5MB
    maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class uploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        filePart.write(uploadPath + File.separator + fileName);

        response.getWriter().println("File uploaded to: " + uploadPath + "/" + fileName);
    }
}
```

#### 3️⃣ **Create Upload Folder**

Ensure a folder named `uploads` exists inside your project root or is dynamically created in Servlet.

---

### 🔹 B. File Download in JSP/Servlet

To allow users to download a file, stream it using a Servlet.

#### ✅ Example: File Download Servlet

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String fileName = "example.pdf";
    String filePath = getServletContext().getRealPath("") + "uploads/" + fileName;

    File file = new File(filePath);
    FileInputStream in = new FileInputStream(file);

    response.setContentType("application/octet-stream");
    response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

    OutputStream out = response.getOutputStream();
    byte[] buffer = new byte[1024];
    int bytesRead;
    while ((bytesRead = in.read(buffer)) != -1) {
        out.write(buffer, 0, bytesRead);
    }
    in.close();
    out.close();
}
```

---

### ✅ 15. Integration with Backend (DAO + Servlet + JSP)

---

### 🔹 MVC Recap:

* **Model** – DAO classes / JavaBeans / POJOs
* **View** – JSP (renders UI using data)
* **Controller** – Servlet (takes request and sends response)

---

### ✅ Example: Fetch and Display Students from DB

#### 1️⃣ **DAO Class** – `StudentDAO.java`

```java
public class StudentDAO {
    public List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();
        Connection conn = DBUtil.getConnection(); // Assume DBUtil gives DB connection
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM students");

        while (rs.next()) {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setEmail(rs.getString("email"));
            list.add(s);
        }
        return list;
    }
}
```

#### 2️⃣ **Servlet (Controller)** – `StudentServlet.java`

```java
@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        try {
            StudentDAO dao = new StudentDAO();
            List<Student> list = dao.getAllStudents();

            request.setAttribute("studentList", list);
            RequestDispatcher rd = request.getRequestDispatcher("viewStudents.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### 3️⃣ **JSP (View)** – `viewStudents.jsp`

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Students:</h2>
<table border="1">
<tr><th>ID</th><th>Name</th><th>Email</th></tr>
<c:forEach var="student" items="${studentList}">
<tr>
    <td>${student.id}</td>
    <td>${student.name}</td>
    <td>${student.email}</td>
</tr>
</c:forEach>
</table>
```

---

### ✅ Benefits of MVC with DAO

| Layer          | Role                                  |
| -------------- | ------------------------------------- |
| **Model**      | Business logic, DB operations         |
| **View**       | JSP handles UI only (no Java code)    |
| **Controller** | Servlet takes input and prepares data |

---

### 🧠 Summary

| Topic           | Key Points                                                      |
| --------------- | --------------------------------------------------------------- |
| File Upload     | Use `multipart/form-data`, Servlet handles file saving          |
| File Download   | Stream file as byte array in Servlet                            |
| DAO Integration | Keep DB logic separate in DAO classes                           |
| MVC Pattern     | Clean separation: JSP (view), Servlet (controller), DAO (model) |


