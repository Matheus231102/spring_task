package matheus.github.task.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(unique = true, nullable = false)
    private String message;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private NotificationEntity notification;

    public static MessageEntity create() {
        return new MessageEntity();
    }

    public MessageEntity setMessage(String message) {
        this.message = message;
        return this;
    }
}
