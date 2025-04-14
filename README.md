# 📋 TaskTracker – Desktop App for Managing Tasks and Employees

**TaskTracker** is a Java-based desktop application designed for managing tasks, jobs, and employees in a company environment. It provides a clean and structured user experience through a modular **MVC architecture**, a **Swing GUI**, and a **multi-threaded socket server** for real-time communication between client and server. The app ensures data persistence with a **MySQL** backend and allows both managers and employees to interact with the system based on their roles.

---

## ✨ Features

- 🔐 **Login system** with role-based access (Manager / Employee)
- 👤 **Employee management** (create, update, delete, view)
- 📂 **Job/project tracking** (create jobs, assign employees, monitor progress)
- ✅ **Task assignment & real-time status tracking**
- 🔍 **Search functionality** across all entities (jobs, tasks, employees)
- 🧠 **Socket communication** using custom request/response operations
- 🎯 Role-specific GUI navigation (separate interfaces for managers and employees)
- 🧩 Modular and reusable Swing components (JFrames, JPanels, JDialogs)
- 💾 Reliable data persistence with **MySQL database**

---

## 🧱 Architecture Highlights

- 📦 **Modular three-layer architecture**
  - **Client**: Handles GUI, user interaction, and request creation
  - **Server**: Handles socket threads, system operations, and DB access
  - **Shared (Zajednički)**: Domain models and communication protocol (Request/Response)
- 🧠 **System Operation Pattern** via `AbstractSO` and dedicated SO classes for all core entities (Zaposleni, Posao, Zadatak)
- 🔁 **Multi-threaded Server**: Handles multiple client connections concurrently via `ThreadClient`
- 📨 **Command-based communication**: Clients send requests with an operation code (`Operation.java`), server returns structured responses
- 🔄 **Domain-driven design**: Each entity is modeled as an object with full CRUD functionality
- 🧪 Easy expansion and maintainability through clear separation of concerns

---

## 💡 Technologies Used

- **Java SE 17**
- **Swing** (for GUI)
- **MySQL** (via XAMPP for local hosting)
- **SQLyog** (for DB visualization and management)
- **JDBC** (for database connectivity)
- **Socket programming** (for real-time client-server communication)
- **MVC Pattern** (separation of data, logic, and view)

---

