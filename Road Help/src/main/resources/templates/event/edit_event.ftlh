<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Event</title>
    <script src="https://api-maps.yandex.ru/2.1/?apikey=07c8e749-39a8-4b72-af06-2e9647108053&lang=en_RU"
            type="text/javascript"></script>
    <script src="/static/js/editEventMap.js" type="text/javascript"></script>
    <style>
        .error-message {
            color: red;
            display: none;
        }
    </style>
</head>
<body>
<h1>Edit Event</h1>
<form id="eventForm" method="post" onsubmit="return validateForm()">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="${event.title}" required><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description">${event.description}</textarea><br>

    <label for="contactInfo">Contact Info:</label>
    <input type="text" id="contactInfo" name="contactInfo" value="${event.contactInfo}" required><br>

    <input type="hidden" id="latitude" name="latitude" value="${event.latitude}">
    <input type="hidden" id="longitude" name="longitude" value="${event.longitude}">

    <div id="map" style="width: 600px; height: 400px;"></div>
    <button type="submit">Confirm</button>
    <p class="error-message" id="mapError">Please select a location on the map.</p>
</form>

<script>
    ymaps.ready(function () {
        initMap(${event.latitude?c}, ${event.longitude?c});
    });
</script>
</body>
</html>