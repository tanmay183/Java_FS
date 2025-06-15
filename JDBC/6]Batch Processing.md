
### ✅ 8. Batch Processing

**Purpose**: Efficiently execute **multiple SQL queries** (like inserts) in one go to reduce database round-trips.

#### ✅ Example:

```java
import java.sql.*;

public class BatchInsertExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb"; // Replace with your DB URL
        String user = "root";
        String password = "password";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            // Prepare insert query with placeholders
            String sql = "INSERT INTO emp (id, name) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            // First record
            ps.setInt(1, 1);
            ps.setString(2, "Ram");
            ps.addBatch(); // Add to batch

            // Second record
            ps.setInt(1, 2);
            ps.setString(2, "Shyam");
            ps.addBatch(); // Add to batch

            // Execute both insert queries at once
            int[] result = ps.executeBatch();

            // Optional: display how many rows inserted per statement
            for (int res : result) {
                System.out.println("Rows affected: " + res);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

#### ✅ Benefits:

* **Improves performance** when inserting/updating large number of rows.
* Reduces **network calls** between Java app and DB.

---

### ✅ 9. Error Handling in JDBC

#### ✅ Best Practice: Use `try-with-resources`

Automatically closes JDBC resources (`Connection`, `Statement`, `ResultSet`) even if an exception occurs.

#### ✅ Example:

```java
import java.sql.*;

public class JdbcErrorHandlingExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "password";

        String query = "SELECT * FROM emp";

        // Try-with-resources: Auto-close Connection, Statement, and ResultSet
        try (
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + " - " + name);
            }

        } catch (SQLException e) {
            // Log error details
            System.err.println("SQL Error Code: " + e.getErrorCode());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Message: " + e.getMessage());
        }
    }
}
```

#### ✅ Notes:

* Always **log error messages**, not just print stack trace in real applications.
* You can use a **logging framework** like Log4j or SLF4J instead of `System.err`.


