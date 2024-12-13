# Employee Management System

The Employee Management System (EMS) is a JavaFX-based application designed to streamline employee data management and automate payroll processes. It empowers administrators with CRUD functionality to manage comprehensive employee details, including employment type and contact information. EMS ensures accurate salary calculations for full-time, part-time, and contractor roles, integrating PostgreSQL for secure, scalable database management. Equipped with authentication for secure access, the system features data visualization for insights into employee distribution. Built using the MVC pattern, EMS emphasizes user-friendly interfaces, operational efficiency, and robust error handling, ensuring seamless management for organizations handling up to 1000 employees.






## Team Members List
 - Dzhumabekova Aiturgan IEMIT-23
 - Asanova Elza IEMIT-23


  # Roles of Group Members:


              
Dzhumabekova Aiturgan:
- Testing and debugging.
- UI/UX design and integration using JavaFX.
- Presentation
- Technical assignment

Asanova Elza :
 - Implementation of backend logic (data connections, queries).
  - Implement classes and their functions
  - Database setup and schema design.
     - Data connection.
   


# Requirements

- Connection with Database:(PostgresSQL)
The system should be connected to a database to store employee details such as ID, name, position, department, and other relevant information. The application must allow querying and updating the employee data from the database efficiently.

- JavaFX Design(Scene Builder):
The system will be developed using JavaFX for the user interface. The design should be modern, user-friendly, and responsive, ensuring smooth interaction for admins and employees.

- Creating Buttons:
The application should have clearly labeled buttons for various functionalities:

    - View Employees Button: Displays a list of all employees along with their details.
    - Add Employee Button: Allows admins to add a new employee to the system by entering their details such as name, ID, and position.
    - Update Employee Button: Enables admins to update existing employee details.
   - Delete Employee Button: Allows admins to remove an employee record from the system.
   - 
     - View Employees Button:
          -This button will show a comprehensive list of all employees, their roles, departments, and other essential information.


- Employee Information Display:
   - The application should allow admins to view detailed information about each employee, including their job history, contact details, and current status within the company.


    - Create Visually Appealing Application:
         

     - Reports and Analytics:
          The system should provide options to generate reports on employee phone number, hire date, and other metrics that are important for tracking and managing employees effectively.

## Purpose and Goals of Creation
The Employee Management System is a JavaFX-based application designed for administrators to efficiently manage employee data and automate salary calculations. The system aims to:

- Automate employee management processes.
- Simplify salary calculations for employees of different categories.
- Ensure data protection through authentication.
- Provide a user-friendly interface for viewing statistics and working with data.

## Key Features
### Employee Data Management
- Add, edit, delete, and view employee details (CRUD operations).
- Manage attributes like name, position, phone number, email, and employment type.

### Salary Automation
- Calculate salaries based on employment type:
  - **Permanent employees**: Fixed salary.
  - **Part-time employees**: Hourly rate × number of hours worked.
  - **Contractors**: Hourly rate with a cap.

### Data Visualization
- Display employee distribution graphs by positions.
- Dashboard to show statistics like total employees, active/inactive employees.

### Authentication
- Secure administrator login with username and password.


![image](https://github.com/user-attachments/assets/f0e15719-e569-45f9-8003-77a83cd5d6b0)

- Error messages for incorrect login attempts.


![image](https://github.com/user-attachments/assets/a6bbefc9-ac1b-4af6-851f-096b008d9bce)













## IT Product Structure
### Modules
1. **Authentication**: Login system for secure access.

3. **Dashboard**:
   - Displays male, female, and salary employee counts.
4. **Employee Management**:
   - CRUD operations for managing employee data.
5. **Salary Calculation**:
   - Automated salary computation based on employee type.
6. **Data Visualization**:
   - Graphs for visual representation of employee data.

### Glossary
- **CRUD**: Create, Read, Update, Delete — basic data operations.
- **JavaFX**: Framework for developing graphical user interfaces.
- **PostgreSQL**: Relational database for storing employee data.
- **MVC**: Model-View-Controller pattern for separating logic and UI.

## Functional Requirements
- Administrator login with secure authentication.
- CRUD operations for managing employee details.
- Automated salary calculation.
- Graphical data visualization.
- Error notifications for invalid input.
- User-friendly buttons for actions like Add, Edit, Delete, and Clear.

## Technical Requirements
- Operations should complete within 2 seconds.
- Support up to 1000 records in the database.
- Secure storage of employee data using PostgreSQL.
- GUI built using JavaFX Scene Builder.

## Interface Specification
### Authentication Screen
- Fields: Username, Password
- Button: Login








![image](https://github.com/user-attachments/assets/872e810b-0ca3-4ac9-ba95-97e144f65b0a)



### Employee Management Screen
- Table: Displays employee details (ID, name, position, salary, etc.).
- Fields: For entering employee data.
- Buttons: Add, Edit, Delete, Clear.





![image](https://github.com/user-attachments/assets/3851d983-ca66-43c6-99d2-24265672acb3)






## Custom Scenarios
1. **Administrator Authorization**:
   - Precondition: Correct login credentials.
   - Main Flow: Access dashboard after successful login.
   - Alternate Flow: Error message for incorrect login.
  


![image](https://github.com/user-attachments/assets/f0e15719-e569-45f9-8003-77a83cd5d6b0)






2. **Adding a New Employee**:
   - Precondition: Navigate to Employee Management.
   - Main Flow: Fill out fields, click "Add", and save to the database.
   - Postcondition: New employee appears in the table.
  



     ![image](https://github.com/user-attachments/assets/72a89dc2-7f50-475e-ad6a-cda6f26e8888)






     ![image](https://github.com/user-attachments/assets/7fcb5c77-edb1-40d9-8b09-63f35df4141c)






3. **Deleting an Employee**:
   - Precondition: Select a record in the table.
   - Main Flow: Click "Delete" to remove the record.
   - Postcondition: Employee is removed from the table.
  
   - # Remove only one Employee
  
   - 
  
       ![image](https://github.com/user-attachments/assets/9f0b3cfa-0f2a-42df-84fa-302986ef2849)
  



     
  
     ![image](https://github.com/user-attachments/assets/944174f5-bf70-4b3b-a583-06e6c5b163b8)


     # Delete All
  
 

     ![image](https://github.com/user-attachments/assets/019b9575-b698-4631-954b-85915d6d2837)





     # Update


     ![image](https://github.com/user-attachments/assets/dc1a72d9-c908-433d-b0d6-efed6edc3c30)




3. **Calculating Salaries**:
   - Precondition: Employee has an assigned type.
   - Main Flow: System calculates salary based on type.
   - Postcondition: Display calculated salary.
  



![image](https://github.com/user-attachments/assets/41f196f5-6c21-4a3b-9888-913dff1c46e8)


## Database Connection
- **Database**: PostgreSQL database named `employee`.
- **Tables**:
  - `employee`: Fields include ID, name, position, salary, hire_date, employment_type, phone_number, email, and gender.
- **Connection**:
  - URL: `jdbc:postgresql://localhost:5432/employee`
  - User: `postgres`
  - Password: `1359`
- **Operations**:
  - Insert: Add new employees.
  - Update: Modify existing data.
  - Delete: Remove records.
  - Select: Retrieve data.

## Code Overview
### Employee Class
- **Package**: `org.example.employeedb`
- **Attributes**: ID, name, position, salary, hire_date, employment_type, phone_number, email, gender.
- **Constructors**: Default and parameterized.
- **Methods**:
  - Getters/Setters for attributes.
  - Property methods for JavaFX bindings.
  - `toString()` for a formatted string representation.

### EmployeeType Enum
- Defines employment types (FULL_TIME, PART_TIME, CONTRACTOR).

### EmployeeDAO Class
- **Package**: `org.example.employeedb`
- **Functions**:
  - `createEmployee()`: Adds new employee.
  - `getAllEmployees()`: Fetches all records.
  - `updateEmployee()`: Updates an employee.
  - `deleteEmployee(int id)`: Deletes an employee by ID.
  - `deleteAllEmployees()`: Deletes all records.

### HelloController Class
- **Responsibilities**:
  - Manage UI and interactions.
  - Handle CRUD operations via `EmployeeDAO`.
  - Calculate salaries based on employment type.
  - Populate UI components and bind JavaFX properties.

## Security Considerations
- Passwords are hashed before storage.
- Credentials externalized to configuration files.
- Input validation to prevent SQL injection.

## Dependencies
- **Java Libraries**:
  - `java.sql`: Database operations.
  - `java.util`: Collections and utility classes.
  - `java.time.LocalDate`: Date handling.
  - `java.util.logging`: Logging events.
- **PostgreSQL**: Version 13+.
- **JavaFX**: UI components.

## UML Diagram

![image](<img width="1211" alt="Снимок экрана 2024-12-13 в 19 10 58" src="https://github.com/user-attachments/assets/3f5fdf53-0641-40b2-999c-d0fe52ae6c33" />)


## Presentation

[Click here to view the file](https://drive.google.com/file/d/1XNgr-GRWakFi8vemxcetupmaM5Mo03MN/view?usp=sharing)





### Technical Specification
[Click here to view the technical specification](https://docs.google.com/document/d/1tl3I-XU3Ce-mcSsweaR0Idb4B1ERwnHk/edit?usp=sharing&ouid=100922547310916559233&rtpof=true&sd=true)






### Video
[Click here to watch the video](https://drive.google.com/file/d/1EyIBnGSZMs8DrCs2MlYuamvgLLWla03Y/view?usp=sharing)


