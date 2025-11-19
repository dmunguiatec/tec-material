package edu.ic6821.monolithdemo.transfers;

import edu.ic6821.monolithdemo.accounts.SavingsAccountRepository;
import edu.ic6821.monolithdemo.model.SavingsAccount;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InternalTransferService implements TransferService {

    private final SavingsAccountRepository accountRepository;

    public InternalTransferService(final SavingsAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean transfer(final String fromAccount, final String toAccount, final Double amount) {
        final Optional<SavingsAccount> from = this.accountRepository.findByNumber(fromAccount);
        if (from.isEmpty()) {
            return false;
        }

        final Optional<SavingsAccount> to = this.accountRepository.findByNumber(toAccount);
        if (to.isEmpty()) {
            return false;
        }

        if (amount > from.get().balance()) {
            return false;
        }

        this.accountRepository.save(new SavingsAccount(from.get().number(), from.get().balance() - amount));
        this.accountRepository.save(new SavingsAccount(to.get().number(), to.get().balance() + amount));

        return true;
    }
}
