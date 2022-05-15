package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



//data base connection

public class Complaint {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cmp_db", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//insert

	public String insertComplaint( String cus_name, String area_off, String address, String contact, String email, String comp_dis )	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into complaint (`comp_id`,`cus_name`,`area_off`,`address`,`contact`,`email`,`comp_dis' )" + " values (?, ?, ?, ?, ?, ?, ?)";
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, cus_name);
			 preparedStmt.setString(3, area_off);
			 preparedStmt.setString(4, address);
			 preparedStmt.setString(5, contact);
			 preparedStmt.setString(6, email);
			 preparedStmt.setString(7, comp_dis);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newComplaint = readComplaint(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newComplaint + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Complaint.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	//read
	
	public String readComplaint()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border='1'> <tr>"
					+ "<th>cus_name</th>"
					+ "<th>area_off</th>"
					+ "<th>adress</th>"
					+ "<th>contact</th>"
					+ "<th>email</th>"
					+ "<th>comp_dis</th> <th>Update</th> <th>Delete</th> </tr>"; 
			 
			String query = "select * from complaint"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String comp_id = Integer.toString(rs.getInt("comp_id"));
				 String cus_name = rs.getString("cus_name");
				 String area_off = rs.getString("area_off");
				 String address = rs.getString("address");
				 String contact = rs.getString("contact");
				 String email = rs.getString("email");
				 String comp_dis = rs.getString("comp_dis");
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidcomp_idUpdate\' name=\'hidcomp_idUpdate\' type=\'hidden\' value=\'" + comp_id + "'>" 
							+ cus_name + "</td>"; 
				output += "<td>" + area_off + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + contact + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + comp_dis + "</td>";

				  
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-iid='" + comp_id + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Complaint.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	//update
	
	public String updateComplaint(String comp_id,String cus_name, String area_off, String address, String contact, String email, String comp_dis )	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			
			String query = "UPDATE complaint SET cus_name=?,area_off=?,address=?,contact=?,email=?,comp_dis=? WHERE comp_id=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 
	 
			// binding values    
			 preparedStmt.setString(1, cus_name);
			 preparedStmt.setString(2, area_off);
			 preparedStmt.setString(3, address);
			 preparedStmt.setString(4, contact);
			 preparedStmt.setString(5, email);
			 preparedStmt.setString(6, comp_dis);
			 preparedStmt.setInt(7, Integer.parseInt(comp_id)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newComplaint = readComplaint();    
			output = "{\"status\":\"success\", \"data\": \"" + newComplaint + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Complaint.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	//delete
	
	public String deleteComplaint(String comp_id)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
				
			} 
	 
			// create a prepared statement    
			String query = "delete from innovator where comp_id=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(comp_id)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newComplaint = readComplaint();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newComplaint + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Complaint.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
