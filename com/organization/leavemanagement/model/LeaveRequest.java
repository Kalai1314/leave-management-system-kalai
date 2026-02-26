package com.organization.leavemanagement.model;

import java.time.LocalDate;

public class LeaveRequest {

    private String employeeId;
    private LeaveType leaveType;
    private int numberOfDays;
    private String reason;
    private LocalDate requestDate;

    public LeaveRequest(String employeeId, LeaveType leaveType,
                        int numberOfDays, String reason, LocalDate requestDate) {
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.numberOfDays = numberOfDays;
        this.reason = reason;
        this.requestDate = requestDate;
    }

    public String getEmployeeId() { return employeeId; }
    public LeaveType getLeaveType() { return leaveType; }
    public int getNumberOfDays() { return numberOfDays; }
    public String getReason() { return reason; }
    public LocalDate getRequestDate() { return requestDate; }
}