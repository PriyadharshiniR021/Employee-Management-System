**Project Report: Employee Management GUI Application Using Java and MySQL**

---

**1. Problem Statement**

In small and medium-sized enterprises, managing employee data through manual systems or spreadsheets often leads to inefficiencies, data loss, redundancy, and difficulty in tracking updates. There is a lack of a user-friendly, platform-independent system for securely managing employee information. This project addresses the need for a desktop-based application that ensures secure access, accurate record handling, and ease of performing data operations like addition, updates, and deletions.

---

**2. Solution Statement**

To resolve these challenges, we developed a Java Swing-based GUI application integrated with a MySQL database. This software provides an intuitive interface that facilitates authenticated user login and comprehensive employee management through CRUD (Create, Read, Update, Delete) operations. It reduces dependency on manual work, enhances data integrity, and ensures consistency through direct database communication via JDBC.

---

**3. Key Features**

* Secure user login with predefined credentials
* Real-time display of employee data using a JTable
* Add new employee entries dynamically
* Modify existing employee records with immediate reflection
* Remove records using employee ID
* Immediate table refresh post any modification
* User-friendly interface using Java Swing
* Robust exception handling with user feedback dialogs

---

**4. Technical Specifications**

**System Architecture**

* **Presentation Layer:** Java Swing GUI (JFrame, JPanel, JTable)
* **Logic Layer:** Java code (ActionListeners, Validation, Input Handling)
* **Data Layer:** JDBC API for database interaction

**Database Schema**

Database Name: `function`
Table: `employee`

| Column Name | Data Type   | Description               |
| ----------- | ----------- | ------------------------- |
| cid         | INT         | Employee ID (Primary Key) |
| cname       | VARCHAR(50) | Name of the employee      |
| designation | VARCHAR(50) | Role/Title                |
| salary      | INT         | Monthly salary            |
| joindate    | DATE        | Date of joining           |

**Technology Stack**

* **Language:** Java
* **GUI Toolkit:** Swing
* **Database:** MySQL (Port: 3309)
* **Connector:** JDBC (MySQL JDBC Driver)

---

**5. Implementation Details**

**Login Functionality**

* Basic hardcoded authentication: `23bit021 / 2025`
* Valid credentials redirect to main GUI; else, error dialog

**Main Dashboard**

* Displays employee details in a scrollable JTable
* Interactive buttons to add, update, and delete employees

**Add Employee**

* Form with inputs for CID, Name, Designation, Salary, Join Date
* Validates and executes `INSERT INTO employee VALUES (...)`

**Update Employee**

* Accepts CID to identify the employee
* Allows updating all other fields
* SQL: `UPDATE employee SET ... WHERE cid=?`

**Delete Employee**

* Prompts user to input CID
* Performs `DELETE FROM employee WHERE cid=?`

---

**6. Backend Models and Logic**

* Uses `PreparedStatement` for secure, dynamic SQL queries
* Single connection point using JDBC to MySQL
* ActionListeners handle form submissions and button events
* Refreshes table after each database update

---

**7. Descriptions**

* Clean layout using Java Swing GridLayout, BorderLayout
* Modular structure for easy code readability and updates
* Minimal dependencies for portability

---

**8. Error Handling**

* SQL exceptions caught using try-catch blocks
* GUI feedback through `JOptionPane` messages
* Handles numeric format errors, empty fields, and invalid dates

---

**9. Future Enhancements**

* Implement secure database-based login authentication
* Add role-based access control (Admin, HR, Viewer)
* Include advanced filters and search options
* Enable report generation in Excel or PDF
* Add real-time validations (e.g., calendar picker, numeric-only input)
* Improve UI aesthetics with modern JavaFX or external libraries

---

**10. Setup Instructions**

1. Install Java JDK (version 8 or later)
2. Install and configure MySQL Server (use port 3309)
3. Create the `function` database and `employee` table
4. Insert sample employee records
5. Add MySQL JDBC connector (JAR) to classpath
6. Compile the Java program
7. Run the application

---

**11. Installation Steps**

```sql
CREATE DATABASE function;

USE function;

CREATE TABLE employee (
    cid INT PRIMARY KEY,
    cname VARCHAR(50),
    designation VARCHAR(50),
    salary INT,
    joindate DATE
);

INSERT INTO employee VALUES
(1, 'Ruthi', 'Team leader', 50000, '2015-10-02'),
(2, 'Sherin', 'Tester', 35000, '2017-10-02'),
(3, 'Priya', 'Developer', 47000, '2018-10-08'),
(5, 'Sandy', 'HR', 40000, '2017-10-08'),
(6, 'Murali', 'Cyber', 49000, '2016-10-09'),
(7, 'Suresh', 'Manager', 79000, '2015-10-09'),
(8, 'Dhanu', 'Tester', 37000, '2015-11-01'),
(9, 'Lohith', 'Developer', 49000, '2016-10-11'),
(10, 'Rahul', 'Assistent', 30000, '2018-09-09');
```

---

**12. Conclusion**

The Employee Management System provides a foundational yet robust solution for desktop-based data management. With key CRUD operations, real-time feedback, and an intuitive interface, it is ideal for students, academic projects, and organizations needing a lightweight employee tracking solution. Future updates can further scale the application with modern UI/UX and extended functionality.

**13. outputs**

![image alt](https://github.com/PriyadharshiniR021/Employee-Management-System/blob/27b3e22ab334344696c8c78078c6f052f8d92280/login.png)

![image alt](https://github.com/PriyadharshiniR021/Employee-Management-System/blob/7b8bb5e32bb30cda97799c73481b0bedd1dc5ac3/view%20employee%20table.png)

![image alt](https://github.com/PriyadharshiniR021/Employee-Management-System/blob/53a6304a9a74ad2ed7b7b44eae5d55de23932778/add%20employee.png)

![image alt](https://github.com/PriyadharshiniR021/Employee-Management-System/blob/b7dd3a3ef008e713cf023eb206ef03b53572f0e3/added%20successfully.png)

![image alt](https://github.com/PriyadharshiniR021/Employee-Management-System/blob/c61de43e260517c880bcd6ae8ddc9ad9b630bb13/display.png)

![image alt](https://github.com/PriyadharshiniR021/Employee-Management-System/blob/a616495bf54f512ee907e0651b121921eb74913b/deleting%20by%20id.png)

![image alt](https://github.com/PriyadharshiniR021/Employee-Management-System/blob/026ad4cc1f196f56e712ae62e1e996a42850f0b7/deleted%20successfully.png)

![image alt](https://github.com/PriyadharshiniR021/Employee-Management-System/blob/b4b0d066e1033bd76a37e969bf132a471aa2c2bd/display%20after%20delete.png)

