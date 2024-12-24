package com.porfirio.crebito.transaction.model.entity;

import com.porfirio.crebito.customer.model.entity.Customer;
import com.porfirio.crebito.transaction.model.enums.TransactionTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Table(name = "transactions")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    private Long value;

    private TransactionTypeEnum type;

    @CreationTimestamp
    private Date timestamp;

    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
