package com.porfirio.crebito.customer.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerTransactionResponse {
    private Long limite;
    private Long saldo;
}
