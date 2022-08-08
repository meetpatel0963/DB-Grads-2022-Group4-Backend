package com.db.grad.javaapi.model.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpRequest {
	
    private String firstName;
    
    private String lastName;
    
    private String username;
    
    private String email;
    
    private String password;

	private String role;
	
}

