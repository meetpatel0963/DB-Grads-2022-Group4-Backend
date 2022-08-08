package com.db.grad.javaapi.model.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter 
@Setter
public class UserSummary {
	
    private Long id;
    private String username;
    private String email;
	
}

