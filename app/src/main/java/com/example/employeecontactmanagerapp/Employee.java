package com.example.employeecontactmanagerapp;

public class Employee {
    public final String name;
    public final String empId;
    public final String phone;
    public final String dept;

    public Employee(String name, String empId, String phone, String dept) {
        this.name = name;
        this.empId = empId;
        this.phone = phone;
        this.dept = dept;
    }
}