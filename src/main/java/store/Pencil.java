
package store;

public class Pencil extends Item{
/*private String[] type = {"H", "B", "HB"};*/
private String type = null;
private double tipSize = 0;

//CONSTRUCTOR
   Pencil(String name, double price, String description, int stock, int id, /*String[]*/String type, double tipSize){
       super(name, price, description, stock, id);
       this.type = type;
       this.tipSize = tipSize;
   }
   
//METHOD: GET DETAILS
   @Override
       public String getDetails() {
       String tipsize = String.valueOf(tipSize);
       return tipsize+type;
    }
}