<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <title>Sign In</title>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h2>Sign In</h2>
                </div>
                <div class="card-body">
                    <#if error??>
                        <div class="alert alert-danger" role="alert">
                            ${error}
                        </div>
                    </#if>
                    <#if errors??>
                        <#if errors?size gt 0>
                            <#list errors as error>
                                <div class="alert alert-danger" role="alert">
                                    ${error.defaultMessage}
                                </div>
                            </#list>
                        </#if>
                    </#if>
                    <form method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="form-group">
                            <label for="email">Email <span class="text-danger">*</span></label>
                            <input type="email" class="form-control" name="email" value="" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password <span class="text-danger">*</span></label>
                            <input type="password" class="form-control" name="password" value="" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
