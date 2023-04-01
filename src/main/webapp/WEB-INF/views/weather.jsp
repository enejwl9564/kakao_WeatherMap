<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Weather Information</title>
</head>
<body>
    <h1>Weather Information</h1>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <c:if test="${not empty weatherData}">
        <p>Temperature: ${weatherData.main.temp} K</p>
        <p>Humidity: ${weatherData.main.humidity} %</p>
        <p>Pressure: ${weatherData.main.pressure} hPa</p>
        <p>Wind Speed: ${weatherData.wind.speed} m/s</p>
    </c:if>
    <a href="/">Back to map</a>
</body>
</html>
