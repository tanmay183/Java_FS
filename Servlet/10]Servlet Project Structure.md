

## ✅ **15. Servlet Project Structure**

### 🔧 Standard Folder Layout (Maven-style or Manual WAR):

A typical servlet web application follows this layout:

```
MyWebApp/
│
├── index.html                  --> Public HTML or JSP entry page
├── login.jsp                  --> Optional JSP front-end
│
└── WEB-INF/
    ├── web.xml                --> Deployment descriptor (config file for servlets)
    ├── classes/               --> Compiled .class files of servlets and other Java classes
    │    └── com/example/HelloServlet.class
    └── lib/                   --> JAR files (e.g., JDBC drivers, utility libraries)
         └── mysql-connector.jar
```

> 📂 `WEB-INF` is protected — browsers can't access files directly from here. It stores configs, classes, and libraries.

---

### 📦 WAR Packaging

A **WAR** (Web Application Archive) is a packaged `.war` file that contains your full web project, ready for deployment on a server like **Apache Tomcat**.

#### WAR structure inside the file:

```
MyWebApp.war
├── index.html
└── WEB-INF/
    ├── web.xml
    ├── classes/
    └── lib/
```

To create a WAR:

* If using Eclipse → Right-click project → **Export > WAR file**
* In Maven:

```xml
<packaging>war</packaging>
```

---

### 🚀 Deployment on Tomcat:

1. Place the `.war` file in:

```
<tomcat-directory>/webapps/
```

2. Start Tomcat:

```
<tomcat-directory>/bin/startup.sh (Linux/Mac)
startup.bat (Windows)
```

3. Access via browser:

```
http://localhost:8080/MyWebApp/
```

---

## ✅ **16. Security in Servlets**

### 🔐 1. Basic Authentication via `web.xml`

You can protect certain servlet URLs with login prompts:

```xml
<!-- Declare roles -->
<security-role>
    <role-name>admin</role-name>
</security-role>

<!-- Protect URL patterns -->
<security-constraint>
    <web-resource-collection>
        <web-resource-name>Admin Area</web-resource-name>
        <url-pattern>/admin/*</url-pattern>
    </web-resource-collection>
    <auth-constraint>
        <role-name>admin</role-name>
    </auth-constraint>
</security-constraint>

<!-- Define login method -->
<login-config>
    <auth-method>BASIC</auth-method>
    <realm-name>MyRealm</realm-name>
</login-config>
```

> ⚠️ Basic auth is not secure without HTTPS — credentials are base64-encoded, not encrypted.

---

### 🛡️ 2. Role-Based Access Control

Servlet containers (like Tomcat) check if logged-in users have specific **roles** (e.g., `admin`, `user`) before allowing access.

You can also check programmatically inside servlet:

```java
if (request.isUserInRole("admin")) {
    // Allow access
} else {
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
}
```

---

### 🔒 3. HTTPS and Secure Cookies

* **HTTPS** ensures encrypted communication.

  * Set up an SSL certificate on your server.
  * Access app via `https://` instead of `http://`.

* **Secure Cookie**: Prevents cookies from being sent over non-HTTPS.

```java
Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
sessionCookie.setSecure(true);  // Send only over HTTPS
sessionCookie.setHttpOnly(true); // JS can't access
response.addCookie(sessionCookie);
```

