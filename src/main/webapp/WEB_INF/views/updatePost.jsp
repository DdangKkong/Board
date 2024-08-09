<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Update Post</title>
    <script>
      async function updatePost(event) {
        event.preventDefault();
        const postId = document.getElementById('postId').value;
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;

        const response = await fetch(`/board/posts/${postId}/update`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ title, content, _method: 'PUT' })
        });

        const result = await response.text();
        document.open();
        document.write(result);
        document.close();
      }
    </script>
</head>
<body>
<h1>Update Post</h1>
<form onsubmit="updatePost(event)">
    <input type="hidden" id="postId" name="postId" value="${postId}">
    <input type="hidden" name="_method" value="PUT">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title"><br>
    <label for="content">Content:</label>
    <textarea id="content" name="content"></textarea><br>
    <button type="submit">Update</button>
</form>
</body>
</html>
