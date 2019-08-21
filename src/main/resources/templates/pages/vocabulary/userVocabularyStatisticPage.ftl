<#import "../../layout/page.ftl" as layout>

<@layout.page>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2">
                <aside>
                    <div class="sidenav">
                        <ul>
                            <li><a href="#">單字統計資訊</a></li>
                        </ul>
                    </div>
                </aside>
            </div>

            <div class="col-sm-10">

                <div class="row mt-3">
                    <div class="col-6">
                        <section class="countWords">
                            <div class="card shadow p-3 mb-5 round">
                                <div class="card-header">
                                    練習單字次數
                                </div>
                                <div class="card-body">
                                    <p>已經練習 <span id="vocabulary-count">${userVocabularyCount}</span> 個單字囉</p>
                                </div>
                            </div>
                        </section>
                    </div>

                    <div class="col-6">
                        <section class="ansRatio">
                            <div class="card shadow p-2 mb-5 round">
                                <div class="card-header">
                                    答題比率
                                </div>
                                <div class="card-body">
                                    <p>正確率 : <span id="rightRatio">${rightRatio}</span></p>
                                    <p>錯誤率 : <span id="wrongRatio">${wrongRatio}</span></p>
                                </div>
                            </div>
                        </section>
                    </div>


                    <div class="col-12">
                        <section class="finishedWords">
                            <div class="card shadow-lg p-2 mb-5 round">
                                <div class="card-body">

                                    <table class="table table-striped fixed_header">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">英文</th>
                                            <th scope="col">中文</th>
                                            <th scope="col">答對次數</th>
                                            <th scope="col">答錯次數</th>
                                        </tr>
                                        </thead>
                                        <tbody id="vocabulary-statistic">
                                        <#list userVocabularies as userVocabulary>
                                            <tr>
                                                <td></td>
                                                <td>${userVocabulary.vocabularyData.englishWord}</td>
                                                <td>${userVocabulary.vocabularyData.chineseWord}</td>
                                                <td>${userVocabulary.rightAmount}</td>
                                                <td>${userVocabulary.wrongAmount}</td>
                                            </tr>
                                        </#list>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                        </section>
                    </div>

                </div>
            </div>

        </div>
    </div>

</@layout.page>

