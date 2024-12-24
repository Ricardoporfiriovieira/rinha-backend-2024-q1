package com.porfirio.crebito.transaction.api.response;

import com.porfirio.crebito.transaction.model.dto.LastTransactionsDto;
import com.porfirio.crebito.transaction.model.dto.TransactionBalanceDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TransactionExtractResponse {
    private TransactionBalanceDto saldo;
    private List<LastTransactionsDto> ultimas_transacoes;
}
