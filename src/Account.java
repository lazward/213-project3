/**
 *This is an abstract class that defines the common features of all account types.
 * @author Aarif Razak ahr58, Julian Lee jl2203
 *
 */

public abstract class Account {

    private Profile holder;
    private double balance;
    private Date dateOpen;

    /**
     * Debit the account's balance by a specified amount.
     * 
     * @param amount The amount to be deducted from the balance.
     */
    public void debit(double amount) {

        balance = balance - amount;

    }

    /**
     * Credit the account's balance by a specified amount.
     * 
     * @param amount The amount to be added to the balance.
     */
    public void credit(double amount) {

        balance = balance + amount;

    }


    /**
     * Convert any set account to a string result.
     * 
     * @return A valid string comprised of all elements of an account.
     */
    @Override
    public String toString() {
        
        return "*" + this.getClass().getName() + "*" + holder.getFirstName() + " " + holder.getLastName() + "* $"
                + String.format("%,.2f", balance) + "*" + dateOpen.toString() + "*" + specialString();

    }

    /**
     * 
     * Abstract method to calculate the monthly interest, dependent on the account type.
     * 
     * @return a double calculating the interest for a set account.
     */
    public abstract double monthlyInterest();

    /**
     * 
     * Abstract method to calculate the monthly fee, dependent on the account type.
     * 
     * @return a double of the monthly fee
     */
    public abstract double monthlyFee();

    // Helper methods for accessing Private Variables

    /**
     * 
     * Helper method to retrive the account holder information variable.
     * 
     * @return a Profile of an account.
     */
    public Profile getHolder() {
        return this.holder;
    }

    /**
     * 
     * Helper method to retrive the balance of an account.
     * 
     * @return a double of the balance of an account.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * 
     * Helper method to retrive the account's open date
     * 
     * @return a Date of opening.
     */
    public Date getOpenDate() {
        return this.dateOpen;

    }

    // Helper setters

    /**
     * 
     * Helper method to set the account holder.
     * 
     * @param profile Profile to set account holder to.
     */
    public void setHolder(Profile profile) {
        this.holder = profile;
    }

     /**
     * 
     * Helper method to set the account balance
     * 
     * @param bal Amount to set balance to.
     */
    public void setBalance(double bal) {
        this.balance = bal;
    }

     /**
     * 
     * Helper method to set the account open date.
     * 
     * @param newDate Date to set account's open date to.
     */
    public void setOpenDate(Date newDate) {
        this.dateOpen = newDate;
    }

    /**
     * 
     * Print the account-specific condition.
     * 
     * @return String depending on account type.
     */
    public abstract String specialString();

}