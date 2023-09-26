package bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class DataSource {
  public static Connection connect(){
   String db_file="jdbc:sqlite:resources/bank.db";
   Connection connection=null;
   try{
    connection=DriverManager.getConnection(db_file);
    System.out.println("we're connected");
   }catch(SQLException e){
    e.printStackTrace();
   }
   return connection;
  }

  public static Customer getCustomer(String username){
    String sql="select * from customers where username=?";
    Customer customer=null;
    try(Connection connection=connect();
    PreparedStatement statement=connection.prepareStatement(sql)){
      statement.setString(1, username);
      try(ResultSet resultSet=statement.executeQuery()){
        customer=new Customer(
          resultSet.getInt("id"),
          resultSet.getString("name"),
          resultSet.getString("username"),
          resultSet.getString("password"),
          resultSet.getInt("account_id")
        );
      }

    }catch(SQLException e){
      e.printStackTrace();
    }

    return customer;
  }

  public static Account getAccount(int aid){
    Account acc=null;
    String sql="select * from accounts where id = ?";
    try(Connection conn=connect();
    PreparedStatement statement=conn.prepareStatement(sql)){
      statement.setInt(1,aid);
      try(ResultSet rs=statement.executeQuery()){
        acc=new Account(
          rs.getInt("id"),
          rs.getString("type"),
          rs.getDouble("balance")
        );

      }catch(SQLException e){
      e.printStackTrace();
    }

    }catch(SQLException e){
      e.printStackTrace();
    }

    return acc;
  }

  public static void main(String args[]){
    //connect();
    Customer cust=getCustomer("twest8o@friendfeed.com");
    System.out.println(cust.getName());
    Account acc=getAccount(cust.getAccountId());
    System.out.println(acc.getBalance());
  }
}
