

1. **Using `Statement` only**
2. **Using `PreparedStatement` only**

Both connect to the same Derby database and perform CRUD operations on a table named `employees`.

---

## ✅ 1. CRUD Using `Statement` Only

```java
import java.sql.*;

public class StatementCRUD {
    static final String JDBC_URL = "jdbc:derby:employeeDB;create=true";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            System.out.println("Connected to Derby DB");

            createTable(conn);

            insertEmployee(conn, 1, "John Doe", 50000);
            readEmployees(conn);
            updateEmployee(conn, 1, 60000);
            deleteEmployee(conn, 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE employees ("
                   + "id INT PRIMARY KEY, "
                   + "name VARCHAR(100), "
                   + "salary DOUBLE)";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table created.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Table already exists.");
            } else {
                throw e;
            }
        }
    }

    public static void insertEmployee(Connection conn, int id, String name, double salary) throws SQLException {
        String sql = "INSERT INTO employees VALUES (" + id + ", '" + name + "', " + salary + ")";
        try (Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            System.out.println("Inserted: " + rows + " row(s).");
        }
    }

    public static void readEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Employee Records:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Salary: " + rs.getDouble("salary"));
            }
        }
    }

    public static void updateEmployee(Connection conn, int id, double newSalary) throws SQLException {
        String sql = "UPDATE employees SET salary = " + newSalary + " WHERE id = " + id;
        try (Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            System.out.println("Updated: " + rows + " row(s).");
        }
    }

    public static void deleteEmployee(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = " + id;
        try (Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            System.out.println("Deleted: " + rows + " row(s).");
        }
    }
}
```

---

## ✅ 2. CRUD Using `PreparedStatement` Only

```java
import java.sql.*;

public class PreparedStatementCRUD {
    static final String JDBC_URL = "jdbc:derby:employeeDB;create=true";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            System.out.println("Connected to Derby DB");

            createTable(conn);

            insertEmployee(conn, 2, "Jane Smith", 70000);
            readEmployees(conn);
            updateEmployee(conn, 2, 75000);
            deleteEmployee(conn, 2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE employees ("
                   + "id INT PRIMARY KEY, "
                   + "name VARCHAR(100), "
                   + "salary DOUBLE)";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table created.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Table already exists.");
            } else {
                throw e;
            }
        }
    }

    public static void insertEmployee(Connection conn, int id, String name, double salary) throws SQLException {
        String sql = "INSERT INTO employees VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setDouble(3, salary);
            int rows = pstmt.executeUpdate();
            System.out.println("Inserted: " + rows + " row(s).");
        }
    }

    public static void readEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employees";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Employee Records:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Salary: " + rs.getDouble("salary"));
            }
        }
    }

    public static void updateEmployee(Connection conn, int id, double newSalary) throws SQLException {
        String sql = "UPDATE employees SET salary = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newSalary);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            System.out.println("Updated: " + rows + " row(s).");
        }
    }

    public static void deleteEmployee(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println("Deleted: " + rows + " row(s).");
        }
    }
}
```

---

## 🔍 Notes

* Both programs use the **same table structure**.
* You can run both sequentially in your IDE or terminal — the table will persist since Derby creates an on-disk DB.
* You can delete the database folder (e.g., `employeeDB`) to reset.




## 🔍 Difference Between Statement vs PreparedStatement

| Feature     | `Statement`                     | `PreparedStatement`           |
| ----------- | ------------------------------- | ----------------------------- |
| SQL Writing | Static, must concatenate values | Uses placeholders `?`         |
| Security    | ❌ Prone to SQL injection        | ✅ Safe against SQL injection  |
| Performance | Compiled every time             | Compiled once, reused         |
| Use Case    | Simple one-time queries         | Repetitive or dynamic queries |

---

## ⏱ Time & Space Complexity

* **Time Complexity**: Depends on DB engine, but `O(1)` per operation at code level
* **Space Complexity**: Minimal, constant auxiliary memory used

