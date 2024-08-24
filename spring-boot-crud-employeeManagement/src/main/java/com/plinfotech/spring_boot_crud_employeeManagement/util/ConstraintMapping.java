package com.plinfotech.spring_boot_crud_employeeManagement.util;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashMap;
import java.util.Map;

public class ConstraintMapping {
    private static final Map<String, String> constraintToFieldMap = new HashMap<>();

    static {
        constraintToFieldMap.put("SYS.SYS_C008689", "email");
        constraintToFieldMap.put("SYS.SYS_C008690", "phone_number");
        // Add other mappings based on your database schema
    }
    public static String getFieldNameForConstraint(String constraintName) {
        return constraintToFieldMap.getOrDefault(constraintName, "unknown field");
    }
    public static String extractConstraintNameFromException(DataIntegrityViolationException ex) {
        String message = ex.getMessage();
        String constraintPrefix = "constraint [";
        int startIndex = message.indexOf(constraintPrefix) + constraintPrefix.length();
        int endIndex = message.indexOf(']', startIndex);
        return message.substring(startIndex, endIndex);
    }


}
