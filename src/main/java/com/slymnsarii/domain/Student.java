package com.slymnsarii.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.slymnsarii.domain.enums.userRole;

import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
@Entity 
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
													
	private Long id; 
	
	@NotNull(message = "first name can not be null")
	@NotBlank(message = "last name can not be white space") 
	@Size(min = 2,max = 25, message = "First name '${validatedValue}' must be between {min} and {max} long") 
	@Column(nullable = false, length=25)
	private String name;
	
	@Column(nullable = false, length=25)
	private String lastName;
	
	@Column
	private Integer grade;
	
	@Column(nullable = false, length=50, unique = true) 
	@Email(message = "Provide valid email") 
	private String email;
	
	@Column
	private String phoneNumber;
	
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="MM/dd/yyyy HH:mm:ss", timezone="Turkey")
	
	private LocalDateTime createDate=LocalDateTime.now(); 
	@OneToMany(mappedBy = "student")
	private List<Book>books=new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
}
