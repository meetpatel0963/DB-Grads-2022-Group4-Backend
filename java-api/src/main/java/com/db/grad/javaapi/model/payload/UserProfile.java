package com.db.grad.javaapi.model.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter 
@Setter
public class UserProfile {
	
    private Long id;
    private String username;
    private String email;
    private Date date;
	
}
