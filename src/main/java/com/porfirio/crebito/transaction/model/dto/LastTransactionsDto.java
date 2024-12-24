package com.porfirio.crebito.transaction.model.dto;

import com.porfirio.crebito.transaction.model.enums.TransactionTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class LastTransactionsDto {
    private Long valor;
    private TransactionTypeEnum tipo;
    private String descricao;
    private Date realizada_em;
}
