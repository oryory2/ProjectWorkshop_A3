package Domain;

public class User {

    private String userName;
    private String password;

    // constructors / standard setters / getters
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