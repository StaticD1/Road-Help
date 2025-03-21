<#import "../tags/main_layout.ftlh" as layout>

<@layout.mainLayout title="User Profile">
    <div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="card mb-4">
                    <div class="card-header">
                        <h2>${user.firstName} ${user.lastName}'s Profile</h2>
                    </div>
                    <div class="card-body">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">
                                <a class="btn btn-primary btn-block" href="/event/${user.id}/created">View Created Events</a>
                            </li>
                            <li class="list-group-item">
                                <a class="btn btn-primary btn-block" href="/reply/${user.id}/replies">View Replies</a>
                            </li>
                            <li class="list-group-item">
                                <a class="btn btn-primary btn-block" href="/profile/${user.id}/comments">View Comments</a>
                            </li>
                            <#if currentUserId?? && currentUserId != user.id>
                            <li class="list-group-item">
                                <a class="btn btn-primary btn-block" href="/profile/${user.id}/comments/add">Add Comment</a>
                            </li>
                                <li class="list-group-item">
                                    <a class="btn btn-danger btn-block" href="/profile/${user.id}/report/new">Write Report</a>
                                </li>
                            </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@layout.mainLayout>
