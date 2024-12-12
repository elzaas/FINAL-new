package org.example.employeedb;

public class GenderCount {
    private String gender;
    private int count;

    // Constructor to initialize gender and count
    public GenderCount(String gender, int count) {
        this.gender = gender;
        this.count = count;
    }

    // Getter for gender
    public String getGender() {
        return gender;
    }

    // Getter for count
    public int getCount() {
        return count;
    }

    // Setter for gender
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Setter for count
    public void setCount(int count) {
        this.count = count;
    }

    // toString method for easier debugging and logging
    @Override
    public String toString() {
        return "Gender: " + gender + ", Count: " + count;
    }

    // Optional: Method to increment the gender count
    public void incrementCount() {
        this.count++;
    }

    // Optional: Method to decrement the gender count (in case of removal)
    public void decrementCount() {
        if (this.count > 0) {
            this.count--;
        }
    }
}
