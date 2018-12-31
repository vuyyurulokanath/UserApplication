<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="POST" action="/upload/${user.id} " enctype="multipart/form-data"  align="right">
            File:
            <input type="file" name="file" /> <br/>
          
            </br>
            <input type="submit" value="Upload"  />
        </form>

<div align="center">




	<h2>file LIST</h2>
	
	<h3>available file:${user.available_files}</h3>
<h3>total file:${user.totalfiles_uploaded}</h3>
	

<table style="width:2%"  border="10">
  <tr>
  
    <th>filename</th>
    <th>delete</th>
    
    </tr>
		
			<c:forEach var="listValue" items="${list}">
			
			
			
			
			<tr>
			 
				
				<td>${listValue.filename} </td>
		<td><a href="/delete/${listValue.id}"onclick="return confirm('Are you sure you want to delete this file?');">Delete</a></td>
 				<!-- <td><a href="/deleteuser/${listValue.id}"onclick="return confirm('Are you sure you want to delete this user?');">Delete</a></td> -->
			
				
				</tr>
				
				</c:forEach>
				
			
	
</table>
    </div>
    
   

</body>
</html>