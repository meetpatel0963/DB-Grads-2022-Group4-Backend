package com.db.grad.javaapi.service;

import com.db.grad.javaapi.constants.MessageConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.book.Book;
import com.db.grad.javaapi.model.security.Security;
import com.db.grad.javaapi.model.trade.Trade;
import com.db.grad.javaapi.model.user.User;
import com.db.grad.javaapi.repository.SecurityRepository;
import com.db.grad.javaapi.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SecurityService {

	@Autowired
    private SecurityRepository securityRepository;

	@Autowired
	private UserRepository userRepository;

    public List < Security > getAllSecurity() throws ResourceNotFoundException{
		List <Security> securities = securityRepository.findAll();
		if (securities == null || securities.isEmpty()) {
            throw new ResourceNotFoundException(MessageConstants.NO_SECURITIES_FOUND);
        }
        return securities;
    }
    
    public Security getSecurityById(@NonNull final Long securityId) throws ResourceNotFoundException {
        Security security = securityRepository.findById(securityId).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_SECURITY_FOUND_FOR_GIVEN_ID));
		return security;
    }
    
    public void addSecurity(@NonNull final Security security) {
        securityRepository.saveAndFlush(security);
    }

    public Security updateSecurity(@NonNull final Long securityId, @NonNull final Security updateSecurity)
    		throws ResourceNotFoundException {
    	Security security = securityRepository.findById(securityId).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_SECURITY_FOUND_FOR_GIVEN_ID));
		security.setISIN(updateSecurity.getISIN());
		security.setCUSIP(updateSecurity.getCUSIP());
		security.setIssuer(updateSecurity.getIssuer());
		security.setMaturityDate(updateSecurity.getMaturityDate());
		security.setCoupon(updateSecurity.getCoupon());
		security.setType(updateSecurity.getType());
		security.setFaceValue(updateSecurity.getFaceValue());
		security.setStatus(updateSecurity.getStatus());
		return securityRepository.save(security);
    }
    
    public Boolean deleteSecurity(@NonNull final Long securityId) throws ResourceNotFoundException {
		Security security = securityRepository.findById(securityId).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_SECURITY_FOUND_FOR_GIVEN_ID));
		securityRepository.delete(security);
		return Boolean.TRUE;
	}

	public List<Security> getSecuritiesByUserId(@NonNull final Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_USER_FOUND_FOR_GIVEN_USER_ID));

		Set<Book> books = user.getBooks();
		Set<Trade> trades = new HashSet<>();
		for (Book book : books) {
			trades.addAll(book.getTrades());
		}

		Set<Security> securities = new HashSet<>();
		for (Trade trade : trades) {
			securities.add(trade.getSecurity());
		}

		return new ArrayList<>(securities);
	}

	public List<Trade> getTradesBySecurityId(@NonNull final Long securityId) throws ResourceNotFoundException {
		Security security = securityRepository.findById(securityId).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_TRADES_FOUND_FOR_GIVEN_SECURITY_ID));
		return new ArrayList<>(security.getTrades());
	}

    public List < Security > getSecurityByDateRange(@NonNull final String from, @NonNull final String to)
			throws ResourceNotFoundException, ParseException {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdformat.parse(from);
		Date d2 = sdformat.parse(to);

		List<Security> security = securityRepository.findAll();
		List<Security> newSecurity = new ArrayList<Security>();
		for (Security temp : security) {
			Date d = sdformat.parse(temp.getMaturityDate());
			if(d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0) newSecurity.add(temp);
		}

		return newSecurity;
	}

}
