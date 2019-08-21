<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<header>
    <nav class="navbar navbar-expand-md bg-dark navbar-dark">
        <!-- Brand/logo -->
        <a class="navbar-brand" href="/">單字訓練卡片</a>

        <ul class="nav navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/vocabulary/wrong-answers">使用者最容易錯誤單字表</a>
            </li>
        </ul>
        <@security.authorize access="!hasAnyRole('ROLE_ANONYMOUS')">
            <div class="dropdown ml-auto mr-5">
                <button aria-expanded="false" aria-haspopup="true" class="btn btn-secondary dropdown-toggle"
                        data-toggle="dropdown"
                        id="dropdownMenuButton" type="button">
                    <i class="fas fa-user" style="color:white;"></i>
                </button>
                <span class="text-white">${user.username}</span>
                <div aria-labelledby="dropdownMenuButton" class="dropdown-menu">
                    <a class="dropdown-item" href="/my-account/vocabulary/statistic">統計資訊</a>
                    <a class="dropdown-item" href="/logout">登出</a>
                </div>
            </div>
        </@security.authorize>
        <@security.authorize access="hasAnyRole('ROLE_ANONYMOUS')">
            <ul class="nav navbar-nav ml-auto">
                <li><a href="/register"><i class="far fa-user mr-3 text-white">註冊</i></a></li>
                <li><a href="/login"><i class="fas fa-sign-in-alt mr-3 text-white">登入</i></a></li>
            </ul>
        </@security.authorize>

    </nav>
</header>