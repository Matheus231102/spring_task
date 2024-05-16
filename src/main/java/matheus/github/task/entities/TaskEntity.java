package matheus.github.task.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import matheus.github.task.enums.EnumTaskPriority;
import matheus.github.task.enums.EnumTaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_tasks")
public class TaskEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @EqualsAndHashCode.Include
     private UUID id;

     private String title;

     private String description;

     private LocalDateTime creationAt;

     private LocalDateTime lastUpdateAt;

     private LocalDateTime completionAt;

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
          setCreationAt(LocalDateTime.now());
          setLastUpdateAt(getCreationAt());
          setTaskNotification(NotificationEntity.create());
     }

}
