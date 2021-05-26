package com.murali.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	private Integer id;
	private String firstName;
	private String lastName;
	private String position;
	private int salary;
	private int age;
	private Integer departmentId;
	private Integer orgId;
	private LocalDate dob;
}
