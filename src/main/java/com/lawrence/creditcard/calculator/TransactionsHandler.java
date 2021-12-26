package com.lawrence.creditcard.calculator;

import com.lawrence.creditcard.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionsHandler {

    public List<List<Transaction>> groupTransactions(List<Transaction> transactions) {
        List<List<Transaction>> groupedTransactionList = new ArrayList<>();
        groupedTransactionList.add(
                transactions.stream()
                        .map(Arrays::asList)
                        .reduce(new ArrayList<>(), (p, c) -> {
                            if (!p.isEmpty()) {
                                LocalDateTime fistEdge = p.get(0).getCreatedDateTime().plusHours(24);
                                if (!fistEdge.isAfter(c.get(0).getCreatedDateTime())) {
                                    groupedTransactionList.add(c);
                                    LocalDateTime lastEdge = c.get(0).getCreatedDateTime().minusHours(24);
                                    p = new ArrayList<>(p.stream()
                                            .filter(t -> lastEdge.isBefore(t.getCreatedDateTime()))
                                            .collect(Collectors.toList()));

                                }
                            }
                            p.addAll(c);
                            return p;
                        })
        );
        return groupedTransactionList;
    }

    public List<BigDecimal> calculateAmount(List<List<Transaction>> transactionsList) {
        return transactionsList.stream().map(list ->
                        list.stream()
                                .map(Transaction::getAmount)
                                .reduce(BigDecimal::add)
                                .get()
                )
                .collect(Collectors.toList());
    }
}
