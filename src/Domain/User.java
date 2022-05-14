package Domain;

// Class that represents a user
public class User {

    private String userName;
    private String password;

    // Constructor
    public User (String iuserName, String ipassword) {
        userName=iuserName;
        password=ipassword;
    }
    public void setuserName (String iuserName){
        userName=iuserName;
    }
    public void setpassword (String ipassword){
        password=ipassword;
    }
    public String getuserName () {return userName;}
    public String getpassword () {return password;}
}