
### ✅ JDBC (Java Database Connectivity) API Basics

JDBC is a Java API that allows Java applications to **connect and interact with databases** (like MySQL, Oracle, PostgreSQL, etc.).

---

### 📌 Important JDBC Interfaces:

#### 1. **DriverManager**

* **Purpose:** Loads the database driver and establishes a connection to the database.
* **Common method:**

  ```java
  Connection con = DriverManager.getConnection(url, username, password);
  ```
* **Example URL:** `"jdbc:mysql://localhost:3306/mydb"`

---

#### 2. **Connection**

* **Purpose:** Represents the **active connection** between Java and the database.
* **Used to:** Create `Statement`, `PreparedStatement`, or `CallableStatement` objects.

---

#### 3. **Statement**

* **Purpose:** Executes **static SQL queries** (not parameterized).
* **Example:**

  ```java
  Statement stmt = con.createStatement();
  ResultSet rs = stmt.executeQuery("SELECT * FROM users");
  ```

---

#### 4. **PreparedStatement**

* **Purpose:** Executes **precompiled SQL queries** with parameters. Prevents **SQL injection**.
* **Example:**

  ```java
  PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = ?");
  ps.setInt(1, 5);
  ResultSet rs = ps.executeQuery();
  ```

---

#### 5. **ResultSet**

* **Purpose:** Stores the result of SQL queries.
* **Used to:** Iterate over the results.
* **Example:**

  ```java
  while (rs.next()) {
      System.out.println(rs.getString("username"));
  }
  ```

---

#### 6. **CallableStatement**

* **Purpose:** Used to **call stored procedures** in the database.
* **Example:**

  ```java
  CallableStatement cs = con.prepareCall("{call getUser(?)}");
  cs.setInt(1, 10);
  ResultSet rs = cs.executeQuery();
  ```

---

### 🔁 JDBC Flow Summary:

```java
1. Load Driver (optional from JDBC 4.0)
2. Get Connection via DriverManager
3. Create Statement / PreparedStatement / CallableStatement
4. Execute Query
5. Process ResultSet
6. Close resources
```

---

### ✅ Sample JDBC Code (MySQL):

```java
import java.sql.*;

public class JdbcExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "root";

        try {
            Connection con = DriverManager.getConnection(url, username, password);

            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email = ?");
            ps.setString(1, "abc@gmail.com");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```


