$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateComplaintForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidcomp_idSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "ComplaintService",  
			type : type,  
			data : $("#formComplaint").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onComplaintSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onComplaintSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divComplaintGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidcomp_idSave").val("");  
	$("#formComplaint")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidcomp_idSave").val($(this).closest("tr").find('#hidcomp_idUpdate').val());     
	$("#cus_name").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#area_off").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());  
	$("#contact").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#email").val($(this).closest("tr").find('td:eq(3)').text());   
	$("#comp_dis").val($(this).closest("tr").find('td:eq(3)').text());        
}); 




//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "ComplaintService",   
		type : "DELETE",   
		data : "comp_id=" + $(this).data("iid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onComplaintDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onComplaintDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divComplaintGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateComplaintForm() 
{  
	// NAME  
	if ($("#cus_name").val().trim() == "")  
	{   
		return "Insert cus_name.";  
	} 

	// DESCRIPTION------------------------  
	if ($("#area_off").val().trim() == "")  
	{   
		return "Insert area_off.";  
	} 
	
	//DISCRIPTION-------------------------------
	if ($("#address").val().trim() == "")  
	{   
		return "Insert address.";  
	} 
	
	//DISCRIPTION-------------------------------
	if ($("#contact").val().trim() == "")  
	{   
		return "Insert contact.";  
	} 
	
	//DISCRIPTION-------------------------------
	if ($("#email").val().trim() == "")  
	{   
		return "Insert email.";  
	} 
	
	//DISCRIPTION-------------------------------
	if ($("#comp_dis").val().trim() == "")  
	{   
		return "Insert comp_dis.";  
	} 


	return true; 
}