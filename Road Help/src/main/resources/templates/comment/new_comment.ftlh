<#import "../tags/main_layout.ftlh" as layout>

<@layout.mainLayout title="New Comment">
    <div class="container mt-5">
        <h1 class="mb-4">New Comment</h1>
        <form action="/profile/${userId}/comments" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label for="content">Comment:</label>
                <textarea class="form-control" id="content" name="content" required>${commentForm.content?default("")}</textarea>
            </div>
            <#if errors?? && errors.hasErrors()>
                <div class="alert alert-danger" role="alert">
                    <ul class="mb-0">
                        <#list errors.allErrors as error>
                            <li>${error.defaultMessage}</li>
                        </#list>
                    </ul>
                </div>
            </#if>
            <button type="submit" class="btn btn-primary mt-3">Submit</button>
        </form>
        <a href="/profile/${userId}" class="btn btn-secondary mt-3">Back to Profile</a>
    </div>
</@layout.mainLayout>
