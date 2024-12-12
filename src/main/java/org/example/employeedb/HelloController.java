
package org.example.employeedb;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class HelloController {
    // FXML elements
    @FXML private TextField textFieldName;
    @FXML private TextField textFieldPosition;
    @FXML private TextField textFieldSalary;
    @FXML private DatePicker datePickerHireDate;
    @FXML private ComboBox<String> comboBoxEmploymentType;
    @FXML private TextField textFieldPhoneNumber;
    @FXML private TextField textFieldEmail;
    @FXML private ComboBox<String> comboBoxGender;
    @FXML private TextField textFieldSearch;
    @FXML private TableView<Employee> tableViewEmployees;
    @FXML private TableColumn<Employee, String> columnEmployeeName;
    @FXML private TableColumn<Employee, String> columnEmployeePosition;
    @FXML private TableColumn<Employee, Double> columnEmployeeSalary;
    @FXML private TableColumn<Employee, LocalDate> columnEmployeeHireDate;
    @FXML private TableColumn<Employee, EmploymentType> columnEmploymentType;
    @FXML private TableColumn<Employee, String> columnPhoneNumber;
    @FXML private TableColumn<Employee, String> columnEmail;
    @FXML private TableColumn<Employee, String> columnGender;
    @FXML private TableColumn<Employee, Double> columnCalculatedSalary;
    @FXML private Label labelMaleCount;
    @FXML private Label labelFemaleCount;

    private final EmployeeDAO employeeDAO;

    public HelloController() {
        this.employeeDAO = new EmployeeDAO();
    }

    @FXML
    private void initialize() {
        // Binding TableView columns to Employee properties
        columnEmployeeName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        columnEmployeePosition.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        columnEmployeeSalary.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());
        columnEmployeeHireDate.setCellValueFactory(cellData -> cellData.getValue().hireDateProperty());
        columnEmploymentType.setCellValueFactory(cellData -> cellData.getValue().employmentTypeProperty());
        columnPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        columnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        columnGender.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        columnCalculatedSalary.setCellValueFactory(cellData -> cellData.getValue().calculatedSalaryProperty().asObject());

        // Populating ComboBox with employment types and genders
        comboBoxEmploymentType.setItems(FXCollections.observableArrayList("FULL_TIME", "PART_TIME", "CONTRACTOR"));
        comboBoxGender.setItems(FXCollections.observableArrayList("Male", "Female"));

        // Listener to populate fields when an employee is selected from the TableView
        tableViewEmployees.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> populateFields(newValue));

        refreshTableView();
    }

    // Populate fields with selected employee data
    private void populateFields(Employee selectedEmployee) {
        if (selectedEmployee != null) {
            textFieldName.setText(selectedEmployee.getName());
            textFieldPosition.setText(selectedEmployee.getPosition());
            textFieldSalary.setText(String.valueOf(selectedEmployee.getSalary()));
            datePickerHireDate.setValue(selectedEmployee.getHireDate());
            textFieldPhoneNumber.setText(selectedEmployee.getPhoneNumber());
            textFieldEmail.setText(selectedEmployee.getEmail());
            comboBoxGender.setValue(selectedEmployee.getGender());
            comboBoxEmploymentType.setValue(selectedEmployee.getEmploymentType().toString());
        } else {
            clearFields();
        }
    }

    // Clear the form fields
    private void clearFields() {
        textFieldName.clear();
        textFieldPosition.clear();
        textFieldSalary.clear();
        datePickerHireDate.setValue(null);
        comboBoxEmploymentType.setValue(null);
        textFieldPhoneNumber.clear();
        textFieldEmail.clear();
        comboBoxGender.setValue(null);
    }

    // Handle adding a new employee
    @FXML
    private void handleAddEmployee() {
        String name = textFieldName.getText();
        String position = textFieldPosition.getText();
        String salaryText = textFieldSalary.getText();
        LocalDate hireDate = datePickerHireDate.getValue();
        String employmentTypeText = comboBoxEmploymentType.getValue();
        String phoneNumber = textFieldPhoneNumber.getText();
        String email = textFieldEmail.getText();
        String gender = comboBoxGender.getValue();

        if (name.isEmpty() || position.isEmpty() || salaryText.isEmpty() || hireDate == null || employmentTypeText == null || phoneNumber.isEmpty() || email.isEmpty() || gender == null) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        double salary;
        try {
            salary = Double.parseDouble(salaryText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid salary. Please enter a valid number.");
            return;
        }

        EmploymentType employmentType = EmploymentType.valueOf(employmentTypeText);
        int nextId = employeeDAO.getNextId();
        Employee employee = new Employee(nextId, name, position, salary, hireDate, employmentType, phoneNumber, email, gender);
        double calculatedSalary = calculateSalary(employee);
        employee.setCalculatedSalary(calculatedSalary);
        employeeDAO.createEmployee(employee);
        refreshTableView();
        clearFields();
        showAlert("Success", "Employee added successfully!");
    }

    // Handle updating selected employee
    @FXML
    private void handleUpdateEmployee() {
        Employee selectedEmployee = tableViewEmployees.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            showAlert("Error", "No employee selected.");
            return;
        }

        String name = textFieldName.getText();
        String position = textFieldPosition.getText();
        String salaryText = textFieldSalary.getText();
        LocalDate hireDate = datePickerHireDate.getValue();
        String employmentTypeText = comboBoxEmploymentType.getValue();
        String phoneNumber = textFieldPhoneNumber.getText();
        String email = textFieldEmail.getText();
        String gender = comboBoxGender.getValue();

        if (name.isEmpty() || position.isEmpty() || salaryText.isEmpty() || hireDate == null || employmentTypeText == null || phoneNumber.isEmpty() || email.isEmpty() || gender == null) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        double salary;
        try {
            salary = Double.parseDouble(salaryText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid salary. Please enter a valid number.");
            return;
        }

        selectedEmployee.setName(name);
        selectedEmployee.setPosition(position);
        selectedEmployee.setSalary(salary);
        selectedEmployee.setHireDate(hireDate);
        selectedEmployee.setEmploymentType(EmploymentType.valueOf(employmentTypeText));
        selectedEmployee.setPhoneNumber(phoneNumber);
        selectedEmployee.setEmail(email);
        selectedEmployee.setGender(gender);
        double calculatedSalary = calculateSalary(selectedEmployee);
        selectedEmployee.setCalculatedSalary(calculatedSalary);
        employeeDAO.updateEmployee(selectedEmployee);
        refreshTableView();
        clearFields();
        showAlert("Success", "Employee updated successfully!");


        // Update employee properties (as before)


        // Update the employee in the database
        employeeDAO.updateEmployee(selectedEmployee);

        // Clear the selection and refresh the TableView
        tableViewEmployees.getSelectionModel().clearSelection();
        refreshTableView();

        // Re-select the updated employee (optional)
        tableViewEmployees.getSelectionModel().select(selectedEmployee);

        clearFields();
        showAlert("Success", "Employee updated successfully!");
    }


    // Show an alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Update TableView
    private void refreshTableView() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        tableViewEmployees.getItems().setAll(employees);
        tableViewEmployees.refresh();
        handleShowGenderCount();  // Refresh gender counts on each data refresh
    }

    // Handle removing selected employee
    @FXML
    private void handleRemoveEmployee() {
        Employee selectedEmployee = tableViewEmployees.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            employeeDAO.deleteEmployee(selectedEmployee.getId());
            refreshTableView();
            showAlert("Success", "Employee removed successfully!");
        } else {
            showAlert("Error", "Please select an employee to remove.");
        }
    }

    // Search employees based on the search text
    @FXML
    private void handleSearch() {
        String searchText = textFieldSearch.getText();
        List<Employee> filteredEmployees = employeeDAO.getAllEmployees().stream()
                .filter(emp -> {
                    if (String.valueOf(emp.getId()).equals(searchText) || emp.getName().equalsIgnoreCase(searchText)) {
                        return true;
                    }

                    try {
                        double salaryFilter = Double.parseDouble(searchText);
                        return emp.getSalary() == salaryFilter;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
        tableViewEmployees.getItems().setAll(filteredEmployees);
    }

    // Show all employees
    @FXML
    private void handleShowAll() {
        refreshTableView();
    }

    // Delete all employees
    @FXML
    private void handleDeleteAll() {
        employeeDAO.deleteAllEmployees();
        refreshTableView();
        showAlert("Success", "All employees deleted.");
    }

    // Calculate salary based on employment type
    private double calculateSalary(Employee employee) {
        double baseSalary = employee.getSalary();
        switch (employee.getEmploymentType()) {
            case FULL_TIME:
                return baseSalary;
            case PART_TIME:
                return baseSalary * 0.5;
            case CONTRACTOR:
                return baseSalary * 1.2;
            default:
                return baseSalary;
        }
    }public void handleCalculateSalaries() {
        List<Employee> employees = tableViewEmployees.getItems();

        // Iterate over each employee and calculate their salary
        for (Employee employee : employees) {
            double calculatedSalary = calculateSalary(employee);
            employee.setCalculatedSalary(calculatedSalary);
        }

        // Refresh the TableView to reflect the updated salaries
        tableViewEmployees.refresh();
    }

    // Show gender count
    @FXML
    private void handleShowStats() {
        handleShowGenderCount();
        showAlert("Stats", "Gender statistics updated.");
    }

    // Show gender count
    private void handleShowGenderCount() {
        List<GenderCount> genderCounts = employeeDAO.getGenderCount();
        int maleCount = 0, femaleCount = 0;

        for (GenderCount genderCount : genderCounts) {
            if (genderCount.getGender().equals("Male")) {
                maleCount = genderCount.getCount();
            } else if (genderCount.getGender().equals("Female")) {
                femaleCount = genderCount.getCount();
            }
        }

        labelMaleCount.setText("Male: " + maleCount);
        labelFemaleCount.setText("Female: " + femaleCount);
    }

}