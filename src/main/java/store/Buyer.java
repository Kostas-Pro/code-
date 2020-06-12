
package store;

import java.util.ArrayList;

public class Buyer extends User{
public int bonus = 0;
public String buyerCategory = "BRONZE";
ShoppingCart shoppingcart = new ShoppingCart();

//CONSTRUCTOR
    Buyer(String name, String email, ShoppingCart shoppingcart){
        super(name, email);
        this.bonus = bonus;
        this.buyerCategory = buyerCategory;
        this.shoppingcart = shoppingcart;
    }

//GET SHOPPING CART 
    public ArrayList<ItemOrdered> getMyShoppingCart(){
        return shoppingcart.orderList;
    }
    
//METHOD: AWARD BONUS
    public int awardBonus(double totalPrice){
    bonus = (int) ((totalPrice * 10) / 100);
    return bonus;
    }
    
//METHOD: SET BUYER CATEGORY
    public String setbuyerCategory(int bonus){
        if (bonus <= 100){
            buyerCategory = "BRONZE";
        }
        else if(bonus <= 200){
            buyerCategory = "SILVER";
        }
        else{
            buyerCategory = "GOLD";
        } 
    return buyerCategory;
    }
    
//METHOD: PLACE ORDER
    public void placeOrder(Item item, int quantity){
    ItemOrdered myItemOrdered = new ItemOrdered(item, quantity);
    shoppingcart.addItemOrdered(myItemOrdered);
    }
    
//METHOD: GET USER DETAILS
    @Override
       public String getUserDetails() {
       String pontoi = String.valueOf(bonus);
       return "BONUS: "+pontoi+"\tCATEGORY: "+buyerCategory;
    }
}