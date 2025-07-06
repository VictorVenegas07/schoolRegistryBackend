package com.codesa.schoolRegistry.application.UserCase.Auth.Querys.Login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginQuery {
    private String username;
    private String password;
}
