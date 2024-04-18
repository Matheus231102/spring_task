package matheus.github.task.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

     @Column
     private String name;

     @Column(unique = true)
     private String username;

     @Column(unique = true)
     private String email;

     @Column
     private String password;

     @Column
     private LocalDateTime creationDate;

     @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
     private List<TaskEntity> taskList;

     @PrePersist
     public void setUp() {
          setCreationDate(LocalDateTime.now());
     }

}
