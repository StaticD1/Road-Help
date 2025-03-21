<#import "../tags/main_layout.ftlh" as layout>

<@layout.mainLayout title="User Replies">
    <link rel="stylesheet" href="/static/css/user_replies_style.css">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>User Replies</title>
        <script src="https://api-maps.yandex.ru/2.1/?apikey=07c8e749-39a8-4b72-af06-2e9647108053&lang=en_RU"
                type="text/javascript"></script>
        <script src="/static/js/staticEventMap.js" type="text/javascript"></script>
    </head>
    <body>
    <h1>User Replies</h1>
    <#list replies as reply>
        <div class="event-card">
            <h2>${reply.eventTitle}</h2>
            <p><strong>Description:</strong> ${reply.eventDescription}</p>
            <p><strong>Created At:</strong> ${reply.eventCreatedAt}</p>
            <p><strong>Status:</strong> ${reply.status}</p>
            <#if reply.status != "FREE">
                <#if reply.responderName??>
                    <p><strong>Responder Name:</strong> ${reply.responderName}</p>
                    <p><strong>Response Created At:</strong> ${reply.createdAt}</p>
                </#if>
            </#if>
            <#if reply.responderId == currentUserId>
                <form action="/reply/delete/${reply.id}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="userId" value="${userId}">
                    <button type="submit" class="button">Delete Reply</button>
                </form>
            </#if>
            <div id="map-${reply.eventId}" class="map"></div>
            <script>
                initMap(${reply.eventId}, ${reply.latitude?c}, ${reply.longitude?c});
            </script>
        </div>
    </#list>
    <a href="/profile/${userId}">Back to Profile</a>
    </body>
    </html>
</@layout.mainLayout>
