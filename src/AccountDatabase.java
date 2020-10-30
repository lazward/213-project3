/**
 * This is an array-based container class with an initial capacity of 5. It will
 * automatically grow the capacity by 5 if the database is full.
 * 
 * @author Aarif Razak ahr58, Julian Lee jl2203
 *
 */

public class AccountDatabase {

    private Account[] accounts;
    private int size;

    /**
     * 
     * Constuctor to create an empty Account Database.
     * 
     */
    public AccountDatabase() {
        accounts = new Account[5];
        size = 0;
    }

    /**
     * 
     * Method to find if any account is present in an Account Database.
     * 
     * @param account Account to find.
     * @return an int indicating the index inside the AccountDatabase where the
     *         account is located.
     */
    private int find(Account account) {
        // Search through dataase and return respective index of account
        for (int i = 0; i < accounts.length - 1; i++) {

            if (accounts[i] == null) {

                continue;

            }

            if (account.getHolder().getFirstName().equals(accounts[i].getHolder().getFirstName())) {
                if (account.getHolder().getLastName().equals(accounts[i].getHolder().getLastName())) {

                    String str = account.getClass().getName();

                    // if they match the account type
                    if (str.equals(accounts[i].getClass().getName())) {
                        return i;

                    }
                }

            }
        }

        return -1;

    }

    /**
     * 
     * Method to grow any Account Database when it has hit its max size.
     * 
     */
    private void grow() {

        final int increment = 5;

        Account[] newAccounts = new Account[accounts.length + increment];

        for (int i = 0; i < size; i++) {

            newAccounts[i] = accounts[i];

        }

        accounts = newAccounts;

    }

    /**
     * 
     * Method to add an account to the Account Database specified.
     * 
     * @param account Account to add.
     * @return True if the account was added successfully or false if it wasn't.
     */
    public boolean add(Account account) {
        // Check Capacity and grow if needed
        int index = find(account);
        // if Account already exists
        if (index != -1) {
            return false;
        }

        if (size == accounts.length) {
            grow();
        }

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                break;
            }
        }

        size++;

        return true;

    }

    /**
     * 
     * Method to remove an account from the specified Account Database.
     * 
     * @param account Account to remove.
     * @return true if the account was removed successfully, false if wasn't.
     */
    public boolean remove(Account account) {

        int index = find(account);

        if (index == -1) { // Account doesn't exist

            return false;

        }

        accounts[index] = null; // will need to add nullcheck in the print

        size--;

        return true;

    }

    /**
     * 
     * Method to deposit a set amount of money into a specific account found in the
     * Account Database.
     * 
     * @param account Account to find.
     * @param amount  Amount to deposit.
     * @return True if the amount was deposited successfully, false otherwise.
     */
    public boolean deposit(Account account, double amount) {
        // find + credit

        int index = find(account);

        if (index == -1) { // If account doeesn't exist

            return false;

        }

        accounts[index].credit(amount);

        return true;

    }

    /**
     * 
     * Method to withdraw a set amount of moeny from a specific account inside the
     * Account Database.
     * 
     * @param account Account to withdraw from.
     * @param amount  Amount to withdraw from the account.
     * @return Returns an integer code, -1 if the account wasn't found in the
     *         database, 1 if there was insufficent funds, or 0 for a successful
     *         transaction.
     */
    public int withdrawal(Account account, double amount) {

        int index = find(account);

        if (index == -1) { // If account doesn't exist

            return -1;

        }

        if (accounts[index].getBalance() - amount <= 0) { // Insufficient funds

            return 1;

        }

        accounts[index].debit(amount); // Transaction successful

        return 0;

    }

    /**
     * 
     * Method to sort the given Account Database based on the open dates of
     * accounts.
     * 
     */
    private void sortByDateOpen() {

        Account temp;

        for (int i = 0; i < size - 1; i++) {
            if (accounts[i] == null) {

                continue;

            }

            for (int j = i + 1; j < size; j++) {

                if (accounts[j] == null) {

                    continue;

                }

                if (accounts[i].getOpenDate().compareTo(accounts[j].getOpenDate()) == 1) { // compares i to j

                    temp = accounts[i];
                    accounts[i] = accounts[j];
                    accounts[j] = temp;
                }

            }
        }

    }

    /**
     * 
     * Method to sort the given Account Database based on the last name of account
     * holders.
     * 
     */
    private void sortByLastName() {

        Account temp;

        for (int i = 0; i < size - 1; i++) {
            if (accounts[i] == null) {

                continue;

            }

            for (int k = i + 1; k < size; k++) {
                if (accounts[k] == null) {

                    continue;

                }
                if (accounts[i].getHolder().getLastName()
                        .compareToIgnoreCase(accounts[k].getHolder().getLastName()) > 0) {
                    temp = accounts[i];
                    accounts[i] = accounts[k];
                    accounts[k] = temp;
                }
            }
        }

    }

    /**
     * 
     * Method to print the given Account Database based on the open dates of
     * accounts.
     * 
     */
    public String printByDateOpen() {

        sortByDateOpen();

        String output = "" ;

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {

                continue;

            }

            output = output.concat(accounts[i].toString() + "\n");
            double interest = accounts[i].monthlyInterest();
            output = output.concat("-interest: $ " + String.format("%,.2f", interest) + "\n");
            double fee = accounts[i].monthlyFee();
            output = output.concat("-fee: $ " + String.format("%,.2f", fee) + "\n");
            accounts[i].setBalance(accounts[i].getBalance() + interest - fee);
            output = output.concat("-new balance: $ " + String.format("%,.2f", accounts[i].getBalance()) + "\n");

        }

        return output ;

    }

    /**
     * 
     * Method to print the given Account Database based on the last names of account
     * holders.
     * 
     */
    public String printByLastName() {

        sortByLastName();

        String output = "";
        
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {

                continue;

            }
            output = output.concat(accounts[i].toString() + "\n");
            double interest = accounts[i].monthlyInterest();
            output = output.concat(("-interest: $ " + String.format("%,.2f", interest)) + "\n") ;
            double fee = accounts[i].monthlyFee();
            output = output.concat("-fee: $ " + String.format("%,.2f", fee) + "\n");
            accounts[i].setBalance(accounts[i].getBalance() + interest - fee);
            output = output.concat("-new balance: $ " + String.format("%,.2f", accounts[i].getBalance()) + "\n");
        }

        return output ;

    }

    /**
     * 
     * Method to print the account using our toString method.
     * 
     */
    public String printAccounts() {

        String output = "" ;

        for (int i = 0; i < size; i++) {
            if (accounts[i] == null) {
                continue;
            }
            output = output.concat(accounts[i].toString() + "\n");
        }

        return output ;

    }

    /**
     * 
     * Helper method to retrive the size of an Account Database.
     * 
     * @return int of the current Account Database size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 
     * Helper method to return the Array of Accounts present in an Account Database.
     * 
     * @return an Account[] of accounts inside any set Database.
     */
    public Account[] getAccounts() {
        return this.accounts;
    }

    /**
     * 
     * Helper method for incrementing withdrawals inside any MoneyMarket account.
     * 
     * @param m Money Market account whose withdrawals we need to increment.
     */
    public void incrementWithdrawals(MoneyMarket m) {

        int index = find(m);

        MoneyMarket temp = (MoneyMarket) accounts[index];
        temp.setWithdrawals(temp.getWithdrawals() + 1);
        accounts[index] = temp;

    }

}