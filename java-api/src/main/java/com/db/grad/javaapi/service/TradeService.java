package com.db.grad.javaapi.service;

import com.db.grad.javaapi.constants.ErrorConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.trade.Trade;
import com.db.grad.javaapi.repository.TradeRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    public Trade getTradeById(@NonNull final Long tradeId) throws ResourceNotFoundException {
        Optional<Trade> trade = tradeRepository.findById(tradeId);
        if (!trade.isPresent()) {
            throw new ResourceNotFoundException(ErrorConstants.NO_TRADE_FOUND_FOR_GIVEN_ID);
        }
        else {
            return trade.get();
        }
    }

    public void addTrade(@NonNull final Trade trade) {
        tradeRepository.saveAndFlush(trade);
    }

    public Trade updateTrade(@NonNull final Long tradeId, @NonNull final Trade updateTrade) throws ResourceNotFoundException {
        Optional<Trade> _trade = tradeRepository.findById(tradeId);
        if (!_trade.isPresent()) {
            throw new ResourceNotFoundException(ErrorConstants.NO_TRADE_FOUND_FOR_GIVEN_ID);
        }
        else {
            Trade trade = _trade.get();
            trade.setCounterPartyId(updateTrade.getCounterPartyId());
            trade.setSecurityId(updateTrade.getSecurityId());
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

}
