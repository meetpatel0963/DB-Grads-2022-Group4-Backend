package com.db.grad.javaapi.controller;

import com.db.grad.javaapi.constants.MessageConstants;
import com.db.grad.javaapi.constants.URIConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.ErrorResponse;
import com.db.grad.javaapi.model.security.Security;
import com.db.grad.javaapi.model.trade.Trade;
import com.db.grad.javaapi.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(URIConstants.API_V1)
@Slf4j
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping(URIConstants.GET_TRADE_BY_ID)
    @PreAuthorize(MessageConstants.USER_ADMIN)
    public ResponseEntity<?> getTradeById(@PathVariable(value = "tradeId") Long tradeId) {
        try {
            log.info("Fetch Trade Request For Trade ID: {}", tradeId);
            Trade trade = tradeService.getTradeById(tradeId);
            return ResponseEntity.ok().body(trade);
        }
        catch (ResourceNotFoundException E) {
            log.error(MessageConstants.FETCH_TRADE_BY_ID_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping(URIConstants.GET_SECURITY_FOR_TRADE)
    @PreAuthorize(MessageConstants.USER_ADMIN)
    public ResponseEntity<?> getSecurityForTrade(@PathVariable(value = "tradeId") Long tradeId) {
        try {
            log.info("Fetch Security For Trade ID: {}", tradeId);
            Security security = tradeService.getSecurityForTrade(tradeId);
            return ResponseEntity.ok().body(security);
        }
        catch (ResourceNotFoundException E) {
            log.error(MessageConstants.FETCH_SECURITY_FOR_TRADE_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping(URIConstants.ADD_TRADE)
    @PreAuthorize(MessageConstants.ADMIN)
    public ResponseEntity<?> addTrade(@Valid @RequestBody Trade trade) {
        log.info("Add Trade Request For Trade: {}", trade);
        tradeService.addTrade(trade);
        return ResponseEntity.ok().body(trade);
    }

    @PutMapping(URIConstants.UPDATE_TRADE)
    @PreAuthorize(MessageConstants.ADMIN)
    public ResponseEntity<?> updateTrade(@PathVariable(value = "tradeId") Long tradeId, @Valid @RequestBody Trade trade) throws ResourceNotFoundException {
        try {
            log.info("Update Trade Request For Trade ID: {}", tradeId);
            Trade updatedTrade = tradeService.updateTrade(tradeId, trade);
            return ResponseEntity.ok().body(updatedTrade);
        }
        catch (ResourceNotFoundException E) {
            log.error(MessageConstants.UPDATE_TRADE_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
