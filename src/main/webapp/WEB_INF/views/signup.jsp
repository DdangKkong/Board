<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sign Up</title>
</head>
<body>
<h1>Sign Up</h1>
<form action="/users/signup/form" method="post">
    <label for="userLoginId">User Login ID:</label>
    <input type="text" id="userLoginId" name="userLoginId"><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br>
    <label for="passwordCheck">Password Check:</label>
    <input type="password" id="passwordCheck" name="passwordCheck"><br>
    <label for="nickname">Nickname:</label>
    <input type="text" id="nickname" name="nickname"><br>
    <button type="submit">Sign Up</button>
</form>
</body>
</html>
