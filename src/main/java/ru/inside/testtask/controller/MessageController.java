package ru.inside.testtask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inside.testtask.dto.MessageDTO;
import ru.inside.testtask.payload.response.MessageResponse;
import ru.inside.testtask.service.MessageService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public ResponseEntity<Object> saveMessage(@RequestBody MessageResponse messageResponse,
                                              Principal principal) throws IOException {
        if (messageResponse.getMessage().startsWith("history")) {
            Long limit = Long.parseLong(messageResponse.getMessage().split(" ")[1]);
            List<MessageDTO> messagesByUser = messageService.getAllMessagesByUser(limit, principal);
            return ResponseEntity.ok(messagesByUser);
        } else {
            messageService.save(messageResponse.getMessage(), principal);
            return ResponseEntity.ok(new MessageResponse("Message save successfully"));
        }


    }

}
