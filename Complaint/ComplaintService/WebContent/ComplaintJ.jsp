<%@page import="com.Complaint"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complaint Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/complaint.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Complaint DETAILS</h1>
				<form id="formComplaint" name="formComplaint" method="post" action="ComplaintJ.jsp">  
					cus_name:  
 	 				<input id="cus_name" name="cus_name" type="text"  class="form-control form-control-sm">
					<br> area_off:   
  					<input id="area_off" name="area_off" type="text" class="form-control form-control-sm">  					  
  					<br> address:   
  					<input id="address" name="address" type="text"  class="form-control form-control-sm">
					<br> 				 
       				<br> contact:   
  					<input id="contact" name="contact" type="text"  class="form-control form-control-sm">
					<br> 					
					<br>  email:   
  					<input id=" email" name=" email" type="text" class="form-control form-control-sm">   					
  					<br> comp_dis:   
  					<input id="comp_dis" name="comp_dis" type="text" class="form-control form-control-sm"> 
  					
      				<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidcomp_idSave" name="hidcomp_idSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divComplaintGrid">
					<%
					    Complaint innoObj = new Complaint();
						out.print(innoObj.readComplaint()); 
						
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>