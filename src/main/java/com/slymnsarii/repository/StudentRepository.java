package com.slymnsarii.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.slymnsarii.domain.Student;
import com.slymnsarii.dto.StudentDTO;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	boolean existsByEmail(String email);
	
	List<Student> findByLastName(String lastName);
	
	@Query("SELECT s from Student s WHERE s.grade=:pGrade")
	
	List<Student> findAllEqualsGrade(@Param("pGrade")Integer grade);
	
	@Query(value="SELECT * from Student s WHERE s.grade=:pGrade", nativeQuery=true)
	List<Student> findAllEqualsGradeWithSQL(@Param("pGrade")Integer grade);
	
	@Query("SELECT new com.tpro.dto.StudentDTO(s) FROM Student s WHERE s.id=:id")
	
	Optional<StudentDTO> findStudentDTOById(@Param("id")Long id);

}
