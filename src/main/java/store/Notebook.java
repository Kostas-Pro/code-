
package store;

public class Notebook extends Item{
private int arithmosThematwn = 0;

//CONSTRUCTOR
    Notebook(String name, double price, String description, int stock, int id, int arithmosThematwn){
        super(name, price, description, stock, id);
        this.arithmosThematwn = arithmosThematwn;
    }
    
//METHOD: GET DETAILS
    @Override
       public String getDetails() {
       String sectionsNumber = String.valueOf(arithmosThematwn);
       return sectionsNumber;
    }
}