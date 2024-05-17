package matheus.github.task.repositories.spec;

import matheus.github.task.entities.TaskEntity;
import matheus.github.task.entities.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TaskSpecs {

	public static Specification<TaskEntity> withUserEqual(UserEntity user) {
		return (root, query, criteriaBuilder) ->
				criteriaBuilder.equal(root.get("user"), user);
	}

	public static Specification<TaskEntity> withConclusionDateUnderOrEqual(LocalDateTime localDateTime) {
		return (root, query, criteriaBuilder) ->
				criteriaBuilder.lessThanOrEqualTo(root.get("conclusion"), localDateTime);
	}

	public static Specification<TaskEntity> withConclusionDateOverOrEqual(LocalDateTime localDateTime) {
		return (root, query, criteriaBuilder) ->
				criteriaBuilder.greaterThanOrEqualTo(root.get("conclusion"), localDateTime);
	}

}
