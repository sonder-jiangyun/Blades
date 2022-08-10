package com.example.blades.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: 11119824
 * @Date: 2022/8/10
 * @Description:
 */
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

}
