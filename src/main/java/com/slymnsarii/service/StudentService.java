package com.slymnsarii.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.slymnsarii.domain.Student;
import com.slymnsarii.dto.StudentDTO;
import com.slymnsarii.exception.ConflictException;
import com.slymnsarii.exception.ResourceNotFoundException;
import com.slymnsarii.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAll() {
		
		return studentRepository.findAll();
	}

	public void createStudent(Student student) {
		
		if (studentRepository.existsByEmail(student.getEmail())) {
			throw new ConflictException("Email is already exist!");
		}
		studentRepository.save(student);
	}

	public Student findStudent(Long id) {
		return studentRepository.findById(id).orElseThrow(()-> 
						new ResourceNotFoundException("Student not found with id : "+id));
	}

	public Student deleteStudent(Long id) {
		Student student=findStudent(id);
		 studentRepository.delete(student);
		 return student;
	}
	
	public void updateStudent(Long id, StudentDTO studentDTO) {
		
		boolean emailExist=studentRepository.existsByEmail(studentDTO.getEmail()); 
		
		
		Student student=findStudent(id); 
		
		
		if (emailExist && !studentDTO.getEmail().equals(student.getEmail())) {
			
			throw new ConflictException("Email is already exist");
		}
		
		student.setName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setGrade(studentDTO.getGrade());
		student.setEmail(studentDTO.getEmail());
		student.setPhoneNumber(studentDTO.getPhoneNumber());
		
		studentRepository.save(student); 
	}


	public Page<Student> getAllWithPage(Pageable pageable) {
		return studentRepository.findAll(pageable);
		
	}

	public List<Student> findStudent(String lastName) {
		
		return studentRepository.findByLastName(lastName); 
		
	}

	public List<Student> findAllEqualsGrade(Integer grade) {
		
		return studentRepository.findAllEqualsGrade(grade);
	}

	public StudentDTO findStudentDTOById(Long id) {
		
		return studentRepository.
				findStudentDTOById(id).
				orElseThrow(()-> new ResourceNotFoundException("Student not found with id: " + id)); 
					
		
	}

}
