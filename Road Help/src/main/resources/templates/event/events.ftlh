<#import "../tags/main_layout.ftlh" as layout>

<@layout.mainLayout title="Events List">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta name="_csrf" content="${_csrf.token}" />
        <meta name="_csrf_header" content="${_csrf.headerName}" />
        <meta charset="UTF-8">
        <title>Events List</title>
        <script src="https://api-maps.yandex.ru/2.1/?apikey=07c8e749-39a8-4b72-af06-2e9647108053&lang=en_RU"
                type="text/javascript"></script>
        <script src="/static/js/staticEventMap.js" type="text/javascript"></script>
        <script src="/static/js/filterEvents.js" type="text/javascript"></script>
        <style>
            .event-card {
                width: 100%;
                background: white;
                margin: 10px;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .map {
                width: 100%;
                height: 300px;
                margin-top: 16px;
            }
            .button {
                display: inline-block;
                padding: 10px 20px;
                font-size: 16px;
                color: white;
                background: #007bff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                text-align: center;
            }
            .button:disabled {
                background: #ccc;
                cursor: not-allowed;
            }
        </style>
    </head>
    <body>
    <div class="container mt-4">
        <h1 class="text-center mb-4">All Events</h1>
        <div id="eventsContainer" class="row">
            <!-- События будут динамически добавляться сюда -->
        </div>
    </div>
    <script>
        getLocationAndFilterEvents();
    </script>
    </body>
    </html>
</@layout.mainLayout>
