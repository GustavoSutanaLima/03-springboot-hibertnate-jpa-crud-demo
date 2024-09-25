# Data Access Object (DAO)

* No contexto do Spring Boot, usando Spring Data JPA e MySQL, o DAO é um padrão de design que fornece uma abstração para operações de acesso a dados, permitindo que você separe a lógica de persistência de dados da lógica de negócios.

## Implementação de DAO com Spring Data JPA
* Com o Spring Data JPA, você pode criar interfaces DAO que estendem a interface JpaRepository ou CrudRepository, fornecendo métodos CRUD (Create, Read, Update, Delete) prontos para uso. Aqui está um exemplo básico:

### Benefícios do DAO
* **Abstração**: Separa a lógica de persistência da lógica de negócios.
* **Reutilização**: Facilita a reutilização de código.
* **Manutenção**: Torna o código mais fácil de manter e testar.

#### Códigos
* Criação de uma entidade (classe da camanda de modelo) com nome de **User**:

    ```java
        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;

        @Entity
        public class User {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            private String name;
            private String email;
            // Getters e Setters
        }

    ```
* Criação de repositório que relaciona a entidade às operações **CRUD** o repositório é uma **interface** que estende (<i>extends</i>) ```CrudRepository``` ou ```JpaRepository``` à classe entidade  **User**:

    ```java
    import org.springframework.data.jpa.repository.JpaRepository;

    public interface UserRepository extends JpaRepository<User, Long> {
        // Métodos de consulta personalizados podem ser adicionados aqui
    } 
    ```
* Criação da classe de serviço de fato implementam os métodos das operações **CRUD** de fato:
    ```java
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class UserService {
        @Autowired
        private UserRepository userRepository;

        public List<User> findAll() {
            return userRepository.findAll();
        }

        public User save(User user) {
            return userRepository.save(user);
        }

        public void deleteById(Long id) {
            userRepository.deleteById(id);
        }
    }
    ```

