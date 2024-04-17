package matheus.github.task.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_users")
public class UserEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String name;

     private String username;

     private String email;

     private String password;

     private LocalDateTime creationDate;

     @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
     private ArrayList<TaskEntity> taskList;

}
