package com.besafx.app.service;

import com.besafx.app.entity.*;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public interface BankTransactionService extends PagingAndSortingRepository<BankTransaction, Long>, JpaSpecificationExecutor<BankTransaction> {

    BankTransaction findTopByOrderByCodeDesc();

    BankTransaction findByCodeAndIdIsNot(Long code, Long id);

    List<BankTransaction> findBySeller(Seller seller);

    List<BankTransaction> findByBank(Bank bank);

    <T> List<T> findByTransactionTypeIn(List<TransactionType> transactionTypes, Class<T> type);

    <T> List<T> findByDateBetweenOrTransactionTypeCodeIn(@Temporal(TemporalType.TIMESTAMP) Date startDate, @Temporal(TemporalType.TIMESTAMP) Date endDate, List<String> transactionTypeCodes, Class<T> type);

    <T> List<T> findBySellerAndTransactionTypeIn(Seller seller, List<TransactionType> transactionTypes, Class<T> type);

    <T> List<T> findByBankAndTransactionTypeIn(Bank bank, List<TransactionType> transactionTypes, Class<T> type);
}
