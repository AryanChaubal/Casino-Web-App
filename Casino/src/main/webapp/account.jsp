<%-- 
    Document   : userbooks
    Created on : Jan 24, 2021, 1:00:48 AM
    Author     : student
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="com.casino.AccountInfo"%>
<!DOCTYPE html>


<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Account Information</title>
    </head>
    <style>

body {
  background-color: #E6E6FA;

}
</style>
<% 
    AccountInfo account = (AccountInfo) session.getAttribute("userInfo");
    String username = (String) session.getAttribute("username");
    if(username == null){
        username = "Guest";
    }    
%>


<center><h2>Hello <%=username%></h2></center>
<br>
<center><h3> Account Details</h3></center>
<br>
<form action="Extend" method="post">
<table border="2" align="center" cellpadding="5" cellspacing="5">

<tr>
   
<th> Display Name </th>
<th> Account Name </th>
<th> Email </th>
<th> Balance </th>

</tr>
<%

if (account != null) {
%>

    <tr>
        <td> <%=account.getDisplayName()%> </td>
        <td> <%=account.getAccountName()%> </td>
        <td> <%=account.getEmail()%> </td>
        <td> <%=account.getBalance()%> </td>
    </tr>
<% } else { %>
    <tr>
        <td colspan="4">No account information available.</td>
    </tr>
<% } %>
</tr>
</table>
</form>
</body>
</html>
