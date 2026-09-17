import java.util.HashMap;
import java.util.Map;

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) { super(message); }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) { super(message); }
}

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) { super(message); }
}

class Account {
    private String accountNo;
    private double balance;

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    // Layer 1: validates amount, then calls updateBalance()
    public void deposit(double amount) throws InvalidAmountException {
        validateAmount(amount);       // may throw -> propagates up automatically
        balance += amount;
        System.out.println("Deposited " + amount + ". New balance = " + balance);
    }

    public void withdraw(double amount) throws InvalidAmountException,
            InsufficientBalanceException {
        validateAmount(amount);       // step 1: may throw InvalidAmountException
        checkSufficientFunds(amount); // step 2: may throw InsufficientBalanceException
        balance -= amount;
        System.out.println("Withdraw " + amount + ". New balance = " + balance);
    }

    // Layer 2 (helper methods) - exceptions thrown HERE propagate up to deposit()/withdraw()
    private void validateAmount(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive, got: " + amount);
        }
    }

    private void checkSufficientFunds(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance. Available: " + balance + ", Requested: " + amount);
        }
    }

    public double getBalance() { return balance; }
}

public class BankingApp {
    static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        accounts.put("AC101", new Account("AC101", 5000));

        try {
            Account acc = findAccount("AC101");
            acc.deposit(2000);
            acc.withdraw(1000);
            acc.withdraw(6000);  // will fail: insufficient balance
        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        } catch (AccountNotFoundException e) {
            System.out.println("Account error: " + e.getMessage());
        }
    }

    static Account findAccount(String accNo) throws AccountNotFoundException {
        if (!accounts.containsKey(accNo)) {
            throw new AccountNotFoundException("No account found with number: " + accNo);
        }
        return accounts.get(accNo);
    }
}