package com.porfirio.crebito.transaction.model.dto;

import com.porfirio.crebito.transaction.model.enums.TransactionTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionDto {

    @NotNull
    @Positive
    private Long valor;

    @NotNull
    private TransactionTypeEnum tipo;

    @NotNull
    @Length(min = 1, max = 10)
    private String descricao;
}
