<%@page language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>

    <!-- Include Bootstrap for the Icons -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <%-- Include CSS for the Styling --%>
    <link rel="stylesheet" href="style.css">

</head>
<body>
    <div class="glass-card">
        <h1>Welcome Home</h1>
        <h3>Smart Calculator</h3>
        <form action="add">
            <div class="form-group">
                <label for="num1">First Number</label>
                <input type="number" name="num1" id="num1" placeholder="e.g. 42" required>
            </div>
            <div class="form-group">
                <label for="num2">Second Number</label>
                <input type="number" name="num2" id="num2" placeholder="e.g. 10" required>
            </div>
            <button type="submit" class="btn-pro">Calculate Now</button>
        </form>
    </div>
</body>
</html>