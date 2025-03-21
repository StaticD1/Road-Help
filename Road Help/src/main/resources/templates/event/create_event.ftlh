<#import "../tags/main_layout.ftlh" as layout>

<@layout.mainLayout title="Create Event">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Event</title>
    <script src="https://api-maps.yandex.ru/2.1/?apikey=07c8e749-39a8-4b72-af06-2e9647108053&lang=en_RU"
            type="text/javascript"></script>
    <script src="/static/js/createEventMap.js" type="text/javascript"></script>
    <style>
        .error-message {
            color: red;
            display: none;
        }
    </style>
</head>
<body>
<h1>Create Event</h1>
<form id="eventForm" method="post" onsubmit="return validateForm()">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description"></textarea><br>

    <label for="contactInfo">Contact Info:</label>
    <input type="text" id="contactInfo" name="contactInfo" required><br>

    <input type="hidden" id="latitude" name="latitude">
    <input type="hidden" id="longitude" name="longitude">

    <div id="map" style="width: 600px; height: 400px;"></div>
    <button type="submit">Create Event</button>
    <p class="error-message" id="mapError">Please select a location on the map.</p>

    <#if errors??>
        <div class="error-messages">
            <#list errors as error>
                <p class="error-message">${error.defaultMessage}</p>
            </#list>
        </div>
    </#if>

</form>
</body>
</html>
</@layout.mainLayout>