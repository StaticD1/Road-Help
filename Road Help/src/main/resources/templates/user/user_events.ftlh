<#import "../tags/main_layout.ftlh" as layout>

<@layout.mainLayout title="User Events">
    <link rel="stylesheet" href="/static/css/user_events_style.css">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>User Events</title>
        <script src="https://api-maps.yandex.ru/2.1/?apikey=07c8e749-39a8-4b72-af06-2e9647108053&lang=en_RU"
                type="text/javascript"></script>
        <script src="/static/js/staticEventMap.js" type="text/javascript"></script>
    </head>
    <body>
    <h1>User Events</h1>
    <#list events as event>
        <div class="event-card">
            <h2>${event.title}</h2>
            <p><strong>Description:</strong> ${event.description}</p>
            <p><strong>Contact Info:</strong> ${event.contactInfo}</p>
            <p><strong>Created At:</strong> ${event.createdAt}</p>
            <p><strong>Creator Name:</strong> ${event.creatorName}</p>
            <p><strong>Status:</strong> ${event.status}</p>
            <#if event.status != "FREE">
                <#if event.responderName?has_content>
                    <p><strong>Responder Name:</strong> ${event.responderName}</p>
                </#if>
                <#if event.responseCreatedAt?has_content>
                    <p><strong>Response Created At:</strong> ${event.responseCreatedAt}</p>
                </#if>
            </#if>
            <#if event.creatorId == currentUserId>
                <form action="/event/${event.creatorId}/events/${event.id}/delete" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" class="button">Delete</button>
                </form>
                <form action="/event/${event.creatorId}/events/${event.id}/close" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" class="button">Close</button>
                </form>
                <#if event.status != "FREE">
                    <form action="/event/${event.creatorId}/events/${event.id}/open" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="button">Open</button>
                    </form>
                </#if>
                <form action="/event/${event.creatorId}/events/${event.id}/edit" method="get">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" class="button">Edit</button>
                </form>
            </#if>
            <div id="map-${event.id}" class="map"></div>
            <script>
                initMap(${event.id}, ${event.latitude?c}, ${event.longitude?c});
            </script>
        </div>
    </#list>
    </body>
    </html>
</@layout.mainLayout>
