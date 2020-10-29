/**
 *
 * This class represents a 'checking' account type.
 * 
 * @author Aarif Razak ahr58, Julian Lee jl2203
 *
 */

public class Checking extends Account {

    private boolean directDeposit;

    // We need three constants per account type
    private final double fee = 25;
    private final double waiver = 1500;
    private final double interest = .0005;
    private final int months = 12;

    /**
     * 
     * Method to return the Checking-Specific direct deposit status.
     * 
     * @return boolean containing True if the account has direct deposit, false
     *         otherwise.
     * 
     */
    public boolean getDirectDeposit() {
        return directDeposit;
    }

    /**
     * 
     * Method to set the Checking-Specific direct deposit status of a checking
     * account.
     * 
     * @param b Boolean to determine whether or not the account is a direct deposit
     *          or not.
     * 
     */
    public void setDirectDeposit(boolean b) {
        directDeposit = b;
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

        return ((interest / months) * getBalance());

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
        if (getDirectDeposit() == true || getBalance() >= waiver) {
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

        if (directDeposit) {

            return "direct deposit account*";

        }

        return "";

    }

}
