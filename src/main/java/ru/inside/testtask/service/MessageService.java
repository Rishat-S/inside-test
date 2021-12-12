package ru.inside.testtask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.inside.testtask.dto.MessageDTO;
import ru.inside.testtask.entity.Message;
import ru.inside.testtask.entity.User;
import ru.inside.testtask.facade.MessageFacade;
import ru.inside.testtask.repository.MessageRepository;
import ru.inside.testtask.repository.UserRepository;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    public static final Logger LOG = LoggerFactory.getLogger(MessageService.class);
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageFacade messageFacade;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository, MessageFacade messageFacade) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageFacade = messageFacade;
    }

    public void save(String msg, Principal principal) throws IOException {
        User user = getUserByPrincipal(principal);
        Message message = new Message();
        message.setUser(user);
        message.setMessage(msg);


        LOG.info("Saving message for user: {}", user.getUsername());
        messageRepository.save(message);
    }

    public List<MessageDTO> getAllMessagesByUser(Long limit, Principal principal) {
        User user = getUserByPrincipal(principal);
        List<Message> fileList = messageRepository.findAllByUserOrderByCreatedDate(user);
        return fileList.stream()
                .limit(limit)
                .map(messageFacade::messageToMessageDTO)
                .collect(Collectors.toList());
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }
}
