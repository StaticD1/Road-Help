<!-- templates/admin/reports.ftlh -->
<#import "../tags/main_layout.ftlh" as layout>

<@layout.mainLayout title="Reports">
    <div class="container mt-5">
        <h1>Reports</h1>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Reporter</th>
                <th>Reported User</th>
                <th>Title</th>
                <th>Description</th>
                <th>Created At</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <#list reports as report>
                <tr>
                    <td>${report.user.firstName} ${report.user.lastName}</td>
                    <td>${report.reportedUser.firstName} ${report.reportedUser.lastName}</td>
                    <td>${report.title}</td>
                    <td>${report.description}</td>
                    <td>${report.createdAt}</td>
                    <td>
                        <form action="/admin/reports/ban/${report.reportedUser.id}" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger"
                                    <#if report.reportedUser.isBanned()>disabled</#if>>Ban</button>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@layout.mainLayout>
