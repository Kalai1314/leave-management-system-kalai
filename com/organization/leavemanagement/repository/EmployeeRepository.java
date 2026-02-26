package com.organization.leavemanagement.repository;

import java.util.HashMap;
import java.util.Map;

import com.organization.leavemanagement.model.Employee;

public class EmployeeRepository {

    // O(1) Lookup using employeeId
    private Map<String, Employee> employeeMap = new HashMap<>();

    // Add Employee
    public void addEmployee(Employee employee) {
        employeeMap.put(employee.getEmployeeId(), employee);
    }

    // Find Employee by ID
    public Employee findById(String employeeId) {
        return employeeMap.get(employeeId);
    }
}