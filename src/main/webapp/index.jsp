<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h1>와이파이 정보 구하기</h1>

<a href = "/Mission1/index.jsp">홈</a> | <a href = "/Mission1/history.jsp">위치 히스토리 목록</a> | <a href = "/Mission1/loadWifi">Open API 와이파이 정보 가져오기</a> <br> <br>
LAT: <input type = "text" id="lat"> , LNT: <input type = "text" id = "lnt"> <button id = "btn-getCurPosition">내 위치 가져오기</button> <button id = "btn-getWifiList">근처 WIPI 정보 가져오기</button>

<script src = '/Mission1/history.js'></script>
<script src = '/Mission1/wifi.js'></script>

<br>
<br>
<table id="wifi">
    <tr>
        <th>거리</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    
<c:forEach var="wifi" items="${wifiList}">
        <tr>
            <td>${wifi.distance}</td>
            <td>${wifi.mgrNo}</td>
            <td>${wifi.borough}</td>
            <td>${wifi.wifiName}</td>
            <td>${wifi.roadAddress}</td>
            <td>${wifi.detailAddress}</td>
            <td>${wifi.installFloor}</td>
            <td>${wifi.installType}</td>
            <td>${wifi.installAgency}</td>
            <td>${wifi.serviceType}</td>
            <td>${wifi.networkType}</td>
            <td>${wifi.installYear}</td>
            <td>${wifi.indoorOutdoor}</td>
            <td>${wifi.wifiEnvironment}</td>
            <td>${wifi.latitude}</td>
            <td>${wifi.longitude}</td>
            <td>${wifi.workDate}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
