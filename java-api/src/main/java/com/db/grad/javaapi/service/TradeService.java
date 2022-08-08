package com.db.grad.javaapi.service;

import com.db.grad.javaapi.constants.MessageConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.security.Security;
import com.db.grad.javaapi.model.trade.Trade;
import com.db.grad.javaapi.repository.TradeRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    public Trade getTradeById(@NonNull final Long tradeId) throws ResourceNotFoundException {
        Trade trade = tradeRepository.findById(tradeId).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_TRADE_FOUND_FOR_GIVEN_ID));
        return trade;
    }

    public Security getSecurityForTrade(@NonNull final Long tradeId) throws ResourceNotFoundException {
        Trade trade = tradeRepository.findById(tradeId).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_TRADE_FOUND_FOR_GIVEN_ID));
        return trade.getSecurity();
    }

    public void addTrade(@NonNull final Trade trade) {
        tradeRepository.saveAndFlush(trade);
    }

    public Trade updateTrade(@NonNull final Long tradeId, @NonNull final Trade updateTrade) throws ResourceNotFoundException {
        Trade trade = tradeRepository.findById(tradeId).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_TRADE_FOUND_FOR_GIVEN_ID));
        trade.setCounterPartyId(updateTrade.getCounterPartyId());
        trade.setQuantity(updateTrade.getQuantity());
        trade.setStatus(updateTrade.getStatus());
        trade.setPrice(updateTrade.getPrice());
        trade.setBuySell(updateTrade.getBuySell());
        trade.setTradeDate(updateTrade.getTradeDate());
        trade.setSettlementDate(updateTrade.getSettlementDate());
        trade.setBook(updateTrade.getBook());
        return tradeRepository.save(trade);
    }

}
