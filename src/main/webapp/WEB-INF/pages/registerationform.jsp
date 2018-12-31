<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>New/Edit</title>
</head>
<body>
    <div class="form">
    
    <h1 align="center">enter details</h1>
    
    
    
     <form:form action="saveuser" method="POST" modelAttribute="user" >
    <table align="center">
    
  
    
    
            <form:hidden path="id"/>
            
             <tr>
                <td>firstname:</td>
                <td><input type="text" name="firstName" ></td>
            </tr>
     
            
            <tr> 
                <td>lastName:</td>
                <td><input type="text" name="lastName"></td>
            </tr>
            
<tr>
                <td>Password:</td>
                <td><input type="text" name="password"></td>
            </tr>
            
            
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email"  ></td>
            </tr>
     
            
			
			
			
                <td colspan="2" align="center"><input type="submit" value="save"></td>
            </tr>
            
			
			
				
			
	

    </table>
    </form:form>
   </div>

</body>
</html>