<#import "../../layout/page.ftl" as layout>

<@layout.page>
    <div class="container">
        <div class="row">
            <div class="col d-flex flex-column justify-content-center mt-3">
                <h2>使用者最容易答錯單字前十名</h2>
                <table class="table mt-4">
                    <thead>
                    <tr>
                        <th>單字</th>
                        <th>答錯次數</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list vocabularies as vocabulary>
                        <tr>
                            <td>${vocabulary.englishWord}</td>
                            <td>${vocabulary.wrongAmount}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    </body>
</@layout.page>