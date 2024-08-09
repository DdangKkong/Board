<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Create Post</title>
    <script>
      async function createPost(event) {
        event.preventDefault();
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;

        const response = await fetch('/board/posts/create', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ title, content })
        });

        const result = await response.text();
        document.open();
        document.write(result);
        document.close();
      }
    </script>
</head>
<body>
<h1>Create Post</h1>
<form onsubmit="createPost(event)">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title"><br>
    <label for="content">Content:</label>
    <textarea id="content" name="content"></textarea><br>
    <button type="submit">Create</button>
</form>
</body>
</html>
