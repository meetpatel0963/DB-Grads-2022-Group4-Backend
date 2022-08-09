package com.db.grad.javaapi.service;

import com.db.grad.javaapi.constants.MessageConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.book.Book;
import com.db.grad.javaapi.model.counterparty.CounterParty;
import com.db.grad.javaapi.model.security.Security;
import com.db.grad.javaapi.model.trade.Trade;
import com.db.grad.javaapi.repository.BookRepository;
import com.db.grad.javaapi.repository.CounterPartyRepository;
import com.db.grad.javaapi.repository.SecurityRepository;
import com.db.grad.javaapi.repository.TradeRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SecurityRepository securityRepository;

    @Autowired
    private CounterPartyRepository counterPartyRepository;

    public Trade getTradeById(@NonNull final Long tradeId) throws ResourceNotFoundException {
        Trade trade = tradeRepository.findById(tradeId).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_TRADE_FOUND_FOR_GIVEN_ID));
        return trade;
    }

    public Security getSecurityForTrade(@NonNull final Long tradeId) throws ResourceNotFoundException {
        Trade trade = tradeRepository.findById(tradeId).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_TRADE_FOUND_FOR_GIVEN_ID));
        return trade.getSecurity();
    }

    public void addTrade(@NonNull final Trade trade) throws ResourceNotFoundException {
        Book book = bookRepository.findById(trade.getBook().getId()).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_BOOK_FOUND_FOR_GIVEN_ID));
        trade.setBook(book);
        Security security = securityRepository.findById(trade.getSecurity().getId()).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_SECURITY_FOUND_FOR_GIVEN_ID));
        trade.setSecurity(security);
        CounterParty counterParty = counterPartyRepository.findById(trade.getCounterParty().getId()).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_COUNTERPARTY_FOUND_FOR_GIVEN_ID));
        trade.setCounterParty(counterParty);
        tradeRepository.saveAndFlush(trade);
    }

    public Trade updateTrade(@NonNull final Long tradeId, @NonNull final Trade updateTrade) throws ResourceNotFoundException {
        Trade trade = tradeRepository.findById(tradeId).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_TRADE_FOUND_FOR_GIVEN_ID));
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
