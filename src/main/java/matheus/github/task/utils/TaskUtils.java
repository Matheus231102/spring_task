package matheus.github.task.utils;

import matheus.github.task.dto.taskdto.TaskRDTO;

import java.util.Comparator;
import java.util.List;

public class TaskUtils {

	public static List<TaskRDTO> sorted(List<TaskRDTO> taskRDTOList) {
		return taskRDTOList.stream()
				.sorted(Comparator.comparing(TaskRDTO::getConclusion))
				.toList();
	}

	public static List<TaskRDTO> sortedInReverse(List<TaskRDTO> taskRDTOList) {
		return taskRDTOList.stream()
				.sorted(Comparator.comparing(TaskRDTO::getConclusion).reversed())
				.toList();
	}

}
