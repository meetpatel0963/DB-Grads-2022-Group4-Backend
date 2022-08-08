package com.db.grad.javaapi.model.payload;

import com.db.grad.javaapi.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpRequest {
	
    private String firstName;
    
    private String lastName;
    
    private String username;
    
    private String email;
    
    private String password;

	private Set<Role> roles;
	
}

