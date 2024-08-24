package com.plinfotech.spring_boot_crud_employeeManagement.entity;

import com.plinfotech.spring_boot_crud_employeeManagement.enums.EmploymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    /*@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id*/
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid and contain 10 to 15 digits")
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @NotBlank(message = "Department is mandatory")
    @Size(max = 100, message = "Department cannot exceed 100 characters")
    @Column(name = "department", nullable = false)
    private String department;

    @NotBlank(message = "Job title is mandatory")
    @Size(max = 100, message = "Job title cannot exceed 100 characters")
    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @NotNull(message = "Salary is mandatory")
    @Min(value = 0, message = "Salary must be a positive number")
    @Column(name = "salary", nullable = false)
    private Double salary;

    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Date of birth must be a past date")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotNull(message = "Date of joining is mandatory")
    @PastOrPresent(message = "Date of joining cannot be a future date")
    @Column(name = "date_of_joining", nullable = false)
    private LocalDate dateOfJoining;

    @Size(max = 200, message = "Address cannot exceed 200 characters")
    @Column(name = "address")
    private String address;

    @Size(max = 100, message = "City cannot exceed 100 characters")
    @Column(name = "city")
    private String city;

    @Size(max = 100, message = "State cannot exceed 100 characters")
    @Column(name = "state")
    private String state;

    @Size(max = 20, message = "Postal code cannot exceed 20 characters")
    @Column(name = "postal_code")
    private String postalCode;

    @Size(max = 100, message = "Country cannot exceed 100 characters")
    @Column(name = "country")
    private String country;

    @NotNull(message = "Employment status is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "employment_status", nullable = false)
    private EmploymentStatus employmentStatus;


}
