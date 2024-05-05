package matheus.github.task.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.enums.EnumTaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tasks")
public class TaskEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private UUID id;

     @Column
     private String title;

     @Column
     private String description;

     @Column
     private LocalDateTime creationDate;

     @Enumerated(EnumType.STRING)
     private EnumTaskStatus status;

     @Enumerated(EnumType.STRING)
     private EnumTaskPriority priority;

     @JsonIgnore
     @ManyToOne
     private UserEntity user;

     @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     private NotificationEntity taskNotification;

     @PrePersist
     private void setUp() {
          setCreationDate(LocalDateTime.now());
          setTaskNotification(NotificationEntity.create());
     }

}
