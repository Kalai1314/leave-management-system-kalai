package com.organization.leavemanagement.service;



import com.organization.leavemanagement.exception.*;
import com.organization.leavemanagement.model.*;
import com.organization.leavemanagement.repository.EmployeeRepository;
import com.organization.leavemanagement.util.ValidationUtil;

public class LeaveService {

    private EmployeeRepository repository;

    public LeaveService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void applyLeave(LeaveRequest request) {

        // Basic validations
        ValidationUtil.validateString(request.getEmployeeId(), "Employee ID cannot be blank");
        ValidationUtil.validateString(request.getReason(), "Reason cannot be blank");
        ValidationUtil.validatePositiveNumber(request.getNumberOfDays(), "Leave days must be greater than 0");

        Employee employee = repository.findById(request.getEmployeeId());

        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found");
        }

        // Check leave balance
        if (request.getNumberOfDays() > employee.getLeaveBalance()) {
            throw new InsufficientLeaveBalanceException("Insufficient leave balance");
        }

        // Sick leave rule
        if (request.getLeaveType() == LeaveType.SICK &&
                request.getNumberOfDays() > 5) {
            throw new InvalidLeaveRequestException("Maximum 5 consecutive sick leaves allowed");
        }

        // ===== ADVANCED RULE IMPLEMENTATION =====

        int year = request.getRequestDate().getYear();

        int leavesTakenThisYear = employee.getLeavesTakenInYear(year);

        if (leavesTakenThisYear + request.getNumberOfDays() > 20) {
            throw new InvalidLeaveRequestException(
                    "Cannot exceed 20 leave days in year " + year
            );
        }

        employee.applyLeave(request.getNumberOfDays(), year);
    }
}