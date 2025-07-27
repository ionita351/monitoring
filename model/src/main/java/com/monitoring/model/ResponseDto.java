package com.monitoring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ResponseDto {
    boolean success;
    String errorMessage;

    public static ResponseDto success() {
        return new ResponseDto(true, "");
    }

    public static ResponseDto error(String errorMessage) {
        return new ResponseDto(false, errorMessage);
    }
}
