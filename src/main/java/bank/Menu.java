package bank;
import java.util.Scanner;
import bank.Exceptions.AmountException;
import javax.security.auth.login.LoginException;

public class Menu {
  private Scanner sc;
  public static void main(String args[]){
    System.out.println("Welcome to Global Bank");
    Menu menu=new Menu();
    menu.sc=new Scanner(System.in);
    Customer cust=menu.authenticateUser();
    if(cust !=null){
      Account acc=DataSource.getAccount(cust.getAccountId());
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
    int select=0;
    while(select !=4 && cust.isAuthenticated()){
      System.out.println("******************************");
      System.out.println("Select your option: ");
      System.out.println("1: Deposite");
      System.out.println("2: Withdraw");
      System.out.println("3: Check Balance");
      System.out.println("4: Exit");
      System.out.println("******************************");
    
    select=sc.nextInt();
      double amount=0;
      switch(select){
        case 1:System.out.println("How much would you like to deposite?");
        amount=sc.nextDouble();
        try{
          acc.deposite(amount);
        }catch(AmountException e){
          System.out.println(e.getMessage());
          System.out.println("Please try again!");
        }
        break;
        case 2:System.out.println("How much would you like to withdraw?");
        amount=sc.nextDouble();
        acc.withdraw(amount);
        break;
        case 3:
        System.out.println("Balance= "+ acc.getBalance());
        break;
        case 4:
        Authenticator.logout(cust);
        System.out.println("You've logged out successfully");
        break;
       default:
        System.out.println("Invalid Option. Try again!");
        break;
      }
    }
    
  }
}
