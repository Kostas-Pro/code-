
package store;

public class Paper extends Item{
private int weight = 0;
private int pages = 0;

//CONSTRUCTOR
   Paper(String name, double price, String description, int stock, int id, int weight, int pages){
       super(name, price, description, stock, id);
       this.weight = weight;
       this.pages = pages;
       }
   
//METHOD: GET DETAILS
       @Override
       public String getDetails() {
       String varos = String.valueOf(weight);
       String selides = String.valueOf(pages);
       return varos+selides;
    }
}