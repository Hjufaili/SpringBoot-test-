package com.myFullstackAlharith.employee_management.entities;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class Employee {
    private UUID id;
    @NotNull(message = "first name is required")
    @Size(min = 2, max = 50, message = "min is 2 char and max is 50 char")
    private String firstName;
    @NotNull(message = "last name is required")
    private String lastName;
    @NotNull
    @Email(message = "Invalid email format")
    private String email;
    @NotNull
    @Pattern(regexp = "^\\ +? [0-9]{10,15}$", message = "Invalid NO. format")
    private String phoneNumber;
    @NotNull
    @PastOrPresent(message = "Hire date cannot be in the future ")
    private LocalDate hireDate;
    @NotNull
    private String position;
    private UUID departmentID;


}
