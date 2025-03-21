<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bad Credentials</title>
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
                    <h1 class="card-title">Bad Credentials</h1>
                </div>
                <div class="card-body">
                    <p class="card-text">Неверные учетные данные пользователя</p>
                    <a href="/signIn" class="btn btn-primary">Попробовать снова</a>
                    <p class="card-text">Не зарегистрированы?</p>
                    <a href="/signUp" class="btn btn-secondary">Sign Up</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
