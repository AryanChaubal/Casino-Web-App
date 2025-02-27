<%@ page contentType="text/html" pageEncoding="UTF-8" import="casino.helper.UserInfo" import="casino.persistence.User_CRUD"
%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

                if (action === 'deposit') {
                    // Deposit logic
                    document.getElementById('balance').innerText = (currentBalance + amount).toFixed(2);
                } else if (action === 'withdraw') {
                    // Withdraw logic
                    if (currentBalance >= amount) {
                        document.getElementById('balance').innerText = (currentBalance - amount).toFixed(2);
                    } else {
                        alert("Insufficient balance for withdrawal.");
                    }
                }
                updateBalancce(account);
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
    </style>

    <% 
        UserInfo account = (UserInfo) session.getAttribute("userInfo");
        String username = (String) session.getAttribute("username");
        if(username == null){
            username = "Guest";
        }    
    %>

    <center><h2>Hello <%=username%></h2></center>
    <br>
    <center><h3>Account Details</h3></center>
    <br>
    <form action="Extend" method="post">
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
                    <td><%=account.getUsername()%></td>
                    <td><%=account.getEmail()%></td>
                    <td id="balance"><%=account.getBalance()%></td>
                </tr>
            <% } else { %>
                <tr>
                    <td colspan="4">No account information available.</td>
                </tr>
            <% } %>
        </table>

        <!-- Transaction Section (Deposit/Withdraw) -->
        <div id="transaction-section">
            <label for="amount">Amount: </label>
            <input type="number" id="amount" min="0.01" step="0.01" placeholder="Enter amount" />
            <br><br>
            <button type="button" onclick="updateBalance('deposit')">Deposit</button>
            <button type="button" onclick="updateBalance('withdraw')">Withdraw</button>
        </div>

        <br><br>
    </form>
    <button onclick="location.href='MainMenu.html'">Slot Machine</button>
 
</body>

</html>
