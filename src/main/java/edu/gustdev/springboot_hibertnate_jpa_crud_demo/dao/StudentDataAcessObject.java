package edu.gustdev.springboot_hibertnate_jpa_crud_demo.dao;

import java.util.List;

import edu.gustdev.springboot_hibertnate_jpa_crud_demo.entities.Student;

/**
 * Esta classe será usada para interface com banco de dados.
 */
public interface StudentDataAcessObject  {

    public void save(Student student);

    public Student findById(int id);

    public List<Student> findAll();

    public List<Student> findByLastName(String lastNameString);

    public void update(int idToUpdate, Student studentToUpdate);

    public void delete(int idToDelete);

    public void deleteByLastName(String lastNameToDelete);
}
