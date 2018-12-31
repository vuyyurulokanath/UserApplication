<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>

 
<h3 align="center">ADMIN PAGE</h3>
<a href="admindetails/${admin.id} " aligin="right">view details</a>
<div align="center">


	<h5>USERS LIST</h5>

<table style="width:2%"  border="10">
  <tr>
  
    <th>EMAIL</th>
    <th>view</th>
    
    </tr>
		
			<c:forEach var="listValue" items="${users}">
			<c:if test="${listValue.role=='USER'}">
			
			
			
			<tr>
			 
				
				<td>${listValue.email} </td>
			<td><a href="userdetails/${listValue.id} ">view</a><td>	
				
				
				
				</tr>
				</c:if>
				</c:forEach>
				
			
	
</table>
    </div>
    
   

</body>
</html>