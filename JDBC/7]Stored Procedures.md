
### ✅ **10. Stored Procedures Using `CallableStatement`**

Used to call stored procedures in the database.

#### ✅ Example Stored Procedure in MySQL:

```sql
DELIMITER //
CREATE PROCEDURE getEmployee(IN empId INT)
BEGIN
   SELECT * FROM employees WHERE id = empId;
END //
DELIMITER ;
```

#### ✅ Java Code to Call This:

```java
CallableStatement cs = con.prepareCall("{call getEmployee(?)}");
cs.setInt(1, 101); // Passing parameter to procedure
ResultSet rs = cs.executeQuery();

while (rs.next()) {
    System.out.println("ID: " + rs.getInt("id"));
    System.out.println("Name: " + rs.getString("name"));
}
```

---

### ✅ **11. Connection Pooling (Advanced)**

**Purpose:** To avoid opening/closing DB connections repeatedly (performance bottleneck).

#### ✅ Benefits:

* High performance
* Reduced DB load
* Better resource management

#### ✅ Common Libraries:

* **HikariCP** (modern, used with Spring Boot)
* **Apache DBCP**
* **C3P0**

#### ✅ Example: HikariCP Code (Standalone)

```java
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;

public class HikariConnectionExample {
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        config.setUsername("root");
        config.setPassword("password");
        config.setMaximumPoolSize(5);

        HikariDataSource ds = new HikariDataSource(config);

        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM employees");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        }

        ds.close(); // Close the pool when app shuts down
    }
}
```

---

## ✅ Final Practice Example with All 9 JDBC Objects

```java
import java.sql.*;

public class JDBCPracticeAllObjects {
    public static void main(String[] args) {
        try {
            // 1. DriverManager
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");

            // 3. Statement
            Statement stmt = con.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM employees");
            while (rs1.next()) {
                System.out.println("Total Employees: " + rs1.getInt(1));
            }

            // 4. PreparedStatement
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employees WHERE id = ?");
            ps.setInt(1, 101);
            ResultSet rs2 = ps.executeQuery();
            while (rs2.next()) {
                System.out.println("Employee Found: " + rs2.getString("name"));
            }

            // 5. ResultSet
            ResultSet rs3 = stmt.executeQuery("SELECT * FROM employees");
            while (rs3.next()) {
                System.out.println("Name: " + rs3.getString("name"));
            }

            // 6. ResultSetMetaData
            ResultSetMetaData meta = rs3.getMetaData();
            System.out.println("Columns:");
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.println(meta.getColumnName(i) + " - " + meta.getColumnTypeName(i));
            }

            // 7. DatabaseMetaData
            DatabaseMetaData dbMeta = con.getMetaData();
            System.out.println("DB Name: " + dbMeta.getDatabaseProductName());
            System.out.println("DB Version: " + dbMeta.getDatabaseProductVersion());

            // 8. CallableStatement (Stored Procedure)
            CallableStatement cs = con.prepareCall("{call getEmployee(?)}");
            cs.setInt(1, 101);
            ResultSet rs4 = cs.executeQuery();
            while (rs4.next()) {
                System.out.println("From SP: " + rs4.getString("name"));
            }

            // 9. Batch Processing
            PreparedStatement ps2 = con.prepareStatement("INSERT INTO employees (id, name) VALUES (?, ?)");
            for (int i = 200; i < 205; i++) {
                ps2.setInt(1, i);
                ps2.setString(2, "Emp_" + i);
                ps2.addBatch();
            }
            int[] results = ps2.executeBatch();
            System.out.println("Inserted rows: " + results.length);

            // Close all
            cs.close();
            ps.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

✅ This full program uses:

* `DriverManager`
* `Connection`
* `Statement`
* `PreparedStatement`
* `CallableStatement`
* `ResultSet`
* `ResultSetMetaData`
* `DatabaseMetaData`
* `Batch Processing`


