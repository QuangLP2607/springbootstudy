package com.example.ex7restfulapi.common;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    @Builder.Default
    private String timestamp = java.time.LocalDateTime.now().toString();

    public static <T> ApiResponse<T> success(ErrorCode code, T data) {
        return ApiResponse.<T>builder()
                .code(code.code())
                .message(code.message())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(ErrorCode code, String customMessage) {
        return ApiResponse.<T>builder()
                .code(code.code())
                .message(customMessage != null ? customMessage : code.message())
                .data(null)
                .build();
    }
}
