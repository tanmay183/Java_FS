
## ✅ **4. Steps to Connect Java with Database**

Let’s understand **step-by-step how Java connects to a database** like MySQL using JDBC.

---

### 🔶 Step-by-Step Explanation:

#### 🧩 1. **Load the JDBC Driver**

This step registers the driver class with the `DriverManager`.

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

> This tells Java to load the MySQL JDBC driver (from the connector JAR file).
> Note: From JDBC 4.0+, this step is optional if the driver is in the classpath.

---

#### 🔌 2. **Establish a Connection**

We use `DriverManager.getConnection()` to connect to the database.

```java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/testdb", "root", "password"
);
```

> `testdb` = database name
> `"root"` and `"password"` = DB credentials

---

#### 🛠️ 3. **Create a Statement Object**

We need a `Statement`, `PreparedStatement`, or `CallableStatement` to run queries.

```java
Statement stmt = con.createStatement();
```

---

#### 🧪 4. **Execute a Query**

```java
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
```

* For `SELECT`: use `executeQuery()`
* For `INSERT`, `UPDATE`, `DELETE`: use `executeUpdate()`

---

#### 📋 5. **Process the Results**

Use `ResultSet` to get data from the query.

```java
while (rs.next()) {
    System.out.println("Username: " + rs.getString("username"));
}
```

---

#### 🚪 6. **Close the Connection**

```java
rs.close();
stmt.close();
con.close();
```

> Always close to free up database resources.

---

### ✅ Full Working Example:

```java
import java.sql.*;

public class JdbcConnectionDemo {
    public static void main(String[] args) {
        try {
            // 1. Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb", "root", "password");

            // 3. Create Statement
            Statement stmt = con.createStatement();

            // 4. Execute Query
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            // 5. Process Result
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " Name: " + rs.getString("name"));
            }

            // 6. Close Resources
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## ✅ **5. Statement vs PreparedStatement vs CallableStatement**

| Feature              | **Statement**           | **PreparedStatement**                | **CallableStatement**               |
| -------------------- | ----------------------- | ------------------------------------ | ----------------------------------- |
| Used for             | Simple static SQL       | Parameterized/dynamic SQL            | Calling Stored Procedures           |
| SQL Injection Safe   | ❌ **Not safe**          | ✅ **Safe**                           | ✅ **Safe**                          |
| Precompiled          | ❌ No                    | ✅ Yes                                | ✅ Yes                               |
| Performance          | Slower for repeated use | Faster due to reuse                  | Good if stored procedures are tuned |
| Parameters supported | ❌ Hardcoded query       | ✅ Use `?` placeholders               | ✅ Use `{call procName(?)}` syntax   |
| Example Syntax       | `"SELECT * FROM users"` | `"SELECT * FROM users WHERE id = ?"` | `"{call getUser(?)}"`               |

---

### 🔍 Examples for Each:

#### ➤ **Statement**

```java
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
```

> ⚠️ Prone to SQL Injection:

```java
String input = "' OR 1=1 --";
stmt.executeQuery("SELECT * FROM users WHERE name = '" + input + "'");
```

---

#### ➤ **PreparedStatement**

```java
PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = ?");
ps.setInt(1, 5);  // Bind value to "?"
ResultSet rs = ps.executeQuery();
```

> ✅ Secure and prevents injection
> ✅ Faster on repeated execution with different parameters

---

#### ➤ **CallableStatement**

```java
CallableStatement cs = con.prepareCall("{call getUser(?)}");
cs.setInt(1, 10);  // Call stored procedure with parameter
ResultSet rs = cs.executeQuery();
```

> ✅ Useful for calling stored procedures in DB
> ✅ Common in enterprise apps for encapsulated logic

---

### 📝 Conclusion:

* ✅ **Use Statement** only for quick and simple, read-only queries.
* ✅ **Use PreparedStatement** for **most real applications** (security + performance).
* ✅ **Use CallableStatement** when working with **stored procedures**.

