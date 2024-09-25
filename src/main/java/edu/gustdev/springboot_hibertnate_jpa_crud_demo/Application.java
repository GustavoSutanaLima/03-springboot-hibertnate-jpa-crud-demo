package edu.gustdev.springboot_hibertnate_jpa_crud_demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.gustdev.springboot_hibertnate_jpa_crud_demo.dao.StudentDataAcessObject;
import edu.gustdev.springboot_hibertnate_jpa_crud_demo.dao.implementation.StudentDAOImplementation;
import edu.gustdev.springboot_hibertnate_jpa_crud_demo.entities.Student;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	//Injeção do StudentDataAccessObject
	public CommandLineRunner commandLineRunner(StudentDataAcessObject studentDAO) {
		return runner -> 
		{
		
		//createStudent(studentDAO);
		createMultiStudent(studentDAO);
		//System.out.println(retrieveStudent(studentDAO));
		//findAllStudents(studentDAO);
		//findStudentByLastName(studentDAO);
		//mergingStudentById(studentDAO);
		//deletingAStudent(studentDAO);
		//deleteStudentByLastName(studentDAO);
		};
	}

	private void deleteStudentByLastName(StudentDataAcessObject studentDAO) {
		studentDAO.deleteByLastName("Lima");
	}

	private void deletingAStudent(StudentDataAcessObject studentDAO) {
		studentDAO.delete(5);
	}

	private void mergingStudentById(StudentDataAcessObject studentDAO) {
		studentDAO.update(1, new Student("Renan", "Carbalho", "renancarvalho@gmail.com"));
	}

	private void findStudentByLastName(StudentDataAcessObject studentDAO) {
		
		for (Student student : studentDAO.findByLastName("Lima")) {
			System.out.println(student);
		}
	}

	private void findAllStudents(StudentDataAcessObject studentDAO) {
		List<Student> students = studentDAO.findAll();
		for (Student student : students) {
			System.out.println(student);
		}
	}

	private Student retrieveStudent(StudentDataAcessObject studentDAO) {
		return studentDAO.findById(5);
	}

	private void createMultiStudent(StudentDataAcessObject studentDAO) {
		Student tempStudent1 = new Student("Maria", "Lima", "marialima@gmail.com");
		Student tempStudent2 = new Student("Eva", "Lima", "evalima@gmail.com");
		Student tempStudent3 = new Student("Antonio", "Lima", "antoniolima@gmail.com");

		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDataAcessObject studentDAO) {
		//creates a new student;
		System.out.println("Cria um novo estudante...");
		Student tempStudent = new Student("Gustavo", "Lima", "gustavosutanalima@gmail.com");

		//save a new student;
		System.out.println("Salva no estudante no DB...");
		studentDAO.save(tempStudent);
		
		//shows the student's id:
		System.out.println("Retorna o ID do usuário");
		System.out.println(tempStudent.getId());
	}

}
