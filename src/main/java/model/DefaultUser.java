package model;

/**
 * Users are the ones using the software.
 */

public class DefaultUser {
    public String name;
    public String password;
    private Boolean admin;
    private Boolean firstLogin;
    private long uid;

    /**
     * constructs a user from the given params.
     *
     * @param Name
     * @param Password
     * @param Admin
     * @param firstLogin
     */

    public DefaultUser(String Name, String Password, Boolean Admin, Boolean firstLogin) {
        this.name = Name;
        this.password = Password;
        this.admin = Admin;
        this.firstLogin = firstLogin;
    }

    /**
     * constructs a user from the given params.
     *
     * @param Name
     * @param Password
     * @param Admin
     * @param firstLogin
     */

    public DefaultUser(long uid, String Name, String Password, Boolean Admin, Boolean firstLogin) {
        this.uid = uid;
        this.name = Name;
        this.password = Password;
        this.admin = Admin;
        this.firstLogin = firstLogin;
    }

    /**
     * @return DefaultUser-Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return DefaultUser-Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return whether this is the users first login
     */
    public Boolean getFirstLogin() {
        return firstLogin;
    }

    /**
     * @param firstLogin
     */
    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    /**
     * @return whether this is user is an admin or not
     */
    public Boolean getAdmin() {
        return admin;
    }

    /**
     * @return whether this is user-id is an admin or not
     */
    public long getUid() {
        return uid;
    }
}
