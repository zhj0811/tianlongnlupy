package com.cctegitc.ai.function.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long userId;
    private String uuid;
    private Long did;
    private String name;
    private String department;
    private String username;
    private String password;
    private String tel;
    private String sex;
    private Integer age;
    private String role;
    private String status;
    private List<String> permissions;
}
