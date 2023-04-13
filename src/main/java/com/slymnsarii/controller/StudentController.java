package com.slymnsarii.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slymnsarii.domain.Student;
import com.slymnsarii.dto.StudentDTO;
import com.slymnsarii.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Student Controller";
	}
	
	//GetAllStudent
	@GetMapping
	public ResponseEntity<List<Student>>getAll(){
		List<Student> students=studentService.getAll();
		return ResponseEntity.ok(students);
	}
	
	//createNewStudent
	@PostMapping
	public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody Student student){
		studentService.createStudent(student);
		Map<String, String> map=new HashMap<>();
		map.put("message", "Student is created successfully");
		map.put("status", "true");
		return new ResponseEntity<>(map,HttpStatus.CREATED);
	}
	
	//get a Student By ID by RequestParam
	@GetMapping("/query")
	public ResponseEntity<Student> getStudent(@RequestParam("id") Long id){
		Student student=studentService.findStudent(id);
		return ResponseEntity.ok(student);
		
	}
	
	//get a Student By ID by PathVariable
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentWithPath(@PathVariable("id") Long id){
		Student student=studentService.findStudent(id);
		return ResponseEntity.ok(student);
	}
	
	//Delete Student
	@DeleteMapping("/{id}")
	public ResponseEntity<Student> deleteStudents(@PathVariable("id")Long id){
		Student student=studentService.deleteStudent(id);
		return ResponseEntity.ok(student);
	}
	
	//Update Student, DTO kullanilacak
	@PutMapping("{id}")
	public ResponseEntity<Map<String, String>> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDTO studentDTO){
		studentService.updateStudent(id,studentDTO);
		Map<String, String> map=new HashMap<>();
		map.put("message", "Student is updated successfully");
		map.put("status", "true");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	
	
	
	
	
}
