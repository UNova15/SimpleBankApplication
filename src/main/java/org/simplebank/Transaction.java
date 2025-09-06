package org.simplebank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

class Transaction implements Serializable {

    private final UUID operationId;
    private final LocalDateTime timeOperations;
    private final UUID fromAccountId;
    private final UUID toAccountId;
    private final int value;


    Transaction(UUID fromAccountId, UUID toAccountId, int value) {

        this.value = value;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        timeOperations = LocalDateTime.now();
        operationId = UUID.randomUUID();

    }

    @Override
    public String toString(){
        return operationId.toString()+"\t"+timeOperations.toString()+"\t"+value;
    }
}
