package com.lawrence.creditcard;

import com.lawrence.creditcard.model.CardTransactions;
import com.lawrence.creditcard.model.Transaction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarTransactionsParser {

    private final static String SEPARATOR = ", ";

    public CardTransactions parse(List<String> fileLines) {
        Map<String, List<Transaction>> cardRecords = new HashMap<>();
        fileLines.stream().forEach(line -> {
            String[] lineArr = line.split(SEPARATOR);
            cardRecords.computeIfAbsent(lineArr[0], k -> new ArrayList<>());

            cardRecords.get(lineArr[0]).add(parseTransaction(lineArr[1], lineArr[2]));
        });
        return new CardTransactions(cardRecords);
    }

    private Transaction parseTransaction(String timeStamp, String amount) {
        return new Transaction(LocalDateTime.parse(timeStamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                new BigDecimal(amount, new MathContext(2)));
    }

}
