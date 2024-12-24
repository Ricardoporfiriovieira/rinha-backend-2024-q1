package com.porfirio.crebito.transaction.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TransactionTypeEnum {
    CREDIT('c'), DEBIT('d');

    TransactionTypeEnum(Character type) {
        this.type = type;
    }

    private final Character type;

    @JsonValue
    public Character getType() {
        return type;
    }

    @JsonCreator
    public static TransactionTypeEnum fromType(Character type) {
        for (TransactionTypeEnum value : values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + type);
    }
}
