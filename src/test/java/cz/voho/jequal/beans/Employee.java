package cz.voho.jequal.beans;

/**
 * Created by vojta on 01/02/16.
 */
public class Employee extends Person {
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }
}
