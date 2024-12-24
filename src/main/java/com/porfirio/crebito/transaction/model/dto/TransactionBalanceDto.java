package com.porfirio.crebito.transaction.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TransactionBalanceDto {
    private Long total;
    private Date data_extrato;
    private Long limite;
}
