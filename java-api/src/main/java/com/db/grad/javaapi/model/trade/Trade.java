package com.db.grad.javaapi.model.trade;

import com.db.grad.javaapi.model.book.Book;
import com.db.grad.javaapi.model.security.Security;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Trades")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trade {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "counterparty_id")
    private Long counterPartyId;

    private Long quantity;
    private String status;
    private Double price;

    @Column(name = "buy_sell")
    private String buySell;

    @Column(name = "trade_date")
    private Date tradeDate;

    @Column(name = "settlement_date")
    private Date settlementDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "security_id")
    @JsonIgnore
    private Security security;

}
