import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

/*Defines a SQL connection using JDBC to connect to SQLite database*/
public class SQLConnection{
   public static Connection DbConnector(String path){
      try{
         Connection conn = null;
         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection("jdbc:sqlite:" + path);
         return conn;
      }catch(ClassNotFoundException | SQLException e){
         System.out.println(e);      
      }
      return null;
   }
   
   public static void createNewDatabase(String fileName) {
 
        String url = "jdbc:sqlite:" + fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}