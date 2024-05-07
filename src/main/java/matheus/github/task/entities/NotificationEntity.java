package matheus.github.task.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table
@Entity(name = "tb_notifications")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
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
