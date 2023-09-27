package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {
  public static Customer login(String username, String password)throws LoginException{
    Customer cust=DataSource.getCustomer(username);
    if(cust==null){
      throw new LoginException("Customer not found");
    }
    if(password.equals(cust.getPassword())){
      cust.setAuthenticated(true);
      return cust;
    }
    else throw new LoginException("Incorrect Password");
    
  }
  public static void logout(Customer cust){
    cust.setAuthenticated(false);
  }
}
