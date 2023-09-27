package bank;

import java.sql.SQLException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {
  private Scanner sc;
  public static void main(String args[]){
    System.out.println("Welcome to Global Bank");
    Menu menu=new Menu();
    menu.sc=new Scanner(System.in);
    Customer cust=menu.authenticateUser();
    if(cust !=null){
      Account acc=DataSource.getAccount(cust.getAccountId);
      menu.showMenu(cust,acc);
    }

    menu.sc.close();

  }

  private Customer authenticateUser(){
    System.out.println("Enter Username:");
    String user=sc.next();
    System.out.println("Enter Password:");
    String pass=sc.next();
    Customer cust=null;
    try{
      cust=Authenticator.login(user, pass);
    }
    catch(LoginException e){
      System.out.println("There was an error: "+ e.getMessage());
  
    }
    return cust;
  }

  private void showMenu(Customer cust, Account acc){
    
  }
}
