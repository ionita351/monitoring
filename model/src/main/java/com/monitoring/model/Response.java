package com.monitoring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Response {
    boolean success;
    String errorMessage;

    public static Response success() {
        return new Response(true, "");
    }

    public static Response error(String errorMessage) {
        return new Response(false, errorMessage);
    }
}
