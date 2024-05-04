package matheus.github.task.repositories;

import matheus.github.task.entities.TaskEntity;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.enums.EnumTaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
    List<TaskEntity> findByUser(UserEntity userEntity);
    void deleteAllByUser(UserEntity userEntity);
    void deleteByUserAndId(UserEntity userEntity, UUID id);
    List<TaskEntity> findByUserAndPriority(UserEntity userEntity, EnumTaskPriority enumTaskPriority);
    List<TaskEntity> findByUserAndTitleStartingWith(UserEntity userEntity, String startsWith);
}
