package edu.ic6821.monolithdemo.accounts;

import edu.ic6821.monolithdemo.model.SavingsAccount;

import java.util.Optional;

public interface SavingsAccountRepository {
    Optional<SavingsAccount> findByNumber(String number);
    SavingsAccount save(SavingsAccount account);
}
