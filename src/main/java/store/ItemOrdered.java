
package store;

public class ItemOrdered{
public Item item = null;  
public int quantity = 0;

//CONSTRUCTOR
    ItemOrdered(Item item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }
    
//METHOD: GET QUANTITY
    public int getQuantity(){
        return quantity;
    }
    
//METHOD: TOSTRING 
    @Override
     public String toString(){
     return item + "\tQUANTITY:" + quantity;
    }
}