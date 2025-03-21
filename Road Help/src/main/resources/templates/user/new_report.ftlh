<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New Report</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1>Write a Report</h1>
    <form action="/profile/${userId}/report" method="post" class="mt-4">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" class="form-control" value="${reportForm.title?default('')}" required>
            <#if errors?? && errors.getFieldErrors("title")?has_content>
                <#list errors.getFieldErrors("title") as error>
                    <small class="text-danger">${error.defaultMessage}</small>
                </#list>
            </#if>
        </div>
        <div class="form-group">
            <label for="description">Reason:</label>
            <textarea id="description" name="description" class="form-control" required>${reportForm.description?default('')}</textarea>
            <#if errors?? && errors.getFieldErrors("description")?has_content>
                <#list errors.getFieldErrors("description") as error>
                    <small class="text-danger">${error.defaultMessage}</small>
                </#list>
            </#if>
        </div>
        <button type="submit" class="btn btn-primary">Submit Report</button>
    </form>
</div>
</body>
</html>
