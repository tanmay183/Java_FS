
### ✅ **9. ServletConfig vs ServletContext**

| Feature         | ServletConfig                               | ServletContext                                       |
| --------------- | ------------------------------------------- | ---------------------------------------------------- |
| **Scope**       | Per **servlet**                             | Whole **web application**                            |
| **Used For**    | Getting init params specific to one servlet | Getting context-wide init params or shared resources |
| **Lifecycle**   | Created when the servlet is initialized     | Created when the web application is deployed         |
| **Accessed By** | `getServletConfig()` inside servlet         | `getServletContext()` inside servlet                 |
| **Use Case**    | E.g., DB config just for `LoginServlet`     | E.g., app-level logging directory path               |

#### 🧠 Example

**web.xml**

```xml
<servlet>
    <servlet-name>MyServlet</servlet-name>
    <servlet-class>com.example.MyServlet</servlet-class>
    <init-param>
        <param-name>dbUser</param-name>
        <param-value=root</param-value>
    </init-param>
</servlet>

<context-param>
    <param-name>appName</param-name>
    <param-value>MyWebApp</param-value>
</context-param>
```

**Inside Servlet:**

```java
public class MyServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        // ServletConfig
        String dbUser = getServletConfig().getInitParameter("dbUser");

        // ServletContext
        String appName = getServletContext().getInitParameter("appName");
    }
}
```

---

### ✅ **10. File Upload & Download with Servlet**

#### 🟢 **Upload File using Servlet 3.0 (No external library needed)**

**1. Enable multipart config:**

```java
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        Part filePart = req.getPart("file");  // from form input name="file"
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();

        // Save the file
        File uploads = new File("C:/uploads");
        File file = new File(uploads, fileName);
        Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        
        res.getWriter().println("File uploaded successfully!");
    }
}
```

**HTML Form:**

```html
<form action="upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" value="Upload" />
</form>
```

---

#### 🟡 **Download File using Servlet**

```java
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String fileName = req.getParameter("name"); // e.g., ?name=test.pdf
        File file = new File("C:/uploads", fileName);

        // Set content type and headers
        res.setContentType(getServletContext().getMimeType(file.getName()));
        res.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        res.setContentLengthLong(file.length());

        // Stream the file
        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = res.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
```

