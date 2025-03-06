<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="casino.helper.UserInfo" %>
<%@ page import="pageNumber.*, java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Slot Machine</title>
        <style>
            body {
                text-align: center;
                font-family: Arial, sans-serif;
            }
            .slot-container {
                margin: 50px auto;
                padding: 20px;
                border: 2px solid black;
                width: 300px;
            }
            .slot-result {
                font-size: 24px;
                margin: 20px 0;
            }
            .spin-button, .back-button {
                padding: 10px 20px;
                font-size: 18px;
                cursor: pointer;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <h1>Welcome to the Slot Machine</h1>
        <div class="slot-container">
            <%
                HttpSession sessionObj = request.getSession(false);
                UserInfo account = (sessionObj != null) ? (UserInfo) sessionObj.getAttribute("userInfo") : null;
                double balance = (account != null) ? account.getBalance() : 0.0;
            %>
            <h3>Your Balance: $<%= balance%></h3>

            <% if (account == null) { %>
            <h3>Please <a href='login.jsp'>Login</a> to play.</h3>
            <% } else {%>
            <form action="SlotMachine" method="get">
                <label for="bet">Enter your bet:</label>
                <input type="number" id="bet" name="bet" min="1" max="<%= (int) balance%>" required>
                <button type="submit" class="spin-button">Spin</button>
            </form>
            <% }%>
        </div>

        <!-- Back Button -->
        <button onclick="history.back()" class="back-button">Go Back</button>
    </body>
</html>
