
package store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;




public class Main {
    Eshop eshop = new Eshop();
    ShoppingCart shoppingcart1 = new ShoppingCart();
    ShoppingCart shoppingcart2 = new ShoppingCart();
    
    public User initializeOwner(){
        User owner1 = new Owner("GEORGE","owner1@mail.com",true);
        return owner1;
    }
    public ArrayList<User> initializeBuyersList(){
        User buyer1 = new Buyer("LEO","buyer1@mail.com",initializeShoppingCart1());
        User buyer2 = new Buyer("KIM","buyer2@mail.com",initializeShoppingCart2());
        
        eshop.buyersList.add(buyer1);
        eshop.buyersList.add(buyer2);
        
        return eshop.buyersList;
    }
    public ArrayList<Item> initializeItemsList(){
        Item pen1 = new Pen("PEN-1",10,"PERFECT PEN-1",2,1,"BLACK",0.10);
        Item pen2 = new Pen("PEN-2",10,"PERFECT PEN-2",2,2,"ROSE",0.10);
        Item pen3 = new Pen("PEN-3",20,"PERFECT PEN-3",2,3,"WHITE",0.10);
        Item pencil1 = new Pencil("PENCIL-1",20,"PERFECT PEN-1",2,4,"H",0.10);
        Item pencil2 = new Pencil("PENCIL-2",30,"PERFECT PEN-2",2,5,"B",0.10);
        Item pencil3 = new Pencil("PENCIL-3",30,"PERFECT PEN-3",2,6,"HB",0.10);
        Item notebook1 = new Notebook("NOTEBOOK-1",40,"PERFECT NOTEBOOK-1",3,7,10);
        Item notebook2 = new Notebook("NOTEBOOK-2",40,"PERFECT NOTEBOOK-2",3,8,10);
        Item notebook3 = new Notebook("NOTEBOOK-3",10,"PERFECT NOTEBOOK-3",3,9,10);
        Item paper1 = new Paper("PAPER-1",60,"PERFECT PAPER-1",5,10,2,105);
        Item paper2 = new Paper("PAPER-2",60,"PERFECT PAPER-2",5,11,2,105);
        Item paper3 = new Paper("PAPER-3",50,"PERFECT PAPER-3",5,12,2,105);
        Item paper4 = new Paper("PAPER-4",80,"PERFECT PAPER-4",5,13,2,105);
        
        eshop.itemsList.add(pen1);
        eshop.itemsList.add(pen2);
        eshop.itemsList.add(pen3);
        eshop.itemsList.add(pencil1);
        eshop.itemsList.add(pencil2);
        eshop.itemsList.add(pencil3);
        eshop.itemsList.add(notebook1);
        eshop.itemsList.add(notebook2);
        eshop.itemsList.add(notebook3);
        eshop.itemsList.add(paper1);
        eshop.itemsList.add(paper2);
        eshop.itemsList.add(paper3);
        eshop.itemsList.add(paper4);
        
       
        return eshop.itemsList;
        }
   
    public ShoppingCart initializeShoppingCart1(){
        ArrayList<Item> products = initializeItemsList();
        ItemOrdered itemOrdered2 = new ItemOrdered(products.get(3), 2);
        ItemOrdered itemOrdered3 = new ItemOrdered(products.get(9), 2);
        shoppingcart1.addItemOrdered(itemOrdered2);
        shoppingcart1.addItemOrdered(itemOrdered3);
        return shoppingcart1;
    }
    public ShoppingCart initializeShoppingCart2(){
        ArrayList<Item> products = initializeItemsList();
        ItemOrdered itemOrdered2 = new ItemOrdered(products.get(8), 3);
        ItemOrdered itemOrdered3 = new ItemOrdered(products.get(5), 2);
        shoppingcart2.addItemOrdered(itemOrdered2);
        shoppingcart2.addItemOrdered(itemOrdered3);
        return shoppingcart2;
    }
    
    
    public static void main(String[] args) {
        
       User owner = new Main().initializeOwner();
       ArrayList<User> buyerslist = new Main().initializeBuyersList();
       ArrayList<Item> itemsList = new Main().initializeItemsList();
        
        Menu menu = new Menu();
        menu.welcomeScreen(owner,itemsList,buyerslist);
            
        }
    
}
