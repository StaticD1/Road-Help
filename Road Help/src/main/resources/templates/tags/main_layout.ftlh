<#macro mainLayout title="Default Title">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <link rel="stylesheet" href="/static/css/bootstrap.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background: #f4f4f4;
                margin: 0;
                padding: 20px;
            }
            .navbar {
                margin-bottom: 20px;
            }
            .content {
                display: flex;
                flex-direction: column;
                align-items: center;
            }
        </style>
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/event/all">Events</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/event/new">Create Event</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Profile</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="content">
        <#nested/>
    </div>
    <script src="/static/js/bootstrap.min.js"></script>
    </body>
    </html>
</#macro>
