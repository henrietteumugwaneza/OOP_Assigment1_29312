
package com.auca.teacher;

import com.auca.abstracts.AcademicMember;
import com.auca.department.Department;
import com.auca.exceptions.ValidationException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Teacher extends AcademicMember {

    private String employeeNumber;
    private String specialization;
    private LocalDate hireDate;
    private BigDecimal salary;
    private String officeNumber;
    private String employmentType;

    public Teacher(
            String personId,
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            LocalDate dateOfBirth,
            String memberNumber,
            Department department,
            LocalDate joinDate,
            String academicStatus,
            String institutionalEmail,
            String advisorName,
            String employeeNumber,
            String specialization,
            LocalDate hireDate,
            BigDecimal salary,
            String officeNumber,
            String employmentType) {

        super(personId, firstName, lastName, email, phoneNumber,
                dateOfBirth, memberNumber, department, joinDate,
                academicStatus, institutionalEmail, advisorName);

        this.employeeNumber = employeeNumber;
        this.specialization = specialization;
        this.hireDate = hireDate;
        this.salary = salary;
        this.officeNumber = officeNumber;
        this.employmentType = employmentType;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    @Override
    public String getId() {
        return employeeNumber;
    }

    @Override
    public boolean isActive() {
        return getAcademicStatus() != null
                && getAcademicStatus().equalsIgnoreCase("ACTIVE");
    }

    @Override
    public String getRoleDescription() {
        return "Teacher";
    }

    @Override
    public String getAcademicCategory() {
        return "TEACHER";
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();

        if (employeeNumber == null || employeeNumber.isBlank()) {
            throw new ValidationException("Employee number is required.");
        }

        if (specialization == null || specialization.isBlank()) {
            throw new ValidationException("Specialization is required.");
        }

        if (hireDate == null || hireDate.isAfter(LocalDate.now())) {
            throw new ValidationException("Hire date cannot be in the future.");
        }

        if (salary == null || salary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Salary must be greater than zero.");
        }

        if (officeNumber == null || officeNumber.isBlank()) {
            throw new ValidationException("Office number is required.");
        }

        if (employmentType == null || employmentType.isBlank()) {
            throw new ValidationException("Employment type is required.");
        }
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Teacher: " + getDisplayName()
                + " | Employee Number: " + employeeNumber
                + " | Specialization: " + specialization
                + " | Department: " + getDepartment().getDepartmentName();
    }

    @Override
    public String toString() {
        return "Teacher {" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", name='" + getDisplayName() + '\'' +
                ", specialization='" + specialization + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", officeNumber='" + officeNumber + '\'' +
                ", employmentType='" + employmentType + '\'' +
                ", department=" + getDepartment().getDepartmentName() +
                '}';
    }
}
