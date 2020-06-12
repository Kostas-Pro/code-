
package store;

abstract class User{
public String name = null;
public String email = null;

//CONSTRUCTOR
    User(String name, String email){
        this.name = name;
        this.email = email;
    }
   
//GET EMAIL
    public String getEmail(){
        return email.toString();
    }
    
//GET NAME + EMAIL
    public String getUserInfo(){
    return name+ "\tEMAIL: "+email;
    }
    
//ABSTRACT GET USER DETAILS    
    public abstract String getUserDetails();
    
//METHOD: TO STRING
    @Override
     public String toString(){
     return getUserInfo()+"\t"+getUserDetails();
    } 
}

