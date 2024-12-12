package org.example.employeedb;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Employee {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty position;
    private final DoubleProperty salary;
    private final ObjectProperty<LocalDate> hireDate;
    private final ObjectProperty<EmploymentType> employmentType;
    private final StringProperty phoneNumber;
    private final StringProperty email;
    private final StringProperty gender;
    private final DoubleProperty calculatedSalary;  // Calculated Salary Property

    // Constructor 1
    public Employee(int id, String name, String position, double salary, LocalDate hireDate) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
        this.salary = new SimpleDoubleProperty(salary);
        this.hireDate = new SimpleObjectProperty<>(hireDate);
        this.employmentType = new SimpleObjectProperty<>(EmploymentType.FULL_TIME); // Default value
        this.phoneNumber = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.gender = new SimpleStringProperty("");
        this.calculatedSalary = new SimpleDoubleProperty(0.0); // Initialize calculated salary
    }

    // Constructor 2
    public Employee(int id, String name, String position, double salary, LocalDate hireDate, EmploymentType employmentType, String phoneNumber, String email, String gender) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
        this.salary = new SimpleDoubleProperty(salary);
        this.hireDate = new SimpleObjectProperty<>(hireDate);
        this.employmentType = new SimpleObjectProperty<>(employmentType);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.email = new SimpleStringProperty(email);
        this.gender = new SimpleStringProperty(gender);
        this.calculatedSalary = new SimpleDoubleProperty(0.0); // Initialize calculated salary
    }

    // Getters and setters for all properties
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPosition() {
        return position.get();
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public StringProperty positionProperty() {
        return position;
    }

    public double getSalary() {
        return salary.get();
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public LocalDate getHireDate() {
        return hireDate.get();
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate.set(hireDate);
    }

    public ObjectProperty<LocalDate> hireDateProperty() {
        return hireDate;
    }

    public EmploymentType getEmploymentType() {
        return employmentType.get();
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType.set(employmentType);
    }

    public ObjectProperty<EmploymentType> employmentTypeProperty() {
        return employmentType;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public double getCalculatedSalary() {
        return calculatedSalary.get();
    }

    public void setCalculatedSalary(double calculatedSalary) {
        this.calculatedSalary.set(calculatedSalary);
    }

    public DoubleProperty calculatedSalaryProperty() {
        return calculatedSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id.get() +
                ", name='" + name.get() + '\'' +
                ", position='" + position.get() + '\'' +
                ", salary=" + salary.get() +
                ", hireDate=" + hireDate.get() +
                ", employmentType=" + employmentType.get() +
                ", phoneNumber='" + phoneNumber.get() + '\'' +
                ", email='" + email.get() + '\'' +
                ", gender='" + gender.get() + '\'' +
                ", calculatedSalary=" + calculatedSalary.get() +
                '}';
    }
}