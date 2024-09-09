import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connector {
    public static Connection getDbConnection()
	  {
		  Connection con=null;
		  
		  String db_url="jdbc:mysql://localhost:3306/VehicleGallery";
		  String db_user="root";
		  String db_pswd="root-sql";
		  
		  try {
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  con=DriverManager.getConnection(db_url,db_user,db_pswd);
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		  
		  return con;
	  }
}
