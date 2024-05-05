package matheus.github.task.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "tb_notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String message;

    public static NotificationEntity create() {
        return new NotificationEntity();
    }

    @PrePersist
    private void setUp() {
        setMessage("THIS IS THE STANDARD MESSAGE");
    }

}
