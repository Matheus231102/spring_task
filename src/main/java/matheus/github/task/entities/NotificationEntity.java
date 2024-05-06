package matheus.github.task.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "tb_notifications")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MessageEntity> messages;

    public static NotificationEntity create() {
        return new NotificationEntity();
    }

    @PrePersist
    private void setUp() {
        setMessages(new ArrayList<>());
    }

}
