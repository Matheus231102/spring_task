package matheus.github.task.repositories;

import matheus.github.task.entities.TaskEntity;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.enums.EnumTaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByUser(UserEntity userEntity);
    void deleteAllByUser(UserEntity userEntity);
    List<TaskEntity> findByUserAndPriority(UserEntity userEntity, EnumTaskPriority enumTaskPriority);
    List<TaskEntity> findByUserAndTitleStartingWith(UserEntity userEntity, String startsWith);
    void deleteByTitle(String title);
}
