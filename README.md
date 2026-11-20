# Northstar HR — HR Management System

A Java 21 + Spring Boot web application for a fictional HR firm. It uses **in-memory dummy data only**. There is no MySQL, PostgreSQL, database server, or database setup.

## Features

- HR dashboard with employee, department and leave-request summaries
- Employee directory with search, add, edit and delete
- Attendance overview
- Leave management with approve/reject actions
- Payroll overview using sample monthly salaries (KES)
- Recruitment vacancy overview
- Responsive layout for desktop, tablet and mobile

## Requirements

1. Java Development Kit (JDK) 21
2. Visual Studio Code
3. VS Code Extension Pack for Java (recommended)
4. Maven (optional if you use the included Maven Wrapper; this starter package uses a system Maven installation)

Check Java and Maven:

```bash
java -version
mvn -version
```

## Run in VS Code

1. Extract `HR-Management-System.zip`.
2. Open the extracted `HR-Management-System` folder in VS Code.
3. Install the **Extension Pack for Java** if prompted.
4. Open the VS Code terminal: **Terminal → New Terminal**.
5. Run:

```bash
mvn spring-boot:run
```

6. Open your browser at:

```text
http://localhost:8080
```

You can also build a runnable JAR:

```bash
mvn clean package
java -jar target/hr-management-system-1.0.0.jar
```

## Demo data

The app starts with seven fictional employees, five departments, attendance entries, leave requests, sample salary amounts and recruitment vacancies. All records live in Java collections in memory.

**Important:** Changes made while the app is running (such as adding an employee or approving leave) are temporary. They disappear when the application stops or restarts. This is intentional for the dummy-data version.

## Project structure

```text
HR-Management-System/
├── .vscode/
│   ├── extensions.json
│   └── settings.json
├── src/
│   ├── main/
│   │   ├── java/com/hrfirm/hrms/
│   │   │   ├── HrManagementApplication.java
│   │   │   ├── controller/HrController.java
│   │   │   ├── model/
│   │   │   │   ├── AttendanceRecord.java
│   │   │   │   ├── Employee.java
│   │   │   │   └── LeaveRequest.java
│   │   │   └── service/HrDataService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/css/styles.css
│   │       └── templates/
│   └── test/
├── pom.xml
└── README.md
```

## Pages

- `/` — Dashboard
- `/employees` — Employee directory
- `/employees/new` — Add employee
- `/attendance` — Attendance
- `/leave` — Leave requests
- `/payroll` — Payroll overview
- `/recruitment` — Recruitment overview

## Notes and next steps

This is a functional starter/demo application, not a production HR platform. It does not include authentication, role-based access, persistent storage, statutory payroll calculations, payslip generation, live clock-in/out, file uploads or candidate application workflows. Before using real employee information, add authentication, authorization, secure persistent storage, audit logging, backups and applicable privacy/security controls.

The company, people, emails, phone numbers, attendance, leave and payroll values are fictional sample data.
