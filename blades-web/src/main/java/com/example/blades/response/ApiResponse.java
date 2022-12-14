package com.example.blades.response;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "成功";

    private Integer code;

    private String message;

    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> ApiResponse<T> error(String msg) {
        return new ApiResponse<>(10000, msg, null);
    }

    public static <T> ApiResponse<T> error(Throwable e){
        Throwable throwable = e.getCause() == null ? e : e.getCause();
        String msg = String.format("%s: %s", throwable.getClass().getName(), throwable.getMessage());
        return ApiResponse.error(msg);
    }

}
