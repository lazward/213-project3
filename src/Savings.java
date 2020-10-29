/**
 *
 * This class represents a 'Savings' type of Account.
 * 
 * @author Aarif Razak ahr58, Julian Lee jl2203
 *
 */

public class Savings extends Account {

    // We need three constants per account type
    private final double fee = 5;
    private final double waiver = 300;
    private final double interest = .0025;
    private final double specialInt = .0035;

    private final int months = 12;

    private boolean isLoyal;

    /**
     * 
     * Helper method to retrive the loyalty status of a Saving Account.
     * 
     * @return boolean, true if the account 'isLoyal', false otherwise.
     * 
     */
    public boolean getLoyal() {
        return isLoyal;
    }

    /**
     * 
     * Helper method to manually set the loyalty status of a saving account.
     * 
     * @param b Boolean value, true if the account was set successfully, false
     *          otherwise.
     * 
     */
    public void setLoyal(boolean b) {
        isLoyal = b;
    }

    /**
     * 
     * Method to calculate the monthly interest of any account.
     * 
     * @return double of the calculated monthly interest.
     * 
     */
    @Override
    public double monthlyInterest() {

        if (getLoyal() == true) {

            return (specialInt / months) * getBalance();
        } else {
            return (interest / months) * getBalance();
        }

    }

    /**
     * 
     * Method to calculate the monthly fee of any account.
     * 
     * @return double of the calculated monthly fee.
     * 
     */
    @Override
    public double monthlyFee() {

        if (getBalance() >= waiver) {
            return 0;
        } else {
            return fee;
        }

    }

    /**
     * 
     * Method to find the special resultant string of any account.
     * 
     * @return string of this specific account.
     * 
     */
    public String specialString() {

        if (isLoyal) {

            return "special Savings account";

        }

        return "";

    }

}
