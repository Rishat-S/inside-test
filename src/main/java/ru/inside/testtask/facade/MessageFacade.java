package ru.inside.testtask.facade;

import org.springframework.stereotype.Component;
import ru.inside.testtask.dto.MessageDTO;
import ru.inside.testtask.entity.Message;


@Component
public class MessageFacade {

    public MessageDTO messageToMessageDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(message.getMessage());
        return messageDTO;
    }
}
