<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <style>
        #wifi {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #wifi td, #wifi th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #wifi tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #wifi tr:hover {
            background-color: #ddd;
        }

        #wifi th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>

<h1>위치 히스토리 목록</h1>

<a href = "index.jsp">홈</a> | <a href = "history.jsp">위치 히스토리 목록</a> | <a href = "/Mission1/loadWifi">Open API 와이파이 정보 가져오기</a>

<br>
<br>
<table id="wifi">
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>        
    </tr>
    <tr>
        <td>1</td>
        <td>123</td>
        <td>456</td>
        <td>2023-09-29</td>
        <td style="text-align: center;"><button style="text-align: center">삭제</button></td>
    </tr>


</body>
</html>
