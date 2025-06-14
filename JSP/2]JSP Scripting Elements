
# ✅ 2. JSP Scripting Elements

JSP allows embedding Java code directly into the HTML using **scripting elements**. But in modern applications, we **minimize or avoid** them to make the code cleaner and easier to maintain.

Let’s understand:

---

## 🔢 Types of JSP Scripting Elements

| Element Type     | Syntax       | Purpose                               |
| ---------------- | ------------ | ------------------------------------- |
| **Declarations** | `<%! ... %>` | Declare variables and methods         |
| **Scriptlets**   | `<% ... %>`  | Embed Java code (logic/loops/if-else) |
| **Expressions**  | `<%= ... %>` | Output result of a Java expression    |

---

## 1️⃣ **JSP Declarations** — `<%! ... %>`

### ✅ What it does:

Used to **declare variables or methods** that will be part of the JSP’s translated Java class (like instance variables).

These are placed **outside** the service method.

### 🧠 When to use:

* When you want to create **helper methods**
* When you need a **global variable** across requests

### 🧪 Example:

```jsp
<%! int counter = 0;

    public String greet(String name) {
        return "Hello, " + name;
    }
%>

<p><%= greet("Tanmay") %></p>
```

### 🧾 Output:

```html
Hello, Tanmay
```

---

## 2️⃣ **JSP Scriptlets** — `<% ... %>`

### ✅ What it does:

Allows writing **Java code blocks** (like conditions, loops, try-catch) **inside the HTML**.

### ❌ Why discouraged in modern apps:

* Mixes **logic and presentation**
* Makes code hard to read and maintain
* Breaks MVC separation (view vs logic)

### 🧠 When it was used:

* Earlier JSP versions had no JSTL or EL
* Scriptlets were the only way to write logic

### 🧪 Example:

```jsp
<%
    int a = 10;
    int b = 20;
    int sum = a + b;
%>

<p>The sum is: <%= sum %></p>
```

### 🧾 Output:

```html
The sum is: 30
```

✅ **You can use Java if-else/for-loop here**, but it's **not recommended** today.

---

## 3️⃣ **JSP Expressions** — `<%= ... %>`

### ✅ What it does:

**Outputs** the value of a Java expression **directly into the HTML**.

* It’s like: `out.print(...)`
* Simple and concise for printing variables or method calls

### 🧠 When to use:

* When you want to **print a single value** (variable, method return, etc.)

### 🧪 Example:

```jsp
<%
    String name = "Tanmay";
%>

<h1>Hello, <%= name %>!</h1>
```

### 🧾 Output:

```html
Hello, Tanmay!
```

✅ Can be used **safely for displaying read-only data** (no logic here).

---

## 🧯 Why Are Scriptlets Discouraged?

Modern JSP uses **JSTL (JSP Standard Tag Library)** and **EL (Expression Language)** instead of scriptlets.

| Problem with Scriptlets   | Modern Solution                            |
| ------------------------- | ------------------------------------------ |
| Mixing Java logic in HTML | ✅ Use JSTL tags (`<c:if>`, `<c:forEach>`)  |
| Poor readability          | ✅ Use Expression Language (`${user.name}`) |
| Hard to test/debug        | ✅ Keep logic in Servlets or Beans          |
| Violates MVC structure    | ✅ Only display data in JSP                 |

---

## 🧪 Real Comparison Example:

### ❌ Old Way Using Scriptlets:

```jsp
<%
    if (user != null) {
%>
    <h1>Hello, <%= user.getName() %></h1>
<%
    } else {
%>
    <h1>Welcome, guest!</h1>
<%
    }
%>
```

### ✅ Modern Way Using JSTL & EL:

```jsp
<c:choose>
    <c:when test="${not empty user}">
        <h1>Hello, ${user.name}</h1>
    </c:when>
    <c:otherwise>
        <h1>Welcome, guest!</h1>
    </c:otherwise>
</c:choose>
```

🔎 **Much cleaner, readable, and maintainable!**

---

## 🧠 Summary Table

| Scripting Element | Syntax       | Use Case                        | Should Use? |
| ----------------- | ------------ | ------------------------------- | ----------- |
| Declaration       | `<%! ... %>` | Declare variables/methods       | ⚠️ Rarely   |
| Scriptlet         | `<% ... %>`  | Control flow, logic (if, loops) | ❌ Avoid     |
| Expression        | `<%= ... %>` | Output single value             | ✅ OK        |

---


