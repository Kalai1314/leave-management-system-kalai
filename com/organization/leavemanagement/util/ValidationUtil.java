package com.organization.leavemanagement.util;

import com.organization.leavemanagement.exception.InvalidLeaveRequestException;
import com.organization.leavemanagement.model.LeaveType;

public class ValidationUtil {

    // String Validation
    public static void validateString(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidLeaveRequestException(message);
        }
    }

    // Numeric Validation
    public static void validatePositiveNumber(int number, String message) {
        if (number <= 0) {
            throw new InvalidLeaveRequestException(message);
        }
    }

    // Enum Validation + Conversion (for runtime input)
    public static LeaveType validateLeaveType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new InvalidLeaveRequestException("Leave type cannot be blank");
        }

        try {
            return LeaveType.valueOf(type.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new InvalidLeaveRequestException(
                    "Invalid Leave Type. Use CASUAL / SICK / EARNED"
            );
        }
    }
}