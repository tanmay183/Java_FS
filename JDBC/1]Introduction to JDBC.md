

## ✅ 1. Introduction to JDBC

### 🔷 What is JDBC?

* **JDBC (Java Database Connectivity)** is an API in Java used to **connect and interact with databases** (like MySQL, Oracle).
* It provides methods to **send SQL queries**, **retrieve results**, **insert, update, or delete data**.

📌 **Example Use**: Connecting a Java program to a MySQL database to display user data.

---

### 🔷 JDBC vs ODBC

| Feature  | JDBC                        | ODBC                          |
| -------- | --------------------------- | ----------------------------- |
| Language | Java                        | C/C++                         |
| Platform | Platform-independent (Java) | Platform-dependent            |
| API      | Java-based API              | Microsoft-based API           |
| Security | More secure in Java         | Less secure                   |
| Use      | Used in Java applications   | Used in Windows-based systems |

---

### 🔷 JDBC Architecture: 4 Components

1. **JDBC API**

   * Provides Java methods (e.g., `Connection`, `Statement`, `ResultSet`) to interact with DB.

2. **JDBC Driver Manager**

   * Manages database drivers and establishes connections.

3. **JDBC Drivers**

   * Translates Java calls into DB-specific calls (e.g., MySQL, Oracle).

4. **Database**

   * The actual data storage (e.g., MySQL, PostgreSQL, Oracle).

📌 Flow:
Java App → JDBC API → Driver Manager → JDBC Driver → Database

---

### 🔷 JDBC API (Core Interfaces & Classes)

| Interface/Class                   | Purpose                          |
| --------------------------------- | -------------------------------- |
| `DriverManager`                   | Loads and manages JDBC drivers   |
| `Connection`                      | Connects to the database         |
| `Statement` / `PreparedStatement` | Executes SQL queries             |
| `ResultSet`                       | Holds data returned from queries |

---

### 🔷 JDBC Driver Manager

* Handles a list of database drivers.
* Calls `DriverManager.getConnection(url, user, pass)` to connect.

---

### 🔷 JDBC Test Drivers

* These are **fake/mock drivers** used for **testing JDBC programs** without a real database.
* Useful in unit testing.

---

### 🔷 Databases

JDBC can work with many databases, such as:

* MySQL
* Oracle
* PostgreSQL
* SQL Server
* SQLite

---

## ✅ 2. JDBC Drivers

### 🔷 Types of JDBC Drivers

| Type | Name             | Description                                    | Example                   |
| ---- | ---------------- | ---------------------------------------------- | ------------------------- |
| 1    | JDBC-ODBC Bridge | Converts JDBC calls to ODBC                    | Deprecated                |
| 2    | Native API       | Uses native DB libraries                       | Oracle Thin Client        |
| 3    | Network Protocol | Sends JDBC over network to a middleware server | App Server                |
| 4    | Thin Driver      | **Pure Java**, connects directly to DB         | ✅ Most used (e.g., MySQL) |

---

### 🔷 Type-1: JDBC-ODBC Bridge

* Converts JDBC to ODBC.
* Requires ODBC driver.
* **Obsolete** and removed in Java 8+.

---

### 🔷 Type-2: Native API

* Converts JDBC calls into **native C/C++ DB APIs**.
* Needs DB native libraries on client machine.
* Fast but not portable.

---

### 🔷 Type-3: Network Protocol

* JDBC calls sent over the network to a **middleware server**, which talks to DB.
* Good for internet-based DBs.
* Complex setup.

---

### 🔷 Type-4: Thin Driver (**Most used**)

* **Pure Java** driver that communicates directly with the database using DB protocol (like MySQL protocol).
* Easy to deploy, **no native libraries required**.
* Used in production for **MySQL, Oracle, PostgreSQL**, etc.

---

### 🔷 Downloading and Adding JDBC Drivers

#### ✅ For MySQL:

1. **Download**: [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
2. **Add JAR to your project**:

   * In Eclipse/IDEA: Right-click Project → Build Path → Add External JARs → Select `.jar`

#### ✅ For Oracle:

1. **Download**: [Oracle JDBC Driver (ojdbc8.jar)](https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html)
2. **Add JAR**: Same as above


