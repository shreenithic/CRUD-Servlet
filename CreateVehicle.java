
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateVehicle extends HttpServlet {
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        try {
        	
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
        	
//        	int Id = jsonObject.getInt("Id");
        	String Type = jsonObject.getString("Type");
        	String Model = jsonObject.getString("Model");
        	int Price = jsonObject.getInt("Price");
        	

            
            String Insert_Sql = "INSERT INTO Vehicles(Type, Model, Price) VALUES (?, ?, ?);";

           
            Connection connection = DB_Connector.getDbConnection();

         
                PreparedStatement preparedStatement = connection.prepareStatement(Insert_Sql, Statement.RETURN_GENERATED_KEYS);
     
                preparedStatement.setString(1, Type);
                preparedStatement.setString(2, Model);
                preparedStatement.setInt(3, Price);

              
                int rowsAffected = preparedStatement.executeUpdate();
                
                PrintWriter out = res.getWriter();
                JSONObject jo = new JSONObject();

 
                if (rowsAffected > 0) {
                	ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        jo.put("status", "success");
                        jo.put("message", "Vehicle added successfully");
                        jo.put("Id", generatedId);
                    } else {
                    jo.put("status", "failure");
                    jo.put("message", "Failed to add vehicle");
                   }
                }
                
                out.println(jo);
            


            connection.close();

        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}







