package com.ex.model;
import org.bson.types.ObjectId;

/**
 * A User model used for data modeling from and to the mongoDB
 */
public class User {
    private String type;
    private String username;
    private String password;
    private Account accounts;
    private boolean isEmployee;
    private boolean inSession;

    private ObjectId id;

    public User() {}

    /**
     * Constructor that initializes a User
     * Auto generated codes
     * @param type type of User account
     * @param username username of USer
     * @param password password of User
     * @param accounts accounts (Balance and Account Type)
     * @param isEmployee is a Employee
     * @param inSession is in Session
     */
    public User(String type, String username, String password, Account accounts, boolean isEmployee, boolean inSession) {
        this.type = type;
        this.username = username;
        this.password = password;
        this.accounts = accounts;
        this.isEmployee = isEmployee;
        this.inSession = inSession;
    }

    /**
     * Check user if user is already existed
     * @param username given a username to compare
     * @param password given a password to compare
     * @return a boolean value if user exists
     */
    public boolean checkUser(String username, String password) {
        if(username.equalsIgnoreCase(getUsername()) && password.equals(getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Auto generated codes
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccounts() {
        return accounts;
    }

    public void setAccounts(Account accounts) {
        this.accounts = accounts;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public boolean isInSession() {
        return inSession;
    }

    public void setInSession(boolean inSession) {
        this.inSession = inSession;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "type='" + type + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accounts=" + accounts +
                ", isEmployee=" + isEmployee +
                ", inSession=" + inSession +
                ", id=" + id +
                '}';
    }
}
