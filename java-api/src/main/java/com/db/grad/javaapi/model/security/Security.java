package com.db.grad.javaapi.model.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Security")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Security {

	@Id
	private long id;
    private long isin;
	private long cusip; 
	private long issuer;
	private String maturitydate;
	private long coupon;
	private String type;
	private long facevalue;
	private String status;
}
