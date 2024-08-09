<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Main Page</title>
    <style>
      .current-page {
        font-weight: bold;
      }
    </style>
</head>
<body>
<h1>Posts</h1>
<table border="1">
    <thead>
    <tr>
        <th>Post ID</th>
        <th>User ID</th>
        <th>Title</th>
        <th>Content</th>
        <th>Created Time</th>
        <th>Updated Time</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="post" items="${pagingResponse.postList}">
        <tr>
            <td>${post.postId}</td>
            <td>${post.userId}</td>
            <td>${post.title}</td>
            <td>${post.content}</td>
            <td>${post.createdTime}</td>
            <td>${post.updatedTime}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <c:if test="${pagingResponse.pageNo > 0}">
        <a href="?page=${pagingResponse.pageNo}&size=${pagingResponse.pageSize}">Previous</a>
    </c:if>

    <c:forEach var="i" begin="1" end="${pagingResponse.totalPages}">
        <c:choose>
            <c:when test="${i == pagingResponse.pageNo+1}">
                <span class="current-page">${i}</span>
            </c:when>
            <c:otherwise>
                <a href="?page=${i}&size=${pagingResponse.pageSize}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${pagingResponse.pageNo < pagingResponse.totalPages - 1}">
        <a href="?page=${pagingResponse.pageNo + 2}&size=${pagingResponse.pageSize}">Next</a>
    </c:if>
</div>
</body>
</html>
