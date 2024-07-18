package com.auth.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequstBody {

    private String name;
    private String email;
    private String password;
}
