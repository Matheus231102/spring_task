package matheus.github.task.dto.mappers;

import matheus.github.task.dto.messagedto.MessageDTO;
import matheus.github.task.entities.MessageEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    @Autowired
    private ModelMapper modelMapper;

    public MessageDTO toDTO(MessageEntity messageEntity) {
	   return modelMapper.map(messageEntity, MessageDTO.class);
    }

    public MessageEntity toEntity(MessageDTO messageDTO) {
	   return modelMapper.map(messageDTO, MessageEntity.class);
    }
}
