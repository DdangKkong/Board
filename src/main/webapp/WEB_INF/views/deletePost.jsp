<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Delete Post</title>
    <script>
      async function deletePost(event) {
        event.preventDefault();
        const postId = document.getElementById('postId').value;

        const response = await fetch(`/board/posts/${postId}/delete`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ _method: 'DELETE' })
        });

        const result = await response.text();
        document.open();
        document.write(result);
        document.close();
      }
    </script>
</head>
<body>
<h1>Delete Post</h1>
<form onsubmit="deletePost(event)">
    <input type="hidden" id="postId" name="postId" value="${postId}">
    <input type="hidden" name="_method" value="DELETE">
    <label for="postId">Post ID:</label>
    <input type="text" id="postIdDisplay" name="postIdDisplay" value="${postId}" disabled><br>
    <button type="submit">Delete</button>
</form>
</body>
</html>
