<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Exists</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .btn {
            margin: 5px;
        }
    </style>
</head>
<body>
<div class="container text-center">
    <h1>User with this email already exists!</h1>
    <p>Please sign in to continue.</p>
    <a href="/signIn" class="btn btn-primary">Sign In</a>
    <a href="/signUp" class="btn btn-secondary">Sign Up with a different email</a>
</div>
</body>
</html>

