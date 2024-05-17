package matheus.github.task.repositories.task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import matheus.github.task.entities.TaskEntity;
import matheus.github.task.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepositoryQueries{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<TaskEntity> getTasksByConclusionDate(UserEntity userEntity,
													 LocalDateTime minConclusionDate,
													 LocalDateTime maxConclusionDate) {
		{
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<TaskEntity> criteriaQuery = criteriaBuilder.createQuery(TaskEntity.class);
			List<Predicate> predicates = new ArrayList<>();

			Root<TaskEntity> root = criteriaQuery.from(TaskEntity.class);

			CriteriaQuery<TaskEntity> query = criteriaQuery.select(root);

			Predicate userCondition = criteriaBuilder.equal(root.get("user"), userEntity);
			predicates.add(userCondition);

			if (maxConclusionDate != null) {
				Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("conclusion"), maxConclusionDate);
				predicates.add(predicate);
			}

			if (minConclusionDate != null) {
				Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("conclusion"), minConclusionDate);
				predicates.add(predicate);
			}

			Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);
			criteriaQuery.where(predicatesArray);

			TypedQuery<TaskEntity> typedQuery = entityManager.createQuery(query);
			List<TaskEntity> resultList = typedQuery.getResultList();

			Collections.sort(resultList, Comparator.comparing(TaskEntity::getConclusion));

			return resultList;
		}
	}
}


