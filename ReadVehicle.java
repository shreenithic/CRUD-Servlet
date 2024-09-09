import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

public class ReadVehicle extends HttpServlet{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	  PrintWriter out=res.getWriter();
	  try {
		  
		  Connection con=DB_Connector.getDbConnection();
		  Statement st=con.createStatement();
		  ResultSet rs=st.executeQuery("select * from Vehicles");
		  JSONArray row = new JSONArray();
		  while(rs.next()) {
			  JSONObject jo=new JSONObject();
			  jo.put("Id", rs.getInt("Id"));
			  jo.put("Type",rs.getString("Type"));
			  jo.put("Model",rs.getString("Model"));
			  jo.put("Price",rs.getInt("Price"));
			  row.put(jo);
			  
		  }
		  out.println(row);
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
  }
}