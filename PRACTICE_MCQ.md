
### ✅ **Question 1**

**What is the result of the following code?**

```java
class A {
    public void display() {
        System.out.println("Class A");
    }
}

class B extends A {
    public void display() {
        System.out.println("Class B");
    }
}

public class Test {
    public static void main(String[] args) {
        A obj = new B();
        obj.display();
    }
}
```

**Options:**

* Class A
* ✅ Class B
* Compilation Error
* Runtime Error

**Explanation:**
Object is of type B, method is overridden, so "Class B" is printed due to runtime polymorphism.

---

### ✅ **Question 2**

**What is the initial capacity of an ArrayList if none is specified?**

**Options:**

* 0
* 5
* ✅ 10
* 16

**Explanation:**
By default, Java's `ArrayList` has an initial capacity of 10.

---

### ✅ **Question 3**

**Which SQL statement groups rows that have the same values into summary rows, like "find the number of customers in each country"?**

**Options:**

* WHERE
* HAVING
* ✅ GROUP BY
* ORDER BY

**Explanation:**
`GROUP BY` is used to group data by columns for aggregation.

---

### ✅ **Question 4**

**Which lifecycle method of a servlet is called only once when the servlet is first loaded?**

**Options:**

* service()
* doGet()
* ✅ init()
* destroy()

**Explanation:**
`init()` runs once during servlet loading.

---

### ✅ **Question 5**

**Consider the following classes:**

```java
class Animal {
    public void makeSound() {
        System.out.println("Generic animal sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}
```

**Which statement is true?**

**Options:**

* An Animal object will call the makeSound method of the Dog class.
* A Dog object will call the makeSound method of the Animal class unless explicitly overridden.
* ✅ A Dog object will call its own overridden makeSound method when the method is invoked.
* The code will not compile because makeSound is not declared private in Animal.

**Explanation:**
Overridden methods are resolved at runtime using actual object type.

---

### ✅ **Question 6**

**Which JSP action tag is used to include the content of another resource in the current JSP page?**

**Options:**

* ✅ `<jsp:include>`
* `<jsp:forward>`
* `<jsp:param>`
* `<jsp:useBean>`

**Explanation:**
`<jsp:include>` is used to include other content dynamically.

---

### ✅ **Question 7**

**How do you remove a table from a database in SQL?**

**Options:**

* DELETE TABLE table\_name;
* DROP table\_name;
* REMOVE table\_name;
* ✅ DROP TABLE table\_name;

**Explanation:**
`DROP TABLE` permanently deletes the table from the database.

---

### ✅ **Question 8**

**Which method is used to establish a connection to a database in JDBC?**

**Options:**

* ✅ DriverManager.getConnection()
* Connection.create()
* DriverManager.connect()
* Connection.initialize()

**Explanation:**
`DriverManager.getConnection()` returns a `Connection` object.

---

### ✅ **Question 9**

**Which method in the HttpServlet class is used to handle HTTP GET requests?**

**Options:**

* doPost()
* ✅ doGet()
* service()
* init()

**Explanation:**
`doGet()` handles HTTP GET requests.

---

### ✅ **Question 10**

**What is the default sorting order when using the 'ORDER BY' clause in SQL?**

**Options:**

* DESC
* ✅ ASC
* RANDOM
* NONE

**Explanation:**
`ASC` (ascending) is the default sort order.

---

### ✅ **Question 11**

**In a servlet, how do you retrieve a parameter sent in an HTTP request?**

**Options:**

* ✅ `request.getParameter()`
* `request.getParameterValue()`
* `request.getParameterName()`
* `request.getParameterList()`

**Explanation:**
`getParameter()` is used to fetch request parameters (e.g., form input).

---

### ✅ **Question 12**

**Which of the following statements about the 'try' block is correct?**

**Options:**

* A try block must be followed by a catch block.
* A try block must be followed by a finally block.
* ✅ A try block must be followed by either a catch block or a finally block.
* A try block must be followed by both a catch block and a finally block.

**Explanation:**
Only one (either `catch` or `finally`) is mandatory.

---

### ✅ **Question 13**

**How do you send a redirect response to the client from a servlet?**

**Options:**

* ✅ `response.sendRedirect(url)`
* `response.forward(url)`
* `response.include(url)`
* `response.dispatch(url)`

**Explanation:**
`sendRedirect()` sends a new HTTP request to the client.

---

### ✅ **Question 14**

**Given the following classes, what will be the output?**

```java
class Shape {
    void show() {
        System.out.println("Shape");
    }
}

class Circle extends Shape {
    void show() {
        System.out.println("Circle");
    }
}

public class Test {
    public static void main(String[] args) {
        Shape s = new Circle();
        s.show();
    }
}
```

**Options:**

* Shape
* ✅ Circle
* Compilation Error
* Runtime Error

**Explanation:**
Dynamic method dispatch; subclass method called.

---

### ✅ **Question 15**

**How can you forward a request to another resource (e.g., a servlet or another JSP) from a JSP page?**

**Options:**

* ✅ `<jsp:forward page="target.jsp" />`
* `<jsp:include page="target.jsp" />`
* `<jsp:redirect page="target.jsp" />`
* `<jsp:send page="target.jsp" />`

**Explanation:**
`<jsp:forward>` transfers control to another resource.

---

### ✅ **Question 16**

**Which SQL clause is used to combine rows from two or more tables, based on a related column between them?**

**Options:**

* MERGE
* ✅ JOIN
* COMBINE
* CONNECT

**Explanation:**
`JOIN` is used to combine rows from multiple tables.

---

### ✅ **Question 17**

**Which of these packages contain all the collection classes?**

**Options:**

* java.lang
* ✅ java.util
* java.net
* java.awt

**Explanation:**
Collections like `List`, `Map`, `Set` are in `java.util`.

---

### ✅ **Question 18**

**Which interface provides the basic methods for connecting with a database in JDBC?**

**Options:**

* ✅ Connection
* Statement
* ResultSet
* Com

**Explanation:**
`Connection` is used to create DB sessions and execute SQL.

---

### ✅ **Question 19**

**Which of the following is used to embed Java code in a JSP file?**

**Options:**

* ✅ `<% ... %>`
* `<%= ... %>`
* `<%@ ... %>`
* `<%: ... %>`

**Explanation:**
`<% ... %>` is a scriptlet tag to embed Java code.

---

### ✅ **Question 20**

**Which of the following is not a valid JSP implicit object?**

**Options:**

* request
* response
* out
* ✅ console

**Explanation:**
`console` is not part of JSP’s built-in objects.

---

### ✅ **Question 21**

**Which of the following is/are true about the `add` method in ArrayList?**

**Options:**

* It adds an element at the end of the list.
* It adds an element at the specified index.
* Both are incorrect
* ✅ Both are correct

**Explanation:**
`add(E)` adds at end; `add(index, E)` adds at index.

---

### ✅ **Question 22**

**What is the output of the following code?**

```java
try {
    int a = 10/0;
} catch (ArithmeticException e) {
    System.out.println("Arithmetic Exception");
} finally { }

System.out.println("Finally block executed");
```

**Options:**

* Arithmetic Exception Finally block executed
* ✅ Arithmetic Exception
* Finally block executed
* Runtime Error

**Explanation:**
`System.out.println(...)` after finally is unreachable due to exception.

---

### ✅ **Question 23**

**Which of the following is an example of runtime polymorphism in Java?**

**Options:**

* Method Merging
* ✅ Method Overriding
* Method Testing
* Method Locking

**Explanation:**
Overriding allows a subclass to provide specific implementation at runtime.

---

### ✅ **Question 24**

**Which of the following is true about constructors in inheritance?**

**Options:**

* A subclass can call a superclass constructor using `super()`.
* Constructors are not inherited in Java.
* ✅ Both are correct.
* Both are incorrect.

**Explanation:**
Constructors are not inherited but `super()` allows superclass constructor call.

---

### ✅ **Question 25**

**What is the correct order of steps to connect to a database using JDBC?**

**Options:**

* Load driver, create statement, create connection, execute query
* ✅ Load driver, create connection, create statement, execute query
* Create connection, load driver, create statement, execute query
* Create statement, load driver, create connection, execute query

**Explanation:**
Always load driver, then open connection, then create statement.

---

### ✅ **Question 26**

**Which method is used to execute a select query in JDBC?**

**Options:**

* executeQ()
* ✅ executeQuery()
* executeSQL()
* executeSelect()

**Explanation:**
`executeQuery()` is used for SELECT queries that return a ResultSet.

---

### ✅ **Question 27**

**What is the default HTTP response status code sent by a servlet if an exception is not caught?**

**Options:**

* 200 OK
* 404 Not Found
* ✅ 500 Internal Server Error
* 403 Forbidden

**Explanation:**
Unhandled exceptions result in 500 error.

---

### ✅ **Question 28**

**Which part of the subquery will be executed first?**

**Options:**

* ✅ Inner most query will be executed first
* Second level query will be executed
* Outer most query will be executed
* None of them

**Explanation:**
SQL evaluates inner subqueries before outer ones.

---

### ✅ **Question 29**

**What is the correct order of the JSP lifecycle methods?**

**Options:**

* ✅ jspInit(), \_jspService(), jspDestroy()
* jspStart(), \_jspService(), jspEnd()
* init(), service(), destroy()
* start(), service(), end()

**Explanation:**
This is the correct JSP lifecycle method order.

---

### ✅ **Question 30**

**Which JDBC driver type is known as the "pure Java" driver?**

**Options:**

* Type-1
* Type-2
* Type-3
* ✅ Type-4

**Explanation:**
Type-4 JDBC driver is 100% Java and platform independent.




### ✅ **Important Topics to Prepare**

#### 🔹 **Java Programming**

1. **OOP Concepts**

   * Inheritance, Polymorphism (compile-time & runtime), Abstraction, Encapsulation

2. **Method Overriding vs Method Overloading**

   * Key differences, real-time examples

3. **Exception Handling in Java**

   * `try-catch-finally`, multiple catch blocks, checked vs unchecked exceptions

4. **Constructors and `super()` keyword**

   * Constructor chaining, inheritance with constructors

5. **Interfaces and Abstract Classes**

   * When to use interface vs abstract class

6. **Access Modifiers**

   * `private`, `public`, `protected`, default — their scope and behavior in inheritance

7. **Collections Framework**

   * List, Set, Map, and their implementations like `ArrayList`, `HashMap`, `HashSet`

8. **ArrayList vs LinkedList**

   * Use cases, performance, internal structure

9. **Wrapper Classes & Autoboxing/Unboxing**

10. **Static and Final Keywords**

    * Static variables/methods, final variables/classes/methods

---

#### 🔹 **JSP & Servlets**

11. **Servlet Lifecycle**

    * `init()`, `service()`, `destroy()` with flow

12. **HttpServletRequest & HttpServletResponse methods**

    * `getParameter()`, `sendRedirect()`, `forward()`, `include()`

13. **JSP Implicit Objects**

    * `request`, `response`, `session`, `application`, `out`, `config`, etc.

14. **JSP Directives vs JSP Action Tags**

    * `<%@ include %>` vs `<jsp:include>`
    * `<jsp:forward>`, `<jsp:useBean>`

15. **Session Management in JSP/Servlet**

    * Cookies, URL Rewriting, HttpSession, Hidden fields

---

#### 🔹 **JDBC (Java Database Connectivity)**

16. **JDBC Architecture**

    * 4 types of JDBC drivers

17. **JDBC Steps**

    * Load driver, get connection, create statement, execute query, process result, close connection

18. **JDBC Statement Types**

    * `Statement`, `PreparedStatement`, `CallableStatement`

19. **ResultSet Navigation**

    * Iterating over ResultSet and fetching values using `getString()`, `getInt()`, etc.

20. **executeQuery() vs executeUpdate() vs execute()**

    * When to use each method

---

#### 🔹 **SQL (Structured Query Language)**

21. **DDL vs DML vs DCL Commands**

    * `CREATE`, `DROP`, `ALTER` vs `INSERT`, `UPDATE`, `DELETE`

22. **GROUP BY vs ORDER BY**

    * Use cases with `COUNT`, `SUM`, etc.

23. **HAVING vs WHERE clause**

    * Filtering aggregated vs non-aggregated results

24. **JOINS**

    * INNER JOIN, LEFT JOIN, RIGHT JOIN, FULL OUTER JOIN with examples

25. **Subqueries and Nested Queries**

    * Scalar, Correlated, and Non-Correlated subqueries

26. **SQL Functions**

    * Aggregate (`COUNT`, `AVG`, `MIN`, `MAX`)
    * String (`UPPER`, `LOWER`, `SUBSTR`, `CONCAT`)
    * Date functions (`NOW()`, `DATE()`, `YEAR()`)

27. **Normalization**

    * 1NF, 2NF, 3NF — purpose and examples

28. **Constraints in SQL**

    * PRIMARY KEY, FOREIGN KEY, UNIQUE, NOT NULL, CHECK, DEFAULT

29. **Indexes**

    * Purpose, types (clustered, non-clustered)

30. **Transactions and ACID Properties**

    * COMMIT, ROLLBACK, SAVEPOINT

---
