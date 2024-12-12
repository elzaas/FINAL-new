package org.example.employeedb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;

public class EmployeeDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/employee";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1359";
    private static final Logger LOGGER = Logger.getLogger(EmployeeDAO.class.getName());

    // Establish a connection to the database
    private Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            LOGGER.info("Connected to Database.");
            return connection;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection failed", e);
            throw e;
        }
    }

    // Get the next available ID for a new employee
    public int getNextId() {
        String sql = "SELECT COALESCE(MAX(id), 0) FROM employee";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting next ID", e);
        }
        return 1; // Return 1 if no records are found
    }

    // Create a new employee record in the database
    public boolean createEmployee(Employee employee) {
        String sql = "INSERT INTO employee (id, name, position, salary, hire_date, employment_type, phone_number, email, gender,calculateSalary) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getPosition());
            pstmt.setDouble(4, employee.getSalary());
            pstmt.setDate(5, Date.valueOf(employee.getHireDate()));
            pstmt.setString(6, employee.getEmploymentType().name());
            pstmt.setString(7, employee.getPhoneNumber());
            pstmt.setString(8, employee.getEmail());
            pstmt.setString(9, employee.getGender());
            pstmt.setDouble(10, employee.getCalculatedSalary());
            pstmt.executeUpdate();
            return true; // Return true on success
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating employee", e);
        }
        return false; // Return false on failure
    }

    // Fetch an employee by their ID
    public Employee getEmployeeById(int id) {
        LOGGER.info("Fetching Employee with ID: " + id);
        String sql = "SELECT * FROM employee WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToEmployee(rs);
                } else {
                    LOGGER.warning("No employee found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching employee by ID", e);
        }
        return null; // Return null if not found
    }

    // Fetch all employees from the database
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(mapToEmployee(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching all employees", e);
        }
        return employees;
    }

    // Update an existing employee record in the database
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET name = ?, position = ?, salary = ?, hire_date = ?, employment_type = ?, phone_number = ?, email = ?, gender = ?, calculateSalary = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getPosition());
            pstmt.setDouble(3, employee.getSalary());
            pstmt.setDate(4, Date.valueOf(employee.getHireDate()));
            pstmt.setString(5, employee.getEmploymentType().name());
            pstmt.setString(6, employee.getPhoneNumber());
            pstmt.setString(7, employee.getEmail());
            pstmt.setString(8, employee.getGender());
            pstmt.setDouble(9, employee.getCalculatedSalary());
            pstmt.setInt(10, employee.getId()); // Set the ID at the end

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was updated
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating employee", e);
        }
        return false; // Return false on failure
    }

    // Delete an employee by their ID
    public boolean deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            LOGGER.info("Employee with ID " + id + " deleted from the database.");
            return true; // Return true on success
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting employee", e);
        }
        return false; // Return false on failure
    }

    // Delete all employees from the database
    public boolean deleteAllEmployees() {
        String sql = "DELETE FROM employee";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            LOGGER.info("All employees deleted from the database.");
            return true; // Return true on success
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting all employees", e);
        }
        return false; // Return false on failure
    }

    // Fetch gender count (male and female)
    public List<GenderCount> getGenderCount() {
        String sql = "SELECT gender, COUNT(*) AS count FROM employee GROUP BY gender";
        List<GenderCount> genderCounts = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String gender = rs.getString("gender");
                int count = rs.getInt("count");
                genderCounts.add(new GenderCount(gender, count));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting gender count", e);
        }

        return genderCounts;
    }

    // Helper method to map ResultSet to Employee object
    private Employee mapToEmployee(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("position"),
                rs.getDouble("salary"),
                rs.getDate("hire_date").toLocalDate(),
                EmploymentType.valueOf(rs.getString("employment_type")),
                rs.getString("phone_number"),
                rs.getString("email"),
                rs.getString("gender")
        );
    }
    public void updateGenderCount() {
        String sqlMerge = "INSERT INTO gender_count (gender, count) " +
                "VALUES (?, ?) " +
                "ON CONFLICT (gender) DO UPDATE " +
                "SET count = EXCLUDED.count";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlMerge)) {
            pstmt.setString(1, "Male");
            pstmt.setInt(2, getGenderCount("Male"));
            pstmt.executeUpdate();

            pstmt.setString(1, "Female");
            pstmt.setInt(2, getGenderCount("Female"));
            pstmt.executeUpdate();

            System.out.println("Gender count updated.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating gender count.");
        }
    }

    private int getGenderCount(String gender) {
        String sql = "SELECT COUNT(*) FROM employee WHERE gender = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, gender);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void updateSalaryDistribution() {
        String sqlMerge = "INSERT INTO salary_distribution (salary_range, count) " +
                "VALUES (?, ?) " +
                "ON CONFLICT (salary_range) DO UPDATE " +
                "SET count = EXCLUDED.count";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlMerge)) {
            pstmt.setString(1, "0-29999");
            pstmt.setInt(2, getSalaryRangeCount(0, 29999));
            pstmt.executeUpdate();

            pstmt.setString(1, "30000-60000");
            pstmt.setInt(2, getSalaryRangeCount(30000, 60000));
            pstmt.executeUpdate();

            pstmt.setString(1, "60001+");
            pstmt.setInt(2, getSalaryRangeCount(60001, Integer.MAX_VALUE));
            pstmt.executeUpdate();

            System.out.println("Salary distribution updated.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating salary distribution.");
        }
    }

    private int getSalaryRangeCount(int minSalary, int maxSalary) {
        String sql = "SELECT COUNT(*) FROM employee WHERE salary BETWEEN ? AND ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, minSalary);
            pstmt.setInt(2, maxSalary);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }




}
