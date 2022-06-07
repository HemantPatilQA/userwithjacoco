package com.selflearning.userwithjacoco.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long uid;
    private String username;
    private int age;
    private String address;
}
