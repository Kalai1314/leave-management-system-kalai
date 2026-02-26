package com.organization.leavemanagement.model;

import java.util.HashMap;
import java.util.Map;

public class Employee {

    private String employeeId;
    private String name;
    private int leaveBalance;

    // Track yearly leave usage
    private Map<Integer, Integer> yearlyLeaveUsage;

    public Employee(String employeeId, String name, int leaveBalance) {
        this.employeeId = employeeId;
        this.name = name;
        this.leaveBalance = leaveBalance;
        this.yearlyLeaveUsage = new HashMap<>();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public int getLeavesTakenInYear(int year) {
        return yearlyLeaveUsage.getOrDefault(year, 0);
    }

    public void applyLeave(int days, int year) {
        // Deduct balance
        this.leaveBalance -= days;

        // Update yearly usage
        int currentUsage = yearlyLeaveUsage.getOrDefault(year, 0);
        yearlyLeaveUsage.put(year, currentUsage + days);
    }
}