
### ✅ 12. JDBC with MVC (Model-View-Controller)

**Architecture:**

| Layer                    | Role                                              |
| ------------------------ | ------------------------------------------------- |
| **JSP (View)**           | Display data to the user (HTML + EL + JSTL)       |
| **Servlet (Controller)** | Accept user requests, call DAO, forward to JSP    |
| **DAO (Model)**          | Data Access Layer (uses JDBC to interact with DB) |

---

**✔️ How it works:**

1. **User sends request** → Servlet (`Controller`)
2. **Servlet calls DAO** → DAO interacts with DB using JDBC
3. **DAO returns data** → Servlet stores it in request/session scope
4. **Forward to JSP** → JSP displays data (`View`)

---

**Example Flow:**

* `login.jsp` → `LoginServlet.java` → `UserDAO.java` → DB
* DB response → `UserDAO` → `LoginServlet` → `welcome.jsp`

---

**✔️ DAO Pattern Purpose:**

* Encapsulates all DB code (cleaner separation)
* Reusable DB logic
* Easier testing and debugging
* Reduces duplication of JDBC logic

---

### ✅ 13. ORM vs JDBC (Conceptual)

| Feature            | **JDBC**                    | **ORM (Hibernate / JPA)**            |
| ------------------ | --------------------------- | ------------------------------------ |
| **Level**          | Low-level, manual           | High-level, abstracted               |
| **SQL Handling**   | Manually written SQL        | Auto-generated or abstracted queries |
| **Object Mapping** | Manual (ResultSet → Object) | Automatic (Entity mapping)           |
| **Performance**    | Basic                       | Caching, Lazy loading, Batching      |
| **Transactions**   | Manual handling             | Built-in transaction support         |
| **Code Size**      | More boilerplate            | Less boilerplate                     |
| **Use Case**       | Small apps, learning JDBC   | Large-scale apps, enterprise-grade   |

---

**Why switch to ORM in big apps?**

* Reduces boilerplate JDBC code
* Supports automatic schema generation
* Enables caching, lazy loading
* Easier transaction management
* Clean mapping of Java classes to DB tables (Entity class ↔ Table)

