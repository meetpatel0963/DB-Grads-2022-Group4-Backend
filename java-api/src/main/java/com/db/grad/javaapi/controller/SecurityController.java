package com.db.grad.javaapi.controller;

import com.db.grad.javaapi.constants.MessageConstants;
import com.db.grad.javaapi.constants.URIConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.ErrorResponse;
import com.db.grad.javaapi.model.security.Security;
import com.db.grad.javaapi.model.trade.Trade;
import com.db.grad.javaapi.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(URIConstants.API_V1)
@Slf4j
public class SecurityController {

	@Autowired
    private SecurityService securityService;

	@GetMapping(URIConstants.GET_ALL_SECURITY)
    @PreAuthorize(MessageConstants.USER_ADMIN)
	public ResponseEntity<?> getAllSecurity(){
		try {
			log.info("Fetch All Securities");
			List<Security> securities = securityService.getAllSecurity();
			return ResponseEntity.ok().body(securities);
		}
		catch(ResourceNotFoundException E){
			log.error(MessageConstants.NO_SECURITIES_FOUND + E.getMessage());
			ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		
	}
	
    @GetMapping(URIConstants.GET_SECURITY_BY_ID)
    @PreAuthorize(MessageConstants.USER_ADMIN)
    public ResponseEntity<?> getSecurityById(@PathVariable(value = "securityId") Long securityId) {
        try {
            log.info("Fetch Security Request For Security ID: {}", securityId);
            Security security = securityService.getSecurityById(securityId);
            return ResponseEntity.ok().body(security);
        }
        catch (ResourceNotFoundException E) {
            log.error(MessageConstants.FETCH_SECURITY_BY_ID_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    
    @PostMapping(URIConstants.ADD_SECURITY)
    @PreAuthorize(MessageConstants.ADMIN)
    public ResponseEntity<?> addSecurity(@Valid @RequestBody Security security) {
        log.info("Add Security Request For Security: {}", security);
        securityService.addSecurity(security);
        return ResponseEntity.ok().body(security);
    }
    
    @PutMapping(URIConstants.UPDATE_SECURITY)
    @PreAuthorize(MessageConstants.ADMIN)
    public ResponseEntity<?> updateSecurity(@PathVariable(value = "securityId") Long securityId, @Valid @RequestBody Security security) {
        try {
            log.info("Update Security Request For Security ID: {}", securityId);
            Security updatedSecurity = securityService.updateSecurity(securityId, security);
            return ResponseEntity.ok().body(updatedSecurity);
        }
        catch (ResourceNotFoundException E) {
            log.error(MessageConstants.UPDATE_SECURITY_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    
    @DeleteMapping(URIConstants.DELETE_SECURITY)
    @PreAuthorize(MessageConstants.ADMIN)
    public ResponseEntity<?> deleteSecurity(@PathVariable(value = "securityId") Long securityId) {
    	try {
    		log.info("Delete Security Request For Security ID: {}", securityId);
            Boolean deleteSecurity = securityService.deleteSecurity(securityId);
            String booleanString = deleteSecurity.toString();
            return ResponseEntity.ok().body("Security Deleted: " + booleanString);
    	}
    	catch(ResourceNotFoundException E) {
    		log.error(MessageConstants.DELETE_SECURITY_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    	}
    }

    @GetMapping(URIConstants.GET_SECURITY_BY_USER_ID)
    @PreAuthorize(MessageConstants.USER_ADMIN)
    public ResponseEntity<?> getSecuritiesByUserId(@PathVariable(value = "userId") Long userId) {
        try {
            log.info("Get Securities For User ID: {}", userId);
            List<Security> securities = securityService.getSecuritiesByUserId(userId);
            return ResponseEntity.ok().body(securities);
        }
        catch(ResourceNotFoundException E) {
            log.error(MessageConstants.FETCH_SECURITIES_BY_USER_ID_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping(URIConstants.GET_TRADES_BY_SECURITY_ID)
    @PreAuthorize(MessageConstants.USER_ADMIN)
    public ResponseEntity<?> getTradesBySecurityId(@PathVariable(value = "securityId") Long securityId) {
    	try {
    		log.info("Get Trades For Security ID: {}", securityId);
    		List<Trade> trades = securityService.getTradesBySecurityId(securityId);
    		return ResponseEntity.ok().body(trades);
    	}
    	catch(ResourceNotFoundException E) {
    		log.error(MessageConstants.FETCH_TRADES_FOR_SECURITY_ID_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    	}
    }

    @GetMapping(URIConstants.GET_SECURITY_BY_DATERANGE)
    @PreAuthorize(MessageConstants.USER_ADMIN)
    public ResponseEntity<?> getSecurityByDateRange(@PathVariable(value = "date1") String date1, @PathVariable(value= "date2") String date2) {
        log.info("Get Security By Date Range: From {} To {}", date1, date2);
        List<Security> securityByDateRange = securityService.getSecurityByDateRange(date1, date2);
        return ResponseEntity.ok().body(securityByDateRange);
    }

    @GetMapping(URIConstants.GET_SECURITY_POST_MATURITY)
    @PreAuthorize(MessageConstants.USER_ADMIN)
    public ResponseEntity<?> getSecuritiesPostMaturity() {
        try {
            log.info("Fetch Securities Post Maturity");
            List<Security> securities = securityService.getSecuritiesPostMaturity();
            return ResponseEntity.ok().body(securities);
        }
        catch(ParseException E) {
            log.error(MessageConstants.FETCH_SECURITY_POST_MATURITY_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
