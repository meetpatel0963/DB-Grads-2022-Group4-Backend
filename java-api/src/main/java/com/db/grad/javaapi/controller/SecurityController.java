package com.db.grad.javaapi.controller;


import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.grad.javaapi.constants.ErrorConstants;
import com.db.grad.javaapi.constants.URIConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.ErrorResponse;
import com.db.grad.javaapi.model.security.Security;
import com.db.grad.javaapi.model.trade.Trade;
import com.db.grad.javaapi.service.SecurityService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(URIConstants.API_V1)
@Slf4j
public class SecurityController {

	@Autowired
    private SecurityService securityService;

	
	@GetMapping(URIConstants.GET_ALL_SECURITY)
	public ResponseEntity<?> getAllSecurity(){
		try {
			log.info("Fetch All Securities");
			List<Security> securities = securityService.getAllSecurity();
			return ResponseEntity.ok().body(securities);
		}
		catch(ResourceNotFoundException E){
			log.error(ErrorConstants.NO_SECURITIES_FOUND + E.getMessage());
			ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		
	}
	
    @GetMapping(URIConstants.GET_SECURITY_BY_ID)
    public ResponseEntity<?> getSecurityById(@PathVariable(value = "securityId") Long securityId) {
        try {
            log.info("Fetch Security Request For Security ID: {}", securityId);
            Security security = securityService.getSecurityById(securityId);
            return ResponseEntity.ok().body(security);
        }
        catch (ResourceNotFoundException E) {
            log.error(ErrorConstants.FETCH_SECURITY_BY_ID_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    
    @PostMapping(URIConstants.ADD_SECURITY)
    public ResponseEntity<?> addSecurity(@Valid @RequestBody Security security) {
        log.info("Add Security Request For Security: {}", security);
        securityService.addSecurity(security);
        return ResponseEntity.ok().body(security);
    }
    
    @PutMapping(URIConstants.UPDATE_SECURITY)
    public ResponseEntity<?> updateSecurity(@PathVariable(value = "securityId") Long securityId, @Valid @RequestBody Security security) throws ResourceNotFoundException {
        try {
            log.info("Update Security Request For Security ID: {}", securityId);
            Security updatedSecurity = securityService.updateSecurity(securityId, security);
            return ResponseEntity.ok().body(updatedSecurity);
        }
        catch (ResourceNotFoundException E) {
            log.error(ErrorConstants.UPDATE_SECURITY_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    
    @DeleteMapping(URIConstants.DELETE_SECURITY)
    public ResponseEntity<?> deleteSecurity(@PathVariable(value = "securityId") Long securityId)
    	    throws ResourceNotFoundException {
    	try {
    		log.info("Delete Security Request For Security ID: {}", securityId);
            Boolean deleteSecurity = securityService.deleteSecurity(securityId);
            String booleanString = deleteSecurity.toString();
            return ResponseEntity.ok().body("Security Deleted: " + booleanString);
    	}
    	catch(ResourceNotFoundException E) {
    		log.error(ErrorConstants.DELETE_SECURITY_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    	}
    }
    
    @GetMapping(URIConstants.GET_SECURITY_BY_DATERANGE)
    public ResponseEntity<?> getSecurityByDateRange(@PathVariable(value = "date1") String date1, @PathVariable(value= "date2") String date2)
    		throws ResourceNotFoundException, ParseException {
    	try {
    		log.info("Delete Security Request For Security ID: {}");
    		List<Security> securityByDaterange = securityService.getSecurityByDaterange(date1, date2);
    		return ResponseEntity.ok().body(securityByDaterange);
    	}
    	catch(ResourceNotFoundException E) {
    		log.error(ErrorConstants.FETCH_SECURITY_BY_DATERANGE_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    	}
    	catch(ParseException E) {
    		log.error(ErrorConstants.FETCH_SECURITY_BY_DATERANGE_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    	}
    }
}
