<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>Login Page</title>
	</head>

	<body>
	
	
	<div align="center" >
	<h3>LOGIN FORM</h3>
		 <form action="login"   method="post" >
 
    <table>
  

  <tr>
  
   <td> <label for="Email"><b>email</b></label></td>
   <td><input type="text" placeholder="Enter email" name="email" required></td></tr>
<tr>
   <td> <label for="password"><b>Password</b></label></td>
   <td> <input type="password" placeholder="Enter Password" name="password" required></td></tr>



<tr>
                <td colspan="2" align="center"><input type="submit" value="LOGIN"></td>
            </tr>

 </table>

 
</form> 
</div>

	</body>
</html>