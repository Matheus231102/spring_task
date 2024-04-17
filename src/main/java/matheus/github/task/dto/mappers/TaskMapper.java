package matheus.github.task.dto.mappers;

import matheus.github.task.dto.TaskDTO;
import matheus.github.task.dto.TaskRDTO;
import matheus.github.task.entities.TaskEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

     @Autowired
     private ModelMapper modelMapper;

     public TaskRDTO toRDTO(TaskEntity taskEntity) {
          return modelMapper.map(taskEntity, TaskRDTO.class);
     }

     public TaskDTO toDTO(TaskEntity userEntity) {
          return modelMapper.map(userEntity, TaskDTO.class);
     }

     public TaskEntity toEntity(TaskDTO taskDTO) {
          return modelMapper.map(taskDTO, TaskEntity.class);
     }

     public TaskEntity toEntity(TaskRDTO userRDTO) {
          return modelMapper.map(userRDTO, TaskEntity.class);
     }
}
