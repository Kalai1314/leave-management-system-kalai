package com.organization.leavemanagement.main;

import java.time.LocalDate;
import java.util.Scanner;

import com.organization.leavemanagement.exception.EmployeeNotFoundException;
import com.organization.leavemanagement.exception.InsufficientLeaveBalanceException;
import com.organization.leavemanagement.exception.InvalidLeaveRequestException;
import com.organization.leavemanagement.model.Employee;
import com.organization.leavemanagement.model.LeaveRequest;
import com.organization.leavemanagement.model.LeaveType;
import com.organization.leavemanagement.repository.EmployeeRepository;
import com.organization.leavemanagement.service.LeaveService;
import com.organization.leavemanagement.util.ValidationUtil;

public class LeaveManagementApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EmployeeRepository repository = new EmployeeRepository();
        LeaveService service = new LeaveService(repository);

        // ===== ADD EMPLOYEE AT RUNTIME =====
        System.out.println("Enter Employee ID:");
        String empId = scanner.nextLine();

        System.out.println("Enter Employee Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Leave Balance:");
        int balance = Integer.parseInt(scanner.nextLine());

        repository.addEmployee(new Employee(empId, name, balance));

        try {

            System.out.println("\n--- APPLY LEAVE ---");

            System.out.println("Enter Employee ID:");
            String leaveEmpId = scanner.nextLine();

            System.out.println("Enter Leave Type (CASUAL/SICK/EARNED):");
            String typeInput = scanner.nextLine();

            LeaveType leaveType = ValidationUtil.validateLeaveType(typeInput);

            System.out.println("Enter Number of Days:");
            int days = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter Reason:");
            String reason = scanner.nextLine();

            System.out.println("Enter Year (e.g., 2026):");
            int year = Integer.parseInt(scanner.nextLine());

            LeaveRequest request = new LeaveRequest(
                    leaveEmpId,
                    leaveType,
                    days,
                    reason,
                    LocalDate.of(year, 1, 1)   // Using year input
            );

            service.applyLeave(request);

            System.out.println("Leave applied successfully.");

        } catch (EmployeeNotFoundException |
                 InvalidLeaveRequestException |
                 InsufficientLeaveBalanceException ex) {

            System.out.println("Error: " + ex.getMessage());
        }

        scanner.close();
    }
}