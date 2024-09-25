package edu.gustdev.springboot_hibertnate_jpa_crud_demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
    //atributos da classe Student (em inglês diz-se fields)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name") //especifica que a coluna "first_name" no banco de dados faz referência ao atributo da classe entity (Student)
    private String firstName;

    @Column(name = "last_name") //assim como a anotação anterior, "last_name" faz referência ao atributo de classe lastName;
    private String lastName;

    @Column(name = "email") //Neste caso, o nome da coluna no DataBase é igual ao nome da variável de classe, portanto, não é necessário especificar o nome da coluna;
    private String email;
    
    //Construtor principal da classe Student:
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //Construtor vazio:
    public Student( ) {

    }

    //getters and setters:
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //toString() method;
    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
    }

    


    


}
