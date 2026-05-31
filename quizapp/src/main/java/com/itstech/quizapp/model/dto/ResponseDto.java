package com.itstech.quizapp.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseDto {
    private Integer id;
    private String response;
}
