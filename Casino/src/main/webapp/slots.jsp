<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession, casino.helper.UserInfo" %>
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
            margin: 30px auto;
            padding: 20px;
            border: 2px solid black;
            width: 300px;
            background-color: #f9f9f9;
        }
        .slot-result {
            font-size: 28px;
            font-weight: bold;
            margin: 20px 0;
        }
        .spin-button, .back-button {
            padding: 10px 20px;
            font-size: 18px;
            cursor: pointer;
            margin-top: 10px;
        }
        .betting-section {
            margin-top: 40px;
        }
    </style>
    <script>
        function playSlotMachine() {
            var betAmount = document.getElementById("bet").value;
            if (betAmount <= 0) {
                alert("Please enter a valid bet amount.");
                return;
            }

            var xhr = new XMLHttpRequest();
            xhr.open("GET", "SlotMachine?bet=" + betAmount, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    document.getElementById("result-section").innerHTML = xhr.responseText;
                }
            };
            xhr.send();
        }
    </script>
</head>
<body>

    <h1>Welcome to the Slot Machine</h1>

    <!-- Default Slot Machine Display -->
    <div class="slot-container">
        <h3>Slot Machine</h3>
        <div class="slot-result">
            🍒 | 🍋 | 🍊
        </div>
    </div>

    <!-- Betting Section -->
    <div class="betting-section">
        <%
            HttpSession sessionObj = request.getSession(false);
            UserInfo account = (sessionObj != null) ? (UserInfo) sessionObj.getAttribute("userInfo") : null;
            double balance = (account != null) ? account.getBalance() : 0.0;
        %>

        <h3>Your Balance: $<%= balance %></h3>

        <% if (account == null) { %>
            <h3>Please <a href='login.jsp'>Login</a> to play.</h3>
        <% } else { %>
            <form onsubmit="event.preventDefault(); playSlotMachine();">
                <label for="bet">Enter your bet:</label>
                <input type="number" id="bet" name="bet" min="1" max="<%= (int) balance %>" required>
                <button type="submit" class="spin-button">Spin</button>
            </form>
        <% } %>
    </div>

    <!-- Results Section (Will be updated dynamically) -->
    <div id="result-section"></div>

    <button onclick="history.back()" class="back-button">Go Back</button>

</body>
</html>
