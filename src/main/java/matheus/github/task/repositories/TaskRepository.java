package matheus.github.task.repositories;

import matheus.github.task.entities.TaskEntity;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.enums.EnumTaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByUser(UserEntity userEntity);
    void deleteByUser(UserEntity userEntity);
    List<TaskEntity> findByUserAndPriority(UserEntity userEntity, EnumTaskPriority enumTaskPriority);
    List<TaskEntity> findByDescriptionStartingWith(String startsWith);
    List<TaskEntity> findByTitleStartingWith(String startsWith);
    void deleteByTitleStartingWith(String title);
}
