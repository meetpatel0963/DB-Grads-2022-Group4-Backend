package com.db.grad.javaapi.model.security;

import com.db.grad.javaapi.model.trade.Trade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

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

    private long ISIN;
	private long CUSIP;
	private long issuer;
	private String maturityDate;
	private long coupon;
	private String type;
	private long faceValue;
	private String status;

	@OneToMany(mappedBy = "security", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Trade> trades;

}
