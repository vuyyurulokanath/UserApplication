<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
    <html>
<body>
 
 
<h3 align="center">Admin details</h3>
<div align="center">

	
 
    
    
<table style="width:2%"  border="10">

			
  <tr>
    <th>email</th>
    <th>FIRST NAME</th> 
    <th>LAST NAME</th>
    <th>PASSWORD</th>
    <th>EDIT</th>
    <th>Delete</th>
    
  </tr>
			
			<tr>
			 
				<td>${user.email} </td>
				<td>${user.firstName}</td>
				<td>${user.lastName} </td>
				<td>${user.password}</td>
               <td><a href="/edituser/${user.id}">Edit</a></td>
               <td><a href="deleteuser/${user.id}"onclick="return confirm('Are you sure you want to delete this user?');">Delete</a></td>
	
		
			
			
				
				</tr>
				
	
</table>


	</div>
</body>
</html>