<#import "../../layout/page.ftl" as layout>
<#import "/spring.ftl" as spring/>

<@layout.page>
    <div class="wrapper">
        <div class="box">
            <h1 class="form-title">E-mail 會員註冊</h1>
            <form action="/register" method="post" id="register">
                <div class="clearfix">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <@spring.bind "registerForm"/>
                    <div class="form-group">
                        <label for="username">姓名：</label>
                        <@spring.formInput "registerForm.username" "class='form-control'"/>
                        <p class="text-danger"><@spring.showErrors "" /></p>
                    </div>
                    <div class="form-group">
                        <label for="email">E-mail：</label>
                        <@spring.formInput "registerForm.email" "class='form-control'"/>
                        <p class="text-danger"><@spring.showErrors "" /></p>
                        <span id="checkIsAccountExist"></span>
                    </div>

                    <div class="form-group">
                        <label for="pwd">密碼：</label>
                        <@spring.formPasswordInput "registerForm.pwd" "class='form-control'"/>
                        <p class="text-danger"><@spring.showErrors "" /></p>
                    </div>

                    <div class="form-group">
                        <label for="checkPwd">確認密碼：</label>
                        <@spring.formPasswordInput "registerForm.checkPwd" "class='form-control'"/>
                        <p class="text-danger"><@spring.showErrors "" /></p>
                    </div>

                    <div class="form-group">
                        <label for="gender">性別：</label>
                        <select name="gender" id="gender" class="form-control">
                            <#list genders as gender>
                                <option value="${gender.code}">${gender.name}</option>
                            </#list>
                        </select>
                    </div>


                    <div class="form-group">
                        <label for="grade">年級：</label>
                        <select name="grade" id="grade" class="form-control">
                            <#list grades as grade>
                                <option value="${grade.code}">${grade.name}</option>
                            </#list>
                        </select>
                    </div>
                    <input type="submit" value="註冊" class="btn-submit"/>
                </div>
            </form>
        </div>
    </div>
</@layout.page>
