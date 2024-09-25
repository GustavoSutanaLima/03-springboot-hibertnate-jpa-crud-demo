package edu.gustdev.springboot_hibertnate_jpa_crud_demo.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import edu.gustdev.springboot_hibertnate_jpa_crud_demo.dao.StudentDataAcessObject;
import edu.gustdev.springboot_hibertnate_jpa_crud_demo.entities.Student;
import jakarta.persistence.EntityManager;


@Repository
public class StudentDAOImplementation implements StudentDataAcessObject {
    //define field for entity manager 

    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public StudentDAOImplementation(EntityManager entityManagerSpring){
        this.entityManager = entityManagerSpring;
    }

    //implementar o método save abaixo:
    @Override
    @Transactional 
    /*A anotação @Transactional no Spring Boot é usada para gerenciar transações de banco de dados
    de forma declarativa. Quando você anota um método ou uma classe com @Transactional, 
    o Spring garante que o método seja executado dentro de uma transação. 
    Se ocorrer uma exceção durante a execução do método, a transação será revertida automaticamente.
        DEFINIÇÃO DE TRANSAÇÃO: 
        Uma transação é uma sequência de operações que são tratadas 
        como uma única unidade de trabalho. Essas operações devem ser concluídas 
        com sucesso ou todas devem ser revertidas (rollback) para manter a integridade dos 
        dados. Por exemplo, ao transferir dinheiro entre contas bancárias, 
        você quer garantir que o dinheiro seja debitado de uma conta e creditado na 
        outra como uma única operação. */
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    @Transactional //Transactional NÃO é necessário aqui pois os dados da tabela não
                   //estão sendo alterados, mas sim, apenas retornados pelo usuário;
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        List<Student> students;
        //JPQL (JPA SQL Query Language): permite a utilização Queries dentro da própria aplicação
        //mas o nome das tabelas e colunas precisão ser iguais ao das Entidades definidas em código
        students = entityManager.createQuery("SELECT s FROM Student s ORDER BY s.lastName", Student.class).getResultList();

        return students;
    }

    @Override
    public List<Student> findByLastName(String lastNameString) {
        List<Student> students;
        
        /*  Neste caso, usando o parâmtro :theLastName, é preciso usar o método .setParameter
        *   dentro de createQuery, para definir qual é a variável que será substituída por
        *   lastNameString 
        */
        students = entityManager
                    .createQuery("SELECT s FROM Student s WHERE s.lastName=:theLastName", Student.class)
                    .setParameter("theLastName", lastNameString) //define que o parametro "theLastName" da Query-String, será substituído pelo valor passado no argumento do método findByLastName;
                    .getResultList(); //retorna o resultado da Query como uma lista;
        return students;
    }

    @Override
    @Transactional 
    /*  Updates on the Data Base need to have Transactional, because they're 
        perfoming modification on the DB directly;
    */
    public void update(int idToUpdate, Student studentToUpdate) {
        Student sutdentId = findById(idToUpdate);
      
        sutdentId.setFirstName(studentToUpdate.getFirstName());
        sutdentId.setLastName(studentToUpdate.getLastName());
        sutdentId.setEmail(studentToUpdate.getEmail());
      
        entityManager.merge(sutdentId);
    }

    @Override
    @Transactional
    public void delete(int idToDelete) {
        Student studentToDelete = findById(idToDelete);
        entityManager.remove(studentToDelete);
        System.out.println(studentToDelete + ": was deleted from the database");

    }

    @Override
    @Transactional
    public void deleteByLastName(String lastNameToDelete) {
        int numberOfRowsAffected = entityManager
            .createQuery("DELETE FROM Student WHERE lastName=:lastNameToDelete")
            .setParameter("lastNameToDelete", lastNameToDelete)
            .executeUpdate();
        System.out.println(numberOfRowsAffected + " row(s) affected");
    }

}
