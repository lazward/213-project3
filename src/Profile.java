/**
 *
 * This class defines the profile of an account holder.
 * 
 * @author Aarif Razak ahr58, Julian Lee jl2203
 *
 */

public class Profile {

    private String fname;
    private String lname;

    /**
     * 
     * Method to retrive the firstName of any given profile.
     * 
     * @return string of this specific Profile's First Name.
     * 
     */
    public String getFirstName() {
        return this.fname;
    }

    /**
     * 
     * Method to find the lastName of any given profile
     * 
     * @return string of this Profile's Last Name.
     * 
     */
    public String getLastName() {
        return this.lname;
    }

    /**
     * 
     * Helper method to set the firstName of a Profile.
     * 
     * @param first String of the name we want to set this Profile's first name to.
     * 
     */
    public void setFirstName(String first) {

        fname = first;

    }

    /**
     * 
     * Helper method to set the lastName of a Profile.
     * 
     * @param last String of the name we want to set this Profile's last name to.
     * 
     */
    public void setLastName(String last) {

        lname = last;

    }
}
