package com.ms.model;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class BookingForm {
	private int id;
	private String name;
	private String from;
	private String email;
	private String to;
	private LocalTime time;
	private LocalDate date;
	private int adult;
	private int children;
	private String message;



}
