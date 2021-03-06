package com.murali.rest.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Integer id;

	private String firstName;

	private String lastName;

	private String position;

	private int salary;

	private int age;

	private LocalDate dob;

	@ManyToOne(fetch = FetchType.LAZY)
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	private Organization organization;

}
