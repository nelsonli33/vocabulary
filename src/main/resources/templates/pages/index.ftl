<#import "../layout/page.ftl" as layout>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<@layout.page>
    <div class="container">
        <div class="row">
            <div class="col">
                <img alt="中英文卡片" class="img-thumbnail img-center" src="../../static/images/learning.jpeg">

                <form action="vocabulary" class="form-inline form-center" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="typename">
                        <@security.authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
                            <input class="form-control mr-3" name="username" value="${user.username}"
                                   type="text" readonly>
                        </@security.authorize>
                        <@security.authorize access="hasAnyRole('ROLE_ANONYMOUS')">
                            <input class="form-control mr-3" name="username" placeholder="請輸入名字"
                                   type="text">
                        </@security.authorize>

                        <button class="btn btn-primary" type="submit">開始訓練</button>
                    </div>
                    <div class="d-flex my-3 py-3 justify-content-center">
                        <div class="btn-group btn-group-toggle" data-toggle="buttons">
                            <label class="btn btn-outline-secondary active rounded mr-3">
                                <input autocomplete="off" checked name="gameMode" type="radio" value="mode1">
                                看中文拼寫英文
                            </label>
                            <label class="btn btn-outline-secondary rounded">
                                <input autocomplete="off" name="gameMode" type="radio" value="mode2">
                                英文翻譯中文(選擇題)
                            </label>

                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@layout.page>