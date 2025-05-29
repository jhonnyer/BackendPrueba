package com.comercio.dto;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiResponse {

    private boolean success;
    private Object data;
    private LocalDateTime timestamp;

    private ApiResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static ApiResponse success(Object data) {
        return new ApiResponse(true, data);
    }

    public static ApiResponse failure(Object error) {
        return new ApiResponse(false, error);
    }

}