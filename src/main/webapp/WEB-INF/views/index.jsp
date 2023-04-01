<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Weather Map</title>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=kakaomapAPI_Key"></script>
    <style>
        .custom-overlay {
            position: absolute;
            border: 1px solid #888;
            background: rgba(255, 255, 255, 0.7);
            padding: 10px;
            border-radius: 5px;
        }
    </style>
    <script type="text/javascript">
        var map;
        var customOverlay;

        function initMap() {
            var mapContainer = document.getElementById('map'), // Map container
                mapOption = {
                    center: new kakao.maps.LatLng(37.566826, 126.9786567), // Initial map center position
                    level: 3 // Initial map zoom level
                };

            map = new kakao.maps.Map(mapContainer, mapOption);

            customOverlay = new kakao.maps.CustomOverlay({
                position: map.getCenter(),
                content: '<div class="custom-overlay"></div>',
                xAnchor: 0.5,
                yAnchor: 0.5,
                zIndex: 3
            });

            kakao.maps.event.addListener(map, 'mousemove', function (mouseEvent) {
                var latlng = mouseEvent.latLng;
                var latitude = latlng.getLat();
                var longitude = latlng.getLng();
                customOverlay.setPosition(latlng);
                fetchWeather(latitude, longitude);
            });
        }

        function fetchWeather(latitude, longitude) {
            fetch('/weather-data?latitude=' + latitude + '&longitude=' + longitude)
                .then(function (response) {
                    return response.json();
                })
                .then(function (data) {
                    var content = '<div class="custom-overlay">';
                    content += 'Temperature: ' + data.temperature + '°C<br>';
                    content += 'Humidity: ' + data.humidity + '%<br>';
                    content += 'Wind Speed: ' + data.windSpeed + ' m/s<br>';
                    content += '</div>';
                    customOverlay.setContent(content);
                })
                .catch(function (error) {
                    console.error('Error fetching weather data:', error);
                });
        }


    </script>
</head>
<body onload="initMap()">
    <div id="map" style="width:100%;height:800px;"></div>
</body>
</html>
