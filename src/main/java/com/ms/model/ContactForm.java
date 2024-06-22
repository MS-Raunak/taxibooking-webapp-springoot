package com.ms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "contactform")
public class ContactForm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name can't be empty") 
    @Size(min = 2, max = 30, message = "Invalid name size")
    @Column(length = 30) //hibernate validation
    private String name;

    @NotEmpty(message = "Email can't be empty")
    @Size(min = 10, max = 50, message = "Invalid email size")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    @Column(length = 50) //hibernate validation
    private String email;

    @NotNull(message = "Phone number can't be empty")
    @Min(value = 1000000000L, message = "Mobile number must have 10 digits")
    @Max(value = 9999999999L, message = "Mobile number must have 10 digits")
    @Column(length = 10) //hibernate validation
    private Long phone;

    @NotEmpty(message = "Message can't be empty")
    @Size(min = 3, max = 500, message = "Invalid message size")
    @Column(length = 500) //hibernate validation
    private String message;
}
