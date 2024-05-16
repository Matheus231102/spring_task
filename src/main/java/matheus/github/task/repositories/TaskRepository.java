package matheus.github.task.repositories;

import matheus.github.task.entities.TaskEntity;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.enums.EnumTaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    List<TaskEntity> findByUser(UserEntity userEntity);

    @Transactional
    @Modifying
    @Query("delete from TaskEntity where user = ?1")
    void deleteAllByUser(UserEntity userEntity);

    @Transactional
    @Modifying
    @Query("delete from TaskEntity where user = ?1 and id = ?2")
    void deleteByUserAndId(UserEntity userEntity, UUID id);

}
