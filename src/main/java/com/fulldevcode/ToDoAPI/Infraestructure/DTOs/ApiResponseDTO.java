package com.fulldevcode.ToDoAPI.Infraestructure.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponseDTO<T>{

    private T data;
    private String message;
    private Boolean isSuccess;
    private LocalDateTime timestamp;

    public ApiResponseDTO(){this.timestamp = LocalDateTime.now();}

    public ApiResponseDTO(Boolean success, String message, T data)
    {
        this.isSuccess = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponseDTO<T> Success (String message, T data)
    {
        return new ApiResponseDTO<>(true, message, data);
    }

    public static <T> ApiResponseDTO<T> Error (String message)
    {
        return new ApiResponseDTO<>(false, message, null);
    }

}
