package com.lawrence.creditcard.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Transaction {

    private LocalDateTime createdDateTime;

    private BigDecimal amount;

}
