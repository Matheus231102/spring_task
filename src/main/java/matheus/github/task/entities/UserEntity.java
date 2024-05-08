package matheus.github.task.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import matheus.github.task.enums.EnumRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_users")
public class UserEntity implements UserDetails {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @EqualsAndHashCode.Include
     private UUID id;

     @Column
     private String name;

     @Column(unique = true)
     @EqualsAndHashCode.Include
     private String username;

     @Column(unique = true)
     @EqualsAndHashCode.Include
     private String email;

     @Column
     private String password;

     @Column
     private LocalDateTime creationDate;

     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     private List<TaskEntity> tasks;

     @JsonIgnore
     @Enumerated(EnumType.STRING)
     private EnumRole role;

     @PrePersist
     public void setUp() {
          setCreationDate(LocalDateTime.now());
          setRole(EnumRole.USER);
          setTasks(new ArrayList<>());
     }

     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
          switch (this.getRole()) {
               case MANAGER -> {
                    return List.of(
                            new SimpleGrantedAuthority("ROLE_MANAGER"),
                            new SimpleGrantedAuthority("ROLE_ADMIN"),
                            new SimpleGrantedAuthority("ROLE_USER")
                    );
               }
               case ADMIN -> {
                    return List.of(
                            new SimpleGrantedAuthority("ROLE_ADMIN"),
                            new SimpleGrantedAuthority("ROLE_USER")
                    );
               }
               case USER -> {
                    return List.of(
                            new SimpleGrantedAuthority("ROLE_USER")
                    );
               }
               case null, default -> {
                    return null;
               }
          }
     }

     @Override
     public boolean isAccountNonExpired() {
          return true;
     }

     @Override
     public boolean isAccountNonLocked() {
          return true;
     }

     @Override
     public boolean isCredentialsNonExpired() {
          return true;
     }

     @Override
     public boolean isEnabled() {
          return true;
     }
}
