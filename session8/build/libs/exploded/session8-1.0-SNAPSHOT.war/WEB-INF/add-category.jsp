<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Mới Danh Mục</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }
        input[type="text"], select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
        .cancel-button {
            background-color: #dc3545;
        }
        .cancel-button:hover {
            background-color: #c82333;
        }
        .message {
            color: green;
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Thêm Mới Danh Mục</h1>
    <form action="/categories" method="post">
        <input type="hidden" id="id" name="id" value="${category.id}">
        <div class="form-group">
            <label for="cateName">Tên Danh Mục:</label>
            <input type="text" id="cateName" name="cateName" value="${category.cateName}" required placeholder="Nhập tên danh mục">
        </div>
        <div class="form-group">
            <label for="description">Mô Tả:</label>
            <input type="text" id="description" value="${category.description}" name="description" required placeholder="Nhập mô tả danh mục">
        </div>
        <div class="form-group">
            <label for="status">Trạng Thái:</label>
            <select id="status" name="status">
                <option value="true" <c:if test="${category.status}" >
                    selected
                </c:if>>Hoạt Động</option>
                <option value="false" <c:if test="${ not category.status}" >
                    selected
                </c:if>>Ngừng Hoạt Động</option>
            </select>
        </div>
        <button type="submit">Thêm Danh Mục</button>
        <a href="/categories" class="cancel-button" style="display: block; text-align: center; margin-top: 10px; text-decoration: none; color: white;">Hủy</a>
    </form>
    <c:if test="${not empty message}">
        <script>
            alert("${message}");
        </script>
    </c:if>
</div>

</body>
</html>