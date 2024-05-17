package matheus.github.task.repositories.task;

import matheus.github.task.entities.TaskEntity;
import matheus.github.task.entities.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepositoryQueries {

	List<TaskEntity> getTasksByConclusionDate(UserEntity userEntity,
											  LocalDateTime minConclusionDate,
											  LocalDateTime maxConclusionDate);

}
