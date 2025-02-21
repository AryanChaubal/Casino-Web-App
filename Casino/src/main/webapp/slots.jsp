<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Slot Machine</title>
    <style>
        body { text-align: center; font-family: Arial, sans-serif; }
        .slot-machine { margin-top: 50px; }
        .reels { font-size: 24px; margin: 20px; }
        .result { font-weight: bold; margin-top: 10px; }
    </style>
</head>
<body>
    <h1>Slot Machine</h1>
    <div class="slot-machine">
        <div class="reels">
            [ <%= request.getAttribute("reel1") != null ? request.getAttribute("reel1") : "?" %> |
              <%= request.getAttribute("reel2") != null ? request.getAttribute("reel2") : "?" %> |
              <%= request.getAttribute("reel3") != null ? request.getAttribute("reel3") : "?" %> ]
        </div>
        <form action="SlotMachineServlet" method="post">
            <button type="submit">Spin</button>
        </form>
        <p class="result">
            <%= request.getAttribute("result") != null ? request.getAttribute("result") : "" %>
        </p>
    </div>
</body>
</html>
