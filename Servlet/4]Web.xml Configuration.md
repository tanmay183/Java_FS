
## 🔹 What is `web.xml`?

`web.xml` is a **deployment descriptor** located inside the `WEB-INF` directory of a Java web application. It is used to configure the application's components like:

* Servlets
* Servlet Mappings
* Filters
* Listeners
* Welcome pages
* Error pages
* Initialization parameters

---

## 🔸 Declaring a Servlet and Mapping It

You declare a servlet in `web.xml` using the `<servlet>` tag and map it using `<servlet-mapping>`.

### ✅ Syntax:

```xml
<servlet>
    <servlet-name>MyServlet</servlet-name>
    <servlet-class>com.example.MyServlet</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>MyServlet</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
```

### 🔹 Explanation:

* `<servlet>`: Declares the servlet.

  * `<servlet-name>`: Logical name of the servlet (used as a reference).
  * `<servlet-class>`: Fully-qualified class name of the servlet.

* `<servlet-mapping>`: Maps a URL pattern to the servlet.

  * `<url-pattern>`: Defines the URL through which the servlet is accessed (e.g., `/hello`).

---

## 🔸 init-param (Servlet Initialization Parameters)

Used to pass configuration data **specific to a servlet**.

### ✅ Syntax:

```xml
<servlet>
    <servlet-name>MyServlet</servlet-name>
    <servlet-class>com.example.MyServlet</servlet-class>
    
    <init-param>
        <param-name>adminEmail</param-name>
        <param-value>admin@example.com</param-value>
    </init-param>
</servlet>
```

### 🔹 Access in Java Code:

Inside your servlet class:

```java
String adminEmail = getServletConfig().getInitParameter("adminEmail");
```

### 🔹 Use Case:

* Pass data that might change (e.g., email addresses, page limits) without modifying Java code.

---

## 🔸 context-param (Application-wide Parameters)

Used to define parameters that are **available to all servlets and JSPs**.

### ✅ Syntax:

```xml
<context-param>
    <param-name>websiteName</param-name>
    <param-value>My Cool Website</param-value>
</context-param>
```

### 🔹 Access in Servlet or JSP:

```java
String siteName = getServletContext().getInitParameter("websiteName");
```

### 🔹 Use Case:

* Application-wide settings like:

  * Website title
  * Support contact
  * Theme name

---

## 🔸 Summary Table

| Feature         | Scope          | Defined In        | Accessed By                              |
| --------------- | -------------- | ----------------- | ---------------------------------------- |
| `init-param`    | Single Servlet | `<servlet>`       | `getServletConfig().getInitParameter()`  |
| `context-param` | Entire App     | `<context-param>` | `getServletContext().getInitParameter()` |

---

