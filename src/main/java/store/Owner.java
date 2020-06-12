
package store;

public class  Owner extends User{
private boolean isAdmin = true; 
    
//CONSTRUCTOR
    Owner(String name, String email, boolean isAdmin){
        super(name, email);
        this.isAdmin = isAdmin;
    }
    
//METHOD: GET USER DETAILS
    @Override
       public String getUserDetails() {
       if (isAdmin == true){
       return "ADMIN";
       }
       else return "";
    }
}