package com.porfirio.crebito.transaction.service;

import com.porfirio.crebito.customer.api.exception.CustomerNotFoundException;
import com.porfirio.crebito.customer.api.response.CustomerTransactionResponse;
import com.porfirio.crebito.customer.model.entity.Customer;
import com.porfirio.crebito.customer.service.CustomerService;
import com.porfirio.crebito.transaction.api.exception.NotValidTransactionException;
import com.porfirio.crebito.transaction.api.response.TransactionExtractResponse;
import com.porfirio.crebito.transaction.model.dto.LastTransactionsDto;
import com.porfirio.crebito.transaction.model.dto.TransactionBalanceDto;
import com.porfirio.crebito.transaction.model.dto.TransactionDto;
import com.porfirio.crebito.transaction.model.entity.Transaction;
import com.porfirio.crebito.transaction.model.enums.TransactionTypeEnum;
import com.porfirio.crebito.transaction.model.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;
    private final CustomerService customerService;

    public CustomerTransactionResponse makeTransaction(Long userId, TransactionDto transaction) {
        Customer customer = customerService.findById(userId).orElseThrow(() -> new CustomerNotFoundException(userId));

        if (transaction.getTipo().equals(TransactionTypeEnum.DEBIT)) {
            if (transaction.getValor() > customer.getBalance() + customer.getCreditLimit()) {
                throw new NotValidTransactionException("The customer " + userId + " Dont have enough credit");
            }

            customer.setBalance(customer.getBalance() - transaction.getValor());
            Customer savedcustomer = customerService.save(customer);
            saveTransactionDto(transaction, customer);
            return CustomerTransactionResponse.builder()
                    .limite(savedcustomer.getCreditLimit())
                    .saldo(savedcustomer.getBalance())
                    .build();
        }

        customer.setBalance(customer.getBalance() + transaction.getValor());
        Customer savedcustomer = customerService.save(customer);
        saveTransactionDto(transaction, customer);
        return CustomerTransactionResponse.builder()
                .limite(savedcustomer.getCreditLimit())
                .saldo(savedcustomer.getBalance())
                .build();

    }

    public TransactionExtractResponse getExtract(Long userId) {
        Customer customer = customerService.findById(userId).orElseThrow(() -> new CustomerNotFoundException(userId));
        List<Transaction> transactions = getTransactionsByCustomer(customer);
        TransactionBalanceDto saldo = TransactionBalanceDto.builder()
                .total(customer.getBalance())
                .data_extrato(Date.from(Instant.now()))
                .limite(customer.getCreditLimit())
                .build();

        List<LastTransactionsDto> lastTransactions = new ArrayList<>();

        transactions.forEach(transaction -> {
            LastTransactionsDto.builder()
                    .valor(transaction.getValue())
                    .tipo(transaction.getType())
                    .descricao(transaction.getDescription())
                    .realizada_em(transaction.getTimestamp())
                    .build();

            lastTransactions.add(
                    LastTransactionsDto.builder()
                        .valor(transaction.getValue())
                        .tipo(transaction.getType())
                        .descricao(transaction.getDescription())
                        .realizada_em(transaction.getTimestamp())
                    .build()
            );
        });

        return TransactionExtractResponse.builder()
                .saldo(saldo)
                .ultimas_transacoes(lastTransactions)
                .build();
    }

    public List<Transaction> getTransactionsByCustomer(Customer customer) {
        return repository.getTransactionByCustomer(customer);
    }

    public void saveTransactionDto(TransactionDto transactionDto, Customer customer) {
        repository.save(Transaction.builder()
                        .value(transactionDto.getValor())
                        .type(transactionDto.getTipo())
                        .customer(customer)
                        .description(transactionDto.getDescricao())
                .build());
    }
}
