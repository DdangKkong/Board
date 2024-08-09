<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Post Detail</title>
    <script>
      async function deletePost(event) {
        event.preventDefault();
        const confirmation = confirm("Are you sure you want to delete this post?");
        if (confirmation) {
          const postId = document.getElementById('postId').value;

          const response = await fetch(`/board/posts/${post_id}/delete`, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({ _method: 'DELETE' })
          });

          if (response.ok) {
            alert('Post deleted successfully!');
            window.location.href = '/board/posts'; // Redirect to posts list or another appropriate page
          } else {
            const result = await response.json();
            alert(`Failed to delete post: ${result.message}`);
          }
        }
      }

      document.addEventListener('DOMContentLoaded', function() {
        const form = document.getElementById('commentForm');
        form.addEventListener('submit', createComment);
      });

      async function createComment(event) {
        event.preventDefault();
        console.log('createComment 함수 실행');
        const postId = document.getElementById('postId').value;
        const content = document.getElementById('commentContent').value;

        try {
          const response = await fetch('/board/comments', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({ postId, content })
          });

          if (response.ok) {
            const newComment = await response.json();
            console.log('New comment data:', newComment); // 디버깅을 위한 로그
            appendComment(newComment);
            document.getElementById('commentContent').value = '';
          } else {
            const errorText = await response.text();
            console.error('Error response:', errorText);
            alert(`Failed to add comment: ${response.status} ${response.statusText}\n${errorText}`);
          }
        } catch (error) {
          console.error('Error:', error);
          alert('An error occurred while creating the comment');
        }
      }

      function showUpdateForm(commentId, currentContent) {
        document.getElementById('updateCommentId').value = commentId;
        document.getElementById('updateCommentContent').value = currentContent;
        document.getElementById('updateCommentForm').style.display = 'block';
        console.log('commentId : ', commentId);
      }

      async function updateComment(event) {
        event.preventDefault();
        const commentId = document.getElementById('updateCommentId').value;
        const content = document.getElementById('updateCommentContent').value;
        const postId = document.getElementById('postId').value;
        console.log('commentId : ', commentId);
        console.log('content : ', content);

        console.log(`Updating comment `+ commentId +` with content: ${content}`);

        try {
          const response = await fetch(`/board/comments/` + commentId, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({ content, postId })
          });

          console.log('Response status:', response.status);
          console.log('Response headers:', response.headers);

          const responseBody = await response.text();
          console.log('Response body:', responseBody);

          if (response.ok) {
            const result = JSON.parse(responseBody);
            console.log('Update successful:', result);
            alert('Comment updated successfully!');
            location.reload();
          } else {
            console.error('Update failed:', responseBody);
            alert(`Failed to update comment: ${responseBody}`);
          }
        } catch (error) {
          console.error('Error:', error);
          alert('An error occurred while updating the comment');
        }
      }

      async function deleteComment(commentId) {
        const confirmation = confirm("Are you sure you want to delete this comment?");
        const postId = document.getElementById('postId').value;
        if (confirmation) {
          const response = await fetch(`/board/comments/` + commentId, {
            method: 'DELETE',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({ postId })

          });

          if (response.ok) {
            alert('Comment deleted successfully!');
            document.getElementById(`comment-` + commentId).remove();
          } else {
            const result = await response.json();
            alert(`Failed to delete comment: ${result.message}`);
          }
        }
      }

      function appendComment(comment) {
        const commentSection = document.getElementById('comments');
        const newCommentDiv = document.createElement('div');
        newCommentDiv.id = `comment-${comment.commentId}`;
        newCommentDiv.innerHTML = `
          <p>${comment.content}</p>
        <p>Commented by ${comment.userId} on ${comment.formattedCreatedTime}</p>
          <button onclick="showUpdateForm(${comment.commentId}, '${comment.content}')">Update</button>
          <button onclick="deleteComment(${comment.commentId})">Delete</button>
        `;
        commentSection.appendChild(newCommentDiv);
      }

    </script>
</head>
<body>
<h1>Post Detail</h1>
<p>Title: ${post.title}</p>
<p>Content: ${post.content}</p>
<input type="hidden" id="postId" name="postId" value="${post_id}">
<button onclick="location.href='/board/posts/${post_id}/update'">Update</button>
<button onclick="deletePost(event)">Delete</button>

<h2>Comments</h2>
<div id="comments">
    <c:forEach var="comment" items="${comments}">
        <c:if test="${empty comment.deletedTime}">
            <div id="comment-${comment.commentId}" class="comment">
                <p>${comment.content}</p>
                <p>Commented by ${comment.userId} on ${comment.createdTime}</p>
                <button onclick="showUpdateForm(${comment.commentId}, '${comment.content}')">Update</button>
                <button onclick="deleteComment(${comment.commentId})">Delete</button>
            </div>
        </c:if>
    </c:forEach>
</div>

<!-- 세션에서 가져온 현재 로그인한 사용자의 ID를 저장하는 hidden input 추가 -->
<input type="hidden" id="loggedInUserId" value="${sessionScope.userId}">

<h3>Add a Comment</h3>
<form id="commentForm" onsubmit="createComment(event)" >
    <input type="hidden" id="postIdTwo" name="postId" value="${post_id}">
    <textarea id="commentContent" name="content" required></textarea>
    <button type="submit">Create</button>
</form>

<div id="updateCommentForm" style="display:none;">
    <h3>Update Comment</h3>
    <form onsubmit="updateComment(event)">
        <input type="hidden" id="updateCommentId">
        <textarea id="updateCommentContent" required></textarea>
        <button type="submit">Update</button>
    </form>
</div>
</body>
</html>
