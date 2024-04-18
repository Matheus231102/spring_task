package matheus.github.task.repositories;

import matheus.github.task.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
     Optional<UserEntity> findByUsername(String username);
     Optional<UserEntity> findByEmail(String email);
     boolean existsByUsername(String username);
     boolean existsByEmail(String email);
}
