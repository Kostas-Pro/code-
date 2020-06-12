
package store;

abstract class Item{
public String name = null;
public double price = 0;
public String description = null;
public int stock = 0;
public int id = 0;

//CONSTRUCTOR
    Item(String name, double price, String description, int stock, int id){
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.id = id;
    }
    
//GET ID
    public int getId(){
        return id;
    }
      
//GET BASIC INFO    
    public String getBasicInfo(){
    String timi = String.valueOf(price);
    String apothema = String.valueOf(stock);
    String kwdikos = String.valueOf(id);
    return "ID:"+kwdikos+"    "+"NAME:"+name+"    "+"PRICE:"+timi+"    "+"DESCRIPTION:"+description+"    "+"STOCK:"+apothema+"     ";
    }
    
//ABSTRACT GET DETAILS
    public abstract String getDetails();
    
//METHOD: TOSTRING
    @Override
     public String toString(){
     return ""+getBasicInfo()+" DETAILS "+getDetails();
    } 
}

