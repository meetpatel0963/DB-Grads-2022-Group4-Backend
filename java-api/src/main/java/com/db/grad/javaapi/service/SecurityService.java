package com.db.grad.javaapi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.grad.javaapi.constants.ErrorConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.security.Security;
import com.db.grad.javaapi.repository.SecurityRepository;

import lombok.NonNull;

@Service
public class SecurityService {

	@Autowired
    private SecurityRepository securityRepository;
	
    public List < Security > getAllSecurity() throws ResourceNotFoundException{
		List <Security> securities = securityRepository.findAll();
		if (securities == null || securities.isEmpty()) {
            throw new ResourceNotFoundException(ErrorConstants.NO_SECURITIES_FOUND);
        }
        return securities;
    }
    
    public Security getSecurityById(@NonNull final Long securityId) throws ResourceNotFoundException {
        Optional<Security> security = securityRepository.findById(securityId);
        if (!security.isPresent()) {
            throw new ResourceNotFoundException(ErrorConstants.NO_SECURITY_FOUND_FOR_GIVEN_ID);
        }
        else {
            return security.get();
        }
    }
    
    public void addSecurity(@NonNull final Security security) {
        securityRepository.saveAndFlush(security);
    }
    
    
    public Security updateSecurity(@NonNull final Long securityId, @NonNull final Security updateSecurity)
    		throws ResourceNotFoundException {
    	Optional<Security> _security = securityRepository.findById(securityId);
    	if (!_security.isPresent()) {
            throw new ResourceNotFoundException(ErrorConstants.NO_SECURITY_FOUND_FOR_GIVEN_ID);
        }
        else {
            Security security = _security.get();
            security.setIsin(updateSecurity.getIsin());
            security.setCusip(updateSecurity.getCusip());
        	security.setIssuer(updateSecurity.getIssuer());
        	security.setMaturitydate(updateSecurity.getMaturitydate());
        	security.setCoupon(updateSecurity.getCoupon());
        	security.setType(updateSecurity.getType());
        	security.setFacevalue(updateSecurity.getFacevalue());
        	security.setStatus(updateSecurity.getStatus());
        	return securityRepository.save(security);
        }
    }
    
    public Boolean deleteSecurity(@NonNull final Long securityId)
    	    throws ResourceNotFoundException {
    	Optional<Security> _security = securityRepository.findById(securityId);
    	if (!_security.isPresent()) {
            throw new ResourceNotFoundException(ErrorConstants.NO_SECURITY_FOUND_FOR_GIVEN_ID);
        }
    	else {            
    		Security security = _security.get();
	    	securityRepository.delete(security);
	    	return Boolean.TRUE;
    	    }
    	}
    
    public List < Security > getSecurityByDaterange(@NonNull final String date1, @NonNull final String date2)
	    throws ResourceNotFoundException, ParseException { 
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = sdformat.parse(date1);
			Date d2 = sdformat.parse(date2);
			
			List<Security> security = securityRepository.findAll();
			List<Security> newSecurity = new ArrayList<Security>();
			for (Security temp : security) {
				Date d = sdformat.parse(temp.getMaturitydate());
	            if(d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0) newSecurity.add(temp);
	        }

			return newSecurity;
		}

}
