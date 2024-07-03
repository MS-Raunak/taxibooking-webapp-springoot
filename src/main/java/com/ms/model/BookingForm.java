package com.ms.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "bookingform")
public class BookingForm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "name can't e empty")
	@NotBlank(message = "name can't be blank")
	@Size(min = 2, max = 30, message = "Invalid size")
	@Pattern(regexp = "^[A-Za-z][A-Za-z' -]*[A-Za-z]$", message = "name must contain only alphabet")
	@Column(length = 20)
	private String name;

	@NotEmpty(message = "source can't e empty")
	@NotBlank(message = "source can't be blank")
	@Size(min = 4, max = 50, message = "Invalid size")
	@Column(length = 50)
	private String source; //to

	@NotEmpty(message = "email can't e empty")
	@NotBlank(message = "email can't be blank")
	@Size(min = 11, max = 50, message = "Invalid size")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")	
	@Column(length = 50)
	private String email;
	
	@NotEmpty(message = "destination can't e empty")
	@NotBlank(message = "destination can't be blank")
	@Size(min = 4, max = 50, message = "Invalid size")
	@Column(length = 50)
	private String destination; //to
	
	@NotNull(message = "time can't be empty")
	private LocalTime time;
	
	@NotNull(message = "date can't be empty")
	private LocalDate date;
	
	@NotEmpty(message = "comfort can't e empty")
	@NotBlank(message = "comfort can't be blank")
	//@Size(min = 4, max = 50, message = "Invalid size of comfort")
	@Column(length = 50)
	private String comfort;
	
	@Min(value = 1, message = "adult can at least be 1")
	@Max(value = 4, message = "adults can be atmost 4")
	private int adult;
	
	@Max(value = 3, message = "Children can be atmost 3")
	private int children;

	@NotEmpty(message = "message can't e empty")
	@NotBlank(message = "message can't be blank")
	@Size(min = 2, max = 500, message = "Invalid size")
	@Column(length = 500)
	private String message;



}
