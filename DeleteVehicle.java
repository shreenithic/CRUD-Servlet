import java.sql.*;
import java.io.*;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteVehicle extends HttpServlet{
   @Override
   protected void doDelete(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
	   
	  res.setContentType("application/json");
      res.setCharacterEncoding("UTF-8");
      
	 try {
	  
	   Statement stmt=null;
	   StringBuilder jsonBuffer = new StringBuilder();
       BufferedReader reader = req.getReader();
       
       String line;
       while ((line = reader.readLine()) != null) {
           jsonBuffer.append(line);
       }
       
   	   String jsonString = jsonBuffer.toString();
        if (jsonString.isEmpty()) {
            throw new ServletException("Empty JSON body");
        }
        
   	  JSONObject jsonObject = new JSONObject(jsonString);
   	
   	  int Id = jsonObject.getInt("Id");

	   Connection connection=DB_Connector.getDbConnection();
	   stmt=connection.createStatement();
	   stmt.executeUpdate("DELETE FROM Vehicles WHERE Id="+Id+";");
	
	   PrintWriter out = res.getWriter();
	   JSONObject jo = new JSONObject();
	   
	   jo.put("status", "success");
	   out.println(jo);
	 }
	 catch(Exception e) {
		 e.printStackTrace();
	 }
  }
}
