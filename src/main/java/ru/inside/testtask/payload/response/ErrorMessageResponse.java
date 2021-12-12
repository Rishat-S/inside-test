package ru.inside.testtask.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessageResponse {
    private String message;
    private int id;
}