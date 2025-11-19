package edu.ic6821.monolithdemo.transfers;

public interface TransferService {
    boolean transfer(String fromAccount, String toAccount, Double amount);
}
