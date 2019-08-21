<#import "../../layout/page.ftl" as layout>
<#import "/spring.ftl" as spring/>

<@layout.page>
    <div class="wrapper">
        <div class="box">
            <form action="j_spring_security_check" method="post" id="loginForm">
                <div class="clearfix">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <@spring.bind "loginForm"/>
                    <@spring.formInput "loginForm.j_username","class='form-control' placeholder='E-mail帳號'"/>
                    <@spring.formPasswordInput "loginForm.j_password","class='form-control' placeholder='密碼'"  />
                    <input class="btn-submit" type="submit" value="登入"/>
                </div>
            </form>
        </div>
    </div>
</@layout.page>
