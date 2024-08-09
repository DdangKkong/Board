<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sign In</title>
</head>
<body>
<h1>Sign In</h1>
<form action="/users/signin/form" method="post">
    <label for="userLoginId">User Login Id:</label>
    <input type="text" id="userLoginId" name="userLoginId"><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br>
    <button type="submit">Sign In</button>
</form>
</body>
</html>
