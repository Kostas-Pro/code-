
package store;

import java.util.ArrayList;
import java.util.Scanner;


public class ShoppingCart{
ArrayList<ItemOrdered> orderList = new ArrayList<ItemOrdered>();
 
//CONSTRUCTOR
    ShoppingCart() {
    this.orderList = new ArrayList<ItemOrdered>();
    }

//METHOD: ADD ITEMORDERED
    public void addItemOrdered(ItemOrdered itemOrdered){
    orderList.add(itemOrdered);
    }
    
//METHOD: REMOVE ITEMORDERED 
    public void removeItemOrdered(Buyer pelatis, ArrayList<ItemOrdered> orderList, ItemOrdered itemOrdered){
    orderList.remove(itemOrdered);
    showCart(orderList);
    }
    
//METHOD: CHANGE ITEMORDERED QUANTITY    
    public void changeItemOrderedQuantity(ItemOrdered itemOrdered, int quantityChanged){
    itemOrdered.quantity = quantityChanged;
    }
  
//METHOD: SHOW CART    
    public void showCart(ArrayList<ItemOrdered> orderList){
        if (orderList.isEmpty()== true){
            System.out.println("THE CART IS EMPTY");
        }
        else{
        System.out.println("ITEMS IN CART:");
        for(ItemOrdered itemordered : orderList) {
        System.out.println(itemordered.toString());
        }
        }
    }
   
//METHOD: CLEAR CART    
    public void clearCart(ArrayList<ItemOrdered> orderList, Buyer pelatis){
    orderList.clear();
    showCart(orderList);
    }
   
//METHOD: CHECKOUT    
    public ArrayList<Item> checkout(ArrayList<ItemOrdered> orderList, Buyer pelatis, ArrayList<Item> itemsList){
        Scanner scan = new Scanner(System.in);
        double totalPrice = 0;
        double productsprice = 0;
        double metaforika = 0;
        int bonus = 0;
        String category = null;
        showCart(orderList);
        System.out.println("DO YOU WANT TO CHECKOUT?"+"\tENTER YOUR OPTION [Y/N]");
        char checkoutOption = scan.next().charAt(0);
        if (checkoutOption == 'y' && orderList.isEmpty()== false){
            productsprice = calculateNet(orderList,pelatis);
            System.out.println("PRODUCTS PRICE:\t"+productsprice+"$");
            
            bonus = pelatis.awardBonus(productsprice);
            category = pelatis.setbuyerCategory(bonus);
            metaforika = calculateCourierCost(pelatis, productsprice, category);
            
            totalPrice = productsprice + metaforika;
            System.out.println("TOTAL PRICE:\t"+totalPrice+"$");
            System.out.println("BONUS POINTS:\t"+bonus+"\nCATEGORY:\t"+category);
            
            for (ItemOrdered itemordered : orderList){
                for(Item item : itemsList){
                    
                    if (item.id == itemordered.item.id){
                        item.stock = item.stock - itemordered.getQuantity();
                        new Eshop().updateItemStock(item, item.stock);
                    }
                }
            }
            clearCart(orderList, pelatis);
            System.out.println(itemsList);
            
        }
        else{
            System.out.println("EMPTY CART - IT'S TIME TO BUY SOMETHING!");
        }
        
        return itemsList;
    }

//METHOD: CALCULATE NET    
    public double calculateNet(ArrayList<ItemOrdered> orderList, Buyer pelatis){
        double checkoutPrice = 0;
        double couriertaxes = 0;
        for(ItemOrdered itemordered : orderList) {
        checkoutPrice = checkoutPrice + (itemordered.item.price * itemordered.quantity);
        System.out.println("PRODUCT: "+itemordered.item.name+"\tPRICE: "+itemordered.item.price+"\tQUANTITY:"+itemordered.quantity);
        }
    return checkoutPrice;
    }

//METHOD: CALCULATE COURIER COSTS    
    public double calculateCourierCost(Buyer pelatis, double checkoutPrice, String category){
        double metaforika = 0;
        metaforika = ((checkoutPrice * 2) / 100);
        
            if ("GOLD".equals(category)){
            metaforika = 0;
            System.out.println("COURIER TAXES:\t"+metaforika+"$"+"\tCOURIER TAXES NOT EXIST FOR GOLDEN CUSTOMERS");
            }
            else if("SILVER".equals(category)){
            metaforika = ((metaforika * 50) / 100);
            System.out.println("COURIER TAXES:\t"+metaforika+"$"+"\tCOURIER TAXES HAVE 50% DISCOUNT FOR SILVER CUSTOMERS");
            }
            else if(metaforika < 3){
            metaforika = 3;
            System.out.println("COURIER TAXES:\t"+metaforika+"$"+"\tCOURIER TAXES ARE AT LEAST 3$ FOR BRONZE CUSTOMERS");
            }
            else{
            System.out.println("COURIER TAXES:\t"+metaforika+"$");
            }
        
        return metaforika;
    }

//METHOD: GET ITEMORDERED BY ID    
    public ItemOrdered getItemByIdCart(ArrayList<ItemOrdered> orderlist, int itemidselect){
    ItemOrdered foundid = null;
        for(ItemOrdered itemordered : orderlist){ 
            if (itemidselect == itemordered.item.id){ 
            foundid = itemordered;
            break;
            }
        }
        if(foundid != null){
        System.out.println(foundid);
        }
        return foundid;    
    }
}
