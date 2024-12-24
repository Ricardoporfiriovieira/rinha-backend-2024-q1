package com.porfirio.crebito.customer.api.controller;

import com.porfirio.crebito.customer.api.response.CustomerTransactionResponse;
import com.porfirio.crebito.transaction.api.response.TransactionExtractResponse;
import com.porfirio.crebito.transaction.model.dto.TransactionDto;
import com.porfirio.crebito.transaction.service.TransactionService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class CustomersController {
    private final TransactionService transactionService;


    @PostMapping("/{id}/transacoes")
    public @ResponseBody CustomerTransactionResponse transactionById(@PathVariable @Positive Long id, @RequestBody TransactionDto transactionDto) {
        return transactionService.makeTransaction(id, transactionDto);
    }

    @GetMapping("/{id}/extrato")
    public @ResponseBody TransactionExtractResponse getExtract(@PathVariable @Positive Long id) {
        return transactionService.getExtract(id);
    }
}
