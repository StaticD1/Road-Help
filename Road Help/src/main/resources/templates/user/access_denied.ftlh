<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Access Denied</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
        }
        .btn-primary, .btn-secondary {
            width: 100%;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card text-center">
                <div class="card-header">
                    <h1 class="card-title">Access Denied</h1>
                </div>
                <div class="card-body">
                    <p class="card-text">Сначала зарегистрируйтесь</p>
                    <a href="/signUp" class="btn btn-primary">Sign Up</a>
                    <p class="card-text">Уже зарегистрированы?</p>
                    <a href="/signIn" class="btn btn-secondary">Sign In</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
