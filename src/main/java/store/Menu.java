
package store;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

 public class Menu{
 Eshop eshop = new Eshop();
 ShoppingCart shoppingcart = new ShoppingCart();
 
//METHOD: WELCOME SCREEN GENERIC 
    public void welcomeScreen(User owner, ArrayList<Item> itemsList, ArrayList<User> buyerslist){
        User foundemail = null;
    Scanner scan = new Scanner (System.in);
    System.out.println("WELCOME TO THE STORE");
    System.out.println("ENTER EMAIL");
    String email = scan.nextLine(); 
    
    //EMAIL VALIDATION
    while (!isEmailValid(email)){ 
        System.out.println("EMAIL IS NOT VALID"); 
        System.out.println("ENTER EMAIL");
        email = scan.nextLine();
    }
    
    //EMAIL VERIFICATION
        if(email.equals(owner.getEmail())){
            OwnerWelcomeScreen((Owner) owner, itemsList, buyerslist);
        }
        else{
            for(User buyer : buyerslist){
                if(email.equals(buyer.getEmail()) ){
                    foundemail = buyer;
                    break;
                }
            }
            if (foundemail != null){
                BuyerWelcomeScreen((Buyer)foundemail, itemsList, buyerslist);
            }
            else{
            System.out.println("EMAIL NOT EXIST. DO YOU WANT TO SIGN UP? (Y/N)"); 
            char signupOption = scan.next().charAt(0);
                if (signupOption == 'y'){
                    scan.nextLine();
                    System.out.println("NAME:");
                    String name = scan.nextLine();
                    System.out.println("EMAIL:");
                    email = scan.nextLine();
                    
                    BuyerWelcomeScreen((Buyer)eshop.addBuyer(buyerslist,name,email), itemsList, buyerslist);
                }
                else{
                   scan.close();
                   
                }
            }
        }
        
    scan.close();
    }
    
//METHOD: WELCOME SCREEN BUYER    
    public void BuyerWelcomeScreen(Buyer buyer, ArrayList<Item>itemsList, ArrayList<User> buyerslist){
    Buyer pelatis = buyer;
    Scanner scan = new Scanner (System.in);
    int buyerOption = 0;
    System.out.println("\n"+"HELLO\t"+buyer.toString()+"\n");
    
    do{
    System.out.println("\nWELCOME TO THE STORE"+"\n 1. BROWSE STORE"+"\n 2. VIEW CART"+"\n 3. CHECKOUT"+"\n 4. BACK"+"\n 5. LOG OUT"+"\n 6. EXIT"+"\n ENTER YOUR OPTION BY NUMBER [1, 2, 3, 4, 5, 6]");
    buyerOption = scan.nextInt();
    switch(buyerOption) {
          case 1:
          System.out.println("PRODUCT CATEGORIES");
          eshop.showCategories(itemsList);
          System.out.println("CHOOSE THE PRODUCT CATEGORY BY NUMBER [1, 2, 3, 4]");
          int categoryOption = scan.nextInt();
          while(categoryOption != 1 && categoryOption != 2 && categoryOption != 3 && categoryOption != 4){
              System.out.println("NOT A VALID NUMBER OF CATEGORY!\nGIVE A VALID NUMBER FROM 1 - 4:");
              categoryOption = scan.nextInt();
          }
          ArrayList<Item>category = eshop.showProductsInCategory(itemsList,categoryOption);
          System.out.println("CHOOSE THE PRODUCT OF THE CATEGORY BY ID");
          int productid = scan.nextInt();
          Item product = null;
            do{
            product = eshop.getItemById(category, productid);
              if (product == null){
              System.out.println("THE ID NOT EXISTS"+"\nENTER A VALID ID");
              productid = scan.nextInt();
              }
            }while(product == null);
          eshop.showProduct(product);
          System.out.println("DO YOU WANT TO BUY THE PRODUCT? (Y/N)");
          char buyitem = scan.next().charAt(0);
            if (buyitem == 'y'){
            System.out.println("HOW MANY PIECES DO YOU WANT OF THE PRODUCT?");
            int posotita = scan.nextInt();
            while(posotita > product.stock){
            System.out.println("QUANTITY IS GREATER THAN THE ACTUAL STOCK! PLEASE GIVE NEW QUANTITY:");
            posotita = scan.nextInt();
            }
            pelatis.placeOrder(product, posotita);
            shoppingcart.showCart(pelatis.shoppingcart.orderList);
            }
          break;
          case 2:
              
              char backoption = 0;
              do{
              shoppingcart.showCart(pelatis.getMyShoppingCart());
              System.out.println("\n1. SELECT ITEM"+"\t2. CLEAR CART"+"\t 3. CHECKOUT"+"\t 4. BACK"+"\nENTER YOUR OPTION BY NUMBER [1, 2, 3, 4]");
              int cartoption = scan.nextInt();
              switch(cartoption){
                case 1:
                if(pelatis.shoppingcart.orderList.isEmpty() == false){
                System.out.println("SELECT ITEM IN CART BY ID");
                int itemselect = scan.nextInt();
                ItemOrdered itemselected = shoppingcart.getItemByIdCart(pelatis.shoppingcart.orderList, itemselect);
                while(itemselected == null){
                System.out.println("ITEM NOT EXISTS! PLEASE ADD A NEW ID:");
                itemselect = scan.nextInt();
                itemselected = shoppingcart.getItemByIdCart(pelatis.shoppingcart.orderList, itemselect);
                }
                System.out.println("\n1. DELETE ITEM"+"\t2. CHANGE QUANTITY"+"\nENTER YOUR OPTION BY NUMBER [1, 2]");
                int selectitemoption = scan.nextInt();  
                    switch(selectitemoption){
                      case 1:
                      shoppingcart.removeItemOrdered(pelatis,pelatis.getMyShoppingCart(),itemselected);
                      System.out.println("WANNA GO BACK? [PRESS Y/N]");
                      backoption = scan.next().charAt(0);
                      break;
                      case 2:
                      System.out.println("PLEASE ADD NEW QUANTITY:");
                      int quantityChanged = scan.nextInt();
                      shoppingcart.changeItemOrderedQuantity(itemselected, quantityChanged);
                      shoppingcart.showCart(pelatis.shoppingcart.orderList);
                      System.out.println("WANNA GO BACK? [PRESS Y/N]");
                      backoption = scan.next().charAt(0);
                      break;
                    }
                }
                else{
                System.out.println("THE CART IS EMPTY! IT'S TIME TO BUY SOMETHING!");
                }
                break;
                case 2:
                shoppingcart.clearCart(pelatis.shoppingcart.orderList, pelatis);
                break;
                case 3:
                shoppingcart.checkout(pelatis.shoppingcart.orderList,pelatis, itemsList);
                break;
                case 4:
                BuyerWelcomeScreen(pelatis, itemsList, buyerslist);
                break;
                }
              
              }while(backoption == 'y');  
          break;
          case 3:
          itemsList = shoppingcart.checkout(pelatis.shoppingcart.orderList,pelatis,itemsList);
          break;
          case 4:
          welcomeScreen(new Main().initializeOwner(),itemsList ,buyerslist);
          break;
          case 5:
          System.out.println("DO YOU WANT TO LOG OUT? [Y/N]");
          char logoutOption = scan.next().charAt(0);
          if (logoutOption == 'y'){
          welcomeScreen(new Main().initializeOwner(),itemsList, buyerslist);
          }
          break;
          case 6:
          System.exit(0);
          break;
    }   
    }while(buyerOption != 6);  
    }
    
//METHOD: WELCOME SCREEN OWNER    
    public void OwnerWelcomeScreen(Owner owner, ArrayList<Item> itemsList, ArrayList<User> buyerslist){
    Scanner scan = new Scanner (System.in);
    int ownerOption = 0;
    System.out.println("\n"+"HELLO\t"+owner.toString()+"\n");
    do{
    System.out.println("\nWELCOME TO THE STORE"+"\n 1. BROWSE STORE"+"\n 2. CHECK STATUS"+"\n 3. BACK"+"\n 4. LOG OUT"+"\n 5. EXIT"+"\n ENTER YOUR OPTION BY NUMBER [1, 2, 3, 4, 5]");
    ownerOption = scan.nextInt();
    switch(ownerOption) {
          case 1:
          System.out.println("PRODUCT CATEGORIES");
          eshop.showCategories(itemsList);
          System.out.println("CHOOSE THE PRODUCT CATEGORY BY NUMBER [1, 2, 3, 4]");
          int categoryOption = scan.nextInt();
          while(categoryOption != 1 && categoryOption != 2 && categoryOption != 3 && categoryOption != 4){
              System.out.println("NOT A VALID NUMBER OF CATEGORY!\nGIVE A VALID NUMBER FROM 1 - 4:");
              categoryOption = scan.nextInt();
          }
          ArrayList<Item>category = eshop.showProductsInCategory(itemsList,categoryOption);
          System.out.println("1. ADD NEW PRODUCT\t2. UPDATE PRODUCT'S STOCK");
          int ownerProductOption = scan.nextInt();
          switch(ownerProductOption){
              case 1:
              System.out.println("GIVE A NAME FOR THE ITEM:");
              scan.nextLine();
              String name = scan.nextLine();
              System.out.println("ENTER THE PRICE OF THE ITEM:");
              double price = scan.nextDouble();
              System.out.println("GIVE A DESCRIPTION FOR THE PRODUCT");
              scan.nextLine();
              String description = scan.nextLine();
              System.out.println("ENTER THE AVAILABLE STOCK OF THE ITEM:");
              int apothema = scan.nextInt();
              System.out.println("ENTER AN ID FOR THE ITEM:");
              int id = scan.nextInt();
              Item item = eshop.getItemById(itemsList, id);
              while(item != null){
              System.out.println("THERE IS ALREADY AN ITEM WITH THIS ID. ENTER A NEW ONE FOR YOUR ITEM:");
              id = scan.nextInt();
              item = eshop.getItemById(itemsList, id);
              };
              eshop.addItem(itemsList,categoryOption, name, price, description, apothema, id);
              break;
              case 2:
              System.out.println("CHOOSE THE PRODUCT OF THE CATEGORY BY ID");
              int productid = scan.nextInt();
              Item product = null;
                do{
                product = eshop.getItemById(category, productid);
                    if (product == null){
                    System.out.println("THE ID NOT EXISTS"+"\nENTER A VALID ID");
                    productid = scan.nextInt();
                    }
                }while(product == null);
              eshop.showProduct(product);
              System.out.println("DO YOU WANT TO CHANGE THE PRODUCT'S STOCK? (Y/N)");
              char buyitem = scan.next().charAt(0);
                    if (buyitem == 'y'){
                    System.out.println("ENTER THE NEW STOCK:");
                    int stock = scan.nextInt();
                    eshop.updateItemStock(product, stock);
                    System.out.println("THE PRODUCT'S STOCK CHANGED!");
                    System.out.println(product);  
                    }
             break;
            }
          break;
          case 2:
          eshop.checkStatus(buyerslist);
          System.out.println("1. ADD BUYER\t2. DELETE BUYER\t3. BACK\n");
          int option = scan.nextInt();
          switch(option){
              case 1:
              System.out.println("ADD NEW CUSTOMER'S INFO:");
              scan.nextLine();
              System.out.println("ENTER THE BUYER'S NAME:");
              String name = scan.nextLine();
              System.out.println("ENTER THE BUYER'S EMAIL:");
              String email = scan.nextLine();
              while(eshop.checkBuyerExistence(buyerslist, email) == 1){
                  System.out.println("THERE IS ALREADY AN ACCOUNT WITH THIS EMAIL ADDRESS! PLEASE ENTER A NEW AND VALID EMAIL:");
                  email = scan.nextLine();
              }
              eshop.addBuyer(buyerslist, name, email);
              break;
              case 2:
              System.out.println("SELECT CUSTOMER BY LINE NUMBER:");
              int line = scan.nextInt();
              User buyer = eshop.getBuyer(buyerslist, line);
              System.out.println("DO YOU WANT TO REMOVE THE BUYER FROM THE CUSTOMER'S LIST? [Y/N]");
              char removebuyeroption = scan.next().charAt(0);
              if(removebuyeroption == 'y'){
               eshop.removeBuyer(buyerslist, line, itemsList, (Buyer) buyer );
              }
              break;
              case 3:
              break;
          }
          break;
          case 3:
          welcomeScreen(owner, itemsList, buyerslist);
          break;
          case 4:
          System.out.println("DO YOU WANT TO LOG OUT? [Y/N]");
          char logoutOption = scan.next().charAt(0);
          if (logoutOption == 'y'){
          welcomeScreen(owner, itemsList, buyerslist);
          }
          break;
          case 5:
          System.exit(0);
          break;
    } 
    }while(ownerOption != 5);
    }
    
//METHOD: EMAIL VALIDATION    
    public static boolean isEmailValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches();
    }
    
}
