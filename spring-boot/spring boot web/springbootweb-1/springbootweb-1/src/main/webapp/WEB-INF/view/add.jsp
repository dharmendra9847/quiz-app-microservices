<%@page language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculator</title>

    <!-- Include Bootstrap for the Icons -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

     <%-- Include CSS for the Styling --%>
    <link rel="stylesheet" href="add.css">
</head>
<body>
    <div class="result-container">
        <div class="icon-circle">
            <span class="glyphicon glyphicon-ok"></span>
        </div>
        <h1>Calculation Complete</h1>
        <p>Your Result is</p>
        <div class="result-value">${result}</div>
        <a href="index" class="btn-back">&larr; Calculate Again</a>
    </div>
</body>
</html>