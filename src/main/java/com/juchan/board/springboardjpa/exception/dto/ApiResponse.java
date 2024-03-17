package com.juchan.board.springboardjpa.exception.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private static final String DEFAULT_STATUS = "SUCCESS";

    //status , data , msg
    private String status;

    private T data;

    private String message;

    //return data null인 경우 (CREATE, UPDATE, DELETE 같은 행위)
    public static ApiResponse<Void> create() {
        return ApiResponse.create(DEFAULT_STATUS,"정상 처리", null);
    }

    private static <T> ApiResponse<T> create(String status, String message, T data) {
        return ApiResponse.<T>builder()
                .status(status)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> create(T data) {
        return ApiResponse.<T>builder()
                .status("200")
                .message("조회 성공")
                .data(data)
                .build();
    }

    public static URI createdLocation(Object createdId) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdId)
                .toUri();
    }








}
