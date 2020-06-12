
package store;

public class Pen extends Item{
private String color = null;
private double tipSize = 0;

//CONSTRUCTOR
   Pen(String name, double price, String description, int stock, int id, String color, double tipSize){
       super(name, price, description, stock, id);
       this.color = color;
       this.tipSize = tipSize;
   }
   
//METHOD: GET DETAILS
  @Override
       public String getDetails() {
       String tipsize = String.valueOf(tipSize);
       return tipsize;
    }
}