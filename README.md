# Employee Management System
## OverviewThe Employee Management System is a JavaFX-based desktop application designed for administrators to manage employee data and automate salary calculations. The system provides an intuitive interface for performing CRUD operations, visualizing data, and calculating salaries based on employee types (permanent, part-time, contractor).
## Features
- Authentication: Secure login for administrators.- Employee Management:
  - Add, edit, delete, and view employee records.  - Manage employee details such as name, position, contact info, and more.
- Salary Calculation:  - Calculate salaries based on employee types:
    - Permanent: Fixed monthly salary.    - Part-time: Hourly rate multiplied by hours worked.
    - Contractor: Hourly rate within a capped limit.- Data Visualization:
  - View employee distribution by position using graphical representations.
## Technologies Used- Programming Language: Java
- GUI Framework: JavaFX- Database: PostgreSQL
- Architecture: MVC (Model-View-Controller)
## Installation and Setup### Prerequisites
- Java Development Kit (JDK) 11 or higher- PostgreSQL installed and running
- IDE (e.g., IntelliJ IDEA, Eclipse) for running the project
### Database Setup1. Create a database named employee in PostgreSQL.
2. Run the following SQL script to create the employee table:      CREATE TABLE employee (       id SERIAL PRIMARY KEY,
       name VARCHAR(100),       position VARCHAR(50),
       salary DECIMAL(10, 2),       hire_date DATE,
       employment_type VARCHAR(20),
       phone_number VARCHAR(15),       email VARCHAR(100),
       gender VARCHAR(10)   );
   ```3. Update the connection credentials in the application to match your PostgreSQL setup:
   - URL: `jdbc:postgresql://localhost:5432/employee`   - User: `postgres`
   - Password: `your_password`
### Running the Application1. Clone the repository:
   ```bash   git clone https://github.com/yourusername/EmployeeManagementSystem.git
   
2. Open the project in your IDE.3. Build the project to resolve dependencies.
4. Run the Main class to start the application.
## Usage1. Login:
   - Enter the administrator username and password to access the dashboard.2. Manage Employees:
   - Navigate to the "Employee Management" section to add, edit, or remove employee records.3. Calculate Salaries:
   - Use the salary calculation feature to compute and display employee earnings based on their type.4. Visualize Data:
   - Access the "Statistics" tab to view graphical insights into employee distribution.
## Class Overview### Employee Class
- Models employee data with fields for name, position, salary, etc.
- Includes constructors, getters, and setters for data encapsulation.
### EmploymentType Enum- Defines employee types: Permanent, Part-Time, Contractor.
### EmployeeDAO Class
- Handles CRUD operations with the database using JDBC.
### HelloController Class- Manages user interactions and binds UI components to the backend logic.
## Screenshots
!Login Screen!Dashboard
## Testing
- Each method is tested with a minimum of three test cases.- Test cases include boundary conditions, edge cases, and normal scenarios.
## Roadmap
- Add role-based access for multiple user types.- Implement employee performance tracking.
- Integrate email notifications for updates.
## ContributionContributions are welcome! Please follow these steps:
1. Fork the repository.2. Create a new branch for your feature: git checkout -b feature-name.
3. Commit your changes: git commit -m 'Add new feature'.4. Push to your branch: git push origin feature-name.
5. Create a pull request.
## LicenseThis project is licensed under the MIT License. See the LICENSE file for details.
## Contact
For any inquiries or feedback, please contact [Your Name] at your_email@example.com.
