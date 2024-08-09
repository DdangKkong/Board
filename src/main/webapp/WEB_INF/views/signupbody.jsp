<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sign Up</title>
    <script>
      async function signUp(event) {
        event.preventDefault();
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        const response = await fetch('/users/signup', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ username, password })
        });

        const result = await response.text();
        document.open();
        document.write(result);
        document.close();
      }
    </script>
</head>
<body>
<h1>Sign Up</h1>
<form onsubmit="signUp(event)">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username"><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br>
    <button type="submit">Sign Up</button>
</form>
</body>
</html>
