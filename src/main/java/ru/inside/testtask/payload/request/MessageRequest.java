package ru.inside.testtask.payload.request;

import lombok.Data;

@Data
public class MessageRequest {
    private String name;
    private String message;
}
