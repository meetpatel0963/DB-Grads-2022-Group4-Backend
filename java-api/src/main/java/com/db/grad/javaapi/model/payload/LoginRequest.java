package com.db.grad.javaapi.model.payload;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Getter 
@Setter
public class LoginRequest {

    private String username;
    private String password;

}

