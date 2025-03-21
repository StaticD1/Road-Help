<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <title>Validation Error</title>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h2>Validation Error</h2>
                </div>
                <div class="card-body">
                    <p>There were errors during the submission:</p>
                    <ul>
                        <#list errors as error>
                            <li>${error}</li>
                        </#list>
                    </ul>
                    <a href="/signUp" class="btn btn-primary">Back to Sign Up</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
