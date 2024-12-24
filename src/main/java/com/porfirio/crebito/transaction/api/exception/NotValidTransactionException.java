package com.porfirio.crebito.transaction.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class NotValidTransactionException extends RuntimeException {
  public NotValidTransactionException(String message) {
    super(message);
  }

  public NotValidTransactionException() {
      super("This transaction is not valid");
  }
}
