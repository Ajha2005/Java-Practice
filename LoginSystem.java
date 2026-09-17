import java.util.Scanner;

class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String message) { super(message); }
}

class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) { super(message); }
}

class AccountLockedException extends Exception {
    public AccountLockedException(String message) { super(message); }
}

public class LoginSystem {
    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "pass123";
    private static final int MAX_ATTEMPTS = 3;

    private static int failedAttempts = 0;
    private static boolean locked = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (failedAttempts < MAX_ATTEMPTS) {
            try {
                if (locked) {
                    throw new AccountLockedException("Account is locked. Contact admin.");
                }

                System.out.print("Enter username: ");
                String username = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();

                validateLogin(username, password);

                System.out.println("Login successful! Welcome, " + username);
                break; // exit loop on success

            } catch (InvalidUsernameException e) {
                System.out.println("Username error: " + e.getMessage());
                failedAttempts++;
            } catch (InvalidPasswordException e) {
                System.out.println("Password error: " + e.getMessage());
                failedAttempts++;
            } catch (AccountLockedException e) {
                System.out.println("Locked: " + e.getMessage());
                break; // no retry once locked
            } finally {
                System.out.println("Attempt number: " + failedAttempts + " of " + MAX_ATTEMPTS);

                if (failedAttempts >= MAX_ATTEMPTS) {
                    locked = true;
                    System.out.println("Maximum attempts reached. Account locked.");
                }
            }
        }

        sc.close();
    }

    static void validateLogin(String username, String password)
            throws InvalidUsernameException, InvalidPasswordException {
        if (!username.equals(CORRECT_USERNAME)) {
            throw new InvalidUsernameException("Username '" + username + "' not recognised.");
        }

        if (!password.equals(CORRECT_PASSWORD)) {
            throw new InvalidPasswordException("Incorrect password.");
        }
    }
}