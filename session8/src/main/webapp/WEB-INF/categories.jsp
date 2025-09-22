<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Danh Mục</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background: white;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #28a745;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        a {
            text-decoration: none;
            color: #007bff;
            margin-right: 10px;
        }
        a:hover {
            text-decoration: underline;
        }
        button {
            padding: 5px 10px;
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #c82333;
        }
        .message {
            color: green;
            text-align: center;
            margin-top: 20px;
        }
        .pagination {
            margin: 20px 0;
            text-align: center;
        }
        .noActive {
            color: black;
        }

    </style>
</head>
<body>

<h1>Danh Sách Danh Mục</h1>
<button style="background-color: #007bff; color: white">
    <a style="text-decoration: none; color: white" href="/categories/add">Thêm mới danh mục</a>
</button>

<!-- Form tìm kiếm -->
<form action="/categories" method="get">
    <input type="text" name="searchName" value="${searchName}" placeholder="Tìm kiếm danh mục...">
    <button type="submit">Tìm kiếm</button>
</form>

<table>
    <thead>
    <tr>
        <th>STT</th>
        <th>Tên Danh Mục</th>
        <th>Mô tả</th>
        <th>Trạng thái</th>
        <th>Thao Tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="category" items="${categories}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${category.cateName}</td>
            <td>${category.description}</td>
            <td>${category.status ? 'Hoạt động' : 'Không hoạt động'}</td>
            <td>
                <a href="/categories/edit/${category.id}">Sửa</a>
                <a href="/categories/delete/${category.id}" onclick="return confirm('Bạn chắc chắn muốn xóa danh mục này chứ !')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Phân trang -->
<div class="pagination">

    <div style="display: flex ; justify-content: center ; align-items: center ; gap: 10px">
        <c:if test="${page > 1}">
            <a style="color: black" href="/categories?page=${page - 1}&size=${size}&searchName=${searchName}">« Trang Trước</a>
        </c:if>
        <c:forEach var="p" items="${pages}">
            <a class="<c:if test="${page != p}">noActive</c:if>" href="/categories?page=${p}&searchName=${searchName}">Page ${p}</a>
        </c:forEach>
        <c:if test="${page < totalPages}">
            <a style="color: black" href="/categories?page=${page + 1}&size=${size}&searchName=${searchName}">Trang Sau »</a>
        </c:if>
    </div>

</div>

<c:if test="${not empty message}">
    <script>
        alert("${message}");
    </script>
</c:if>

</body>
</html>