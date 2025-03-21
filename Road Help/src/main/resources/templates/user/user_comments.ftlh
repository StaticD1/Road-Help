<#import "../tags/main_layout.ftlh" as layout>

<@layout.mainLayout title="User Comments">
    <div class="container mt-5">
        <h1 class="mb-4">User Comments</h1>
        <#list comments as comment>
            <div class="card mb-3">
                <div class="card-body">
                    <p class="card-text"><strong>Commenter:</strong> ${comment.author}</p>
                    <p class="card-text"><strong>Comment:</strong> ${comment.content}</p>
                    <p class="card-text"><small class="text-muted"><strong>Created At:</strong> ${comment.createdAt}</small></p>
                </div>
            </div>
        </#list>
        <a href="/profile/${userId}" class="btn btn-primary mt-3">Back to Profile</a>
    </div>
</@layout.mainLayout>
