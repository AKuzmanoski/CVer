package model;

/**
 * This is the class which drives the whole logic of this system. Its name tells that it represents a personal
 * resume with all its characteristics inside. It will hold personal information like name of the owner, address,
 * telephone number, its skills, education status and previous ,and current work.
 * Also it will hold many other connections with other Cvs and companies.
 *
 * @author CVerTeam
 * @version 1.0
 * @since 1/17/2016
 */
public class Cv extends BaseEntity {
    private String account;
    private String firstName;
    private String lastName;

    public Cv(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Cv(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Cv(String account) {
        this.account = account;
    }

    public Cv(String firstName, String lastName, String account) {
        this(firstName, lastName);
        this.account = account;
    }

    public Cv(Long id, String firstName, String lastName, String account) {
        this(id, firstName, lastName);
        this.account = account;
    }

    public Cv() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
