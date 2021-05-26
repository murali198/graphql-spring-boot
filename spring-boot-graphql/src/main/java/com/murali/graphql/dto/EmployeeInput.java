package com.murali.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInput {

	private String firstName;
	private String lastName;
	private String position;
	private int salary;
	private int age;
	private LocalDate dob;
	private Integer departmentId;
	private Integer organizationId;
}
