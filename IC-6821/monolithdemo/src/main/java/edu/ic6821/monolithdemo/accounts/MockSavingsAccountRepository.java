package edu.ic6821.monolithdemo.accounts;

import edu.ic6821.monolithdemo.model.SavingsAccount;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MockSavingsAccountRepository implements SavingsAccountRepository {

    private final Map<String, SavingsAccount> accounts;

    public MockSavingsAccountRepository() {
        this.accounts = new HashMap<>(Map.of(
                "CR1022", new SavingsAccount("CR1022", 1000.0),
                "CR3201", new SavingsAccount("CR3201", 300.0)
        ));
    }

    @Override
    public Optional<SavingsAccount> findByNumber(String number) {
        return Optional.ofNullable(accounts.get(number));
    }

    @Override
    public SavingsAccount save(final SavingsAccount account) {
        accounts.put(account.number(), account);
        return account;
    }
}
