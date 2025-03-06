<%@ page contentType="text/html" pageEncoding="UTF-8" import="casino.helper.UserInfo" import="casino.persistence.User_CRUD" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Account Information</title>
    <script type="text/javascript">
        // Function to update balance based on deposit or withdrawal
        function updateBalance(action) {
            var amount = parseFloat(document.getElementById('amount').value);
            var currentBalance = parseFloat(document.getElementById('balance').innerText);

            // Check if the amount is a valid number
            if (isNaN(amount) || amount <= 0) {
                alert("Please enter a valid amount.");
                return;
            }

            // Set the action value before submitting the form
            document.getElementById('action').value = action;

            // Update the balance visually before the form is submitted
            if (action === 'deposit') {
                document.getElementById('balance').innerText = (currentBalance + amount).toFixed(2);
            } else if (action === 'withdraw') {
                if (currentBalance >= amount) {
                    document.getElementById('balance').innerText = (currentBalance - amount).toFixed(2);
                } else {
                    alert("Insufficient balance for withdrawal.");
                    return;
                }
            }

            // Submit the form to trigger the backend action
            document.getElementById('balanceForm').submit();
        }
    </script>
</head>

<style>
    body {
        background-color: #E6E6FA;
    }

    #transaction-section {
        position: fixed;
        bottom: 20px;
        right: 20px;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    #transaction-section input {
        padding: 5px;
        width: 100px;
    }

    #transaction-section button {
        padding: 10px;
        margin: 5px;
        font-size: 14px;
        cursor: pointer;
    }

    .play-games-button {
        margin-top: 20px;
        padding: 10px 15px;
        font-size: 16px;
        cursor: pointer;
    }

    .logout-button {
        position: fixed;
        bottom: 20px;
        left: 20px;
        padding: 10px 15px;
        font-size: 16px;
        cursor: pointer;
    }
</style>

<%
    UserInfo account = (UserInfo) session.getAttribute("userInfo");
    String username = (String) session.getAttribute("username");
    if (username == null) {
        username = "Guest";
    }
%>

<center><h2>Hello <%= username%></h2></center>
<br>
<center><h3>Account Details</h3></center>
<br>

<form id="balanceForm" action="Balance" method="post">
    <table border="2" align="center" cellpadding="5" cellspacing="5">
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Balance</th>
        </tr>

        <%
            if (account != null) {
        %>
        <tr>
            <td><%= account.getUsername()%></td>
            <td><%= account.getEmail()%></td>
            <td id="balance"><%= account.getBalance()%></td>
        </tr>
        <% } else { %>
        <tr>
            <td colspan="4">No account information available.</td>
        </tr>
        <% }%>
    </table>

    <center>
        <button class="play-games-button" onclick="location.href = 'MainMenu.html'">Play Games</button>
    </center>

    <div id="transaction-section">
        <label for="amount">Amount: </label>
        <input type="number" id="amount" name="amount" min="0.01" step="0.01" placeholder="Enter amount" />
        <br><br>

        <input type="hidden" id="action" name="action" value="">

        <button type="button" onclick="updateBalance('deposit')">Deposit</button>
        <button type="button" onclick="updateBalance('withdraw')">Withdraw</button>
    </div>
</form>

<!-- Log Out Button -->
<button class="logout-button" onclick="location.href = 'index.html'">Log Out</button>

</body>
</html>
