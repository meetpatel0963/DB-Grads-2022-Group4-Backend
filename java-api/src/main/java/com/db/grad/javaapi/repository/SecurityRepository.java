package com.db.grad.javaapi.repository;

import com.db.grad.javaapi.model.security.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityRepository extends JpaRepository<Security, Long> {

    @Query("SELECT s FROM Security s WHERE s.maturityDate BETWEEN :from AND :to")
    public List<Security> getSecurityByDateRange(String from, String to);

    @Query("SELECT s FROM Security s WHERE s.maturityDate < :date")
    public List<Security> getSecuritiesPostMaturity(String date);

}
