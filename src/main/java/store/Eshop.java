
package store;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.lang.Exception;

public class Eshop{
public String name = null;
public Owner owner = null;
ArrayList<Item> itemsList = new ArrayList<Item>();
ArrayList<User> buyersList = new ArrayList<User>();

//CONSTRUCTOR
    Eshop(){
        this.name = name;
        this.owner = owner;
        this.itemsList = itemsList;
        this.buyersList = buyersList;
    }

//METHOD: ADD ITEM
    public void addItem(ArrayList<Item> itemsList, int categoryOption, String name, double price, String description, int stock, int id){
        Scanner scan = new Scanner (System.in);
        switch(categoryOption){
            case 1:
            System.out.println("SET THE COLOR FOR THE PEN:");
            String color = scan.nextLine();
            System.out.println("SET THE TIP SIZE OF THE PEN:");
            double tipsize = scan.nextDouble();
            Item pen = new Pen(name, price, description, stock, id, color, tipsize);
            itemsList.add(pen);
            break;
            case 2:
            System.out.println("SET THE TYPE FOR THE PENCIL:");
            String type = scan.nextLine();
            System.out.println("SET THE TIP SIZE OF THE PENCIL:");
            double tipSize = scan.nextDouble();
            Item pencil = new Pencil(name, price, description, stock, id, type, tipSize);
            itemsList.add(pencil);
            break;
            case 3:
            System.out.println("SET THE SECTIONS NUMBER FOR THE NOTEBOOK:");
            int arithmosthematwn = scan.nextInt();
            Item notebook = new Notebook(name, price, description, stock, id, arithmosthematwn);
            itemsList.add(notebook);
            break;
            case 4:
            System.out.println("SET THE WEIGHT FOR THE PAPER:");
            int varos = scan.nextInt();
            System.out.println("SET THE NUMBER OF PAGES FOR THE PAPER:");
            int selides = scan.nextInt();
            Item paper = new Paper(name, price, description, stock, id, varos, selides);
            itemsList.add(paper);
            break;
        }
        
     }
    
//METHOD: GET ITEM BY ID
    public Item getItemById(ArrayList<Item> productslist, int productId){
        Item foundid = null;
        for(Item item : productslist){ 
            if (productId == item.id){ 
            foundid = item;
            break;
            }
        }
        return foundid;
    }
 
//METHOD: REMOVE ITEM    
    public void removeItem(Item item){
    itemsList.remove(item);
    }
    
//METHOD: GET BUYER
    public User getBuyer(ArrayList<User> buyerslist, int line){
       Buyer buyer = (Buyer) buyerslist.get(line-1);
       System.out.println(buyer.toString());
       buyer.shoppingcart.showCart(buyer.getMyShoppingCart());
       return buyer;
    }
    
//METHOD: ADD BUYER    
    public User addBuyer(ArrayList<User> buyerslist, String name, String mail ){
        User buyer = new Buyer(name, mail, new ShoppingCart());
        buyerslist.add(buyer);
        return buyer;
    }
 
//METHOD: FIND BUYER IN BUYERSLIST
    public int checkBuyerExistence(ArrayList<User> buyerList, String mail){
        int buyerfound = 0;
        for(User buyer : buyersList){
            if(buyer.email.equals(mail) == true ){
                buyerfound = 1;
            }
        }
        return buyerfound;
    }
    
//METHOD: REMOVE BUYER
    public void removeBuyer(ArrayList<User> buyerslist, int line, ArrayList<Item> itemsList, Buyer buyer){
    buyerslist.remove(line-1);
    for (ItemOrdered itemordered : buyer.shoppingcart.orderList){
        for(Item item : itemsList){
            if (item.id == itemordered.item.id){
            item.stock = item.stock + itemordered.getQuantity();
            updateItemStock(item, item.stock);
            }
        }
    }
    }
    
//METHOD: UPDATE ITEM STOCK    
    public void updateItemStock(Item item, int stock){
    item.stock = stock;
    }
 
//METHOD: SHOW CATEGORIES    
    public void showCategories(ArrayList<Item> itemsList){
    int penCount = 0;
    int pencilCount = 0;
    int notebookCount = 0;
    int paperCount = 0;
    for(Item item : itemsList){
        if(item.stock > 0){
        if(item instanceof Pen){
             penCount++;    
        }
        else if(item instanceof Pencil){
             pencilCount++;
         }
        else if(item instanceof Notebook){
             notebookCount++;
        }
        else if(item instanceof Paper){
             paperCount++;
        }
        else{
        System.out.println("Product not recognized"); 
        }
        }
    }
    System.out.println("1. PEN"+"("+penCount+")"); 
    System.out.println("2. PENCIL"+"("+pencilCount+")"); 
    System.out.println("3. NOTEBOOK"+"("+notebookCount+")"); 
    System.out.println("4. PAPER"+"("+paperCount+")"); 
    }
   
//METHOD: SHOW PRODUCTS IN SPECIFIC CATEGORY
    public ArrayList<Item> showProductsInCategory(ArrayList<Item> itemsList,int categoryoption){
    ArrayList<Item> penList = new ArrayList<Item>();
    ArrayList<Item> pencilList = new ArrayList<Item>();
    ArrayList<Item> notebookList = new ArrayList<Item>();
    ArrayList<Item> paperList = new ArrayList<Item>();
    for(Item item : itemsList){
        if(item.stock > 0){
        if (item instanceof Pen){
        penList.add(item);
        }
        else if (item instanceof Pencil){
        pencilList.add(item);
        }
        else if (item instanceof Notebook){
        notebookList.add(item);
        }
        else if (item instanceof Paper){
        paperList.add(item);
        }
        }
     }
    switch (categoryoption) {
        case 1:
            for(Item item : penList){
                System.out.println(item.getBasicInfo());
            }
            return penList;
        case 2:
            for(Item item : pencilList){
                System.out.println(item.getBasicInfo());
            }
            return pencilList;
        case 3:
            for(Item item : notebookList){
                System.out.println(item.getBasicInfo());
            }
            return notebookList;
        case 4:
            for(Item item : paperList){
                System.out.println(item.getBasicInfo());
            }
            return paperList;
        default:
            return null;
    }
    }
     
//METHOD: SHOW PRODUCT IN SPECIFIC CATEGORY    
    public void showProduct(Item item){
    Item product = item;
    System.out.println(product);
    }
    
//METHOD: CHECK STATUS
    public void checkStatus(ArrayList<User> buyerslist){
    int i = 0;
    for (User buyer : buyerslist){ 		
        i++;
	System.out.println("LINE: "+i+" "+buyer.toString());  
    }
    }
}

