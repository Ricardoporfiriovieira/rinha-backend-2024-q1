package com.porfirio.crebito.transaction.model.repository;

import com.porfirio.crebito.customer.model.entity.Customer;
import com.porfirio.crebito.transaction.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> getTransactionByCustomer(Customer customer);
}
