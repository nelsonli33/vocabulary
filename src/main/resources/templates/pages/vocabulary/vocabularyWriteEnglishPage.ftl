<#import "../../layout/page.ftl" as layout>
<#import "../../macro/finishExamModal.ftl" as modal>
<@layout.page>
    <section class="py-5">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3 d-flex flex-column">
                    <p class="text-center">Hello,
                        <span class="username">${username}</span>
                    </p>

                    <br>
                    <div class="progress-main">
                        <div class="progress-section">
                            <div class="progress">
                                <div class="progress-bar bg-dark" id="total-questions-progress" style="width:0%">0</div>
                            </div>
                            <div class="progress-content">
                                <span>總題數</span>
                                <span id="total-questions">10</span>
                            </div>
                        </div>
                        <div class="progress-section">
                            <div class="progress">
                                <div class="progress-bar bg-success" id="right-answers-progress" style="width:0%"></div>
                            </div>
                            <div class="progress-content">
                                <span>答對題數</span>
                                <span id="right-answers-number">0</span>
                            </div>
                        </div>
                        <div class="progress-section">
                            <div class="progress">
                                <div class="progress-bar bg-danger" id="wrong-answers-progress" style="width:0%"></div>
                            </div>
                            <div class="progress-content">
                                <span>答錯題數</span>
                                <span id="wrong-answers-number">0</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-9 pr-3">
                    <div class="container">
                        <div class="d-flex flex-column align-items-center">
                            <div class="card bg-light w-75">
                                <div class="card-body">
                                    <p class="text-dark text-center" id="gameMode1-question"
                                       style="font-size: 2rem;"></p>
                                </div>
                            </div>

                            <section>
                                <div class="container">
                                    <div class="d-flex justify-content-center align-items-center">
                                        <input id="question" type="hidden">
                                        <input autofocus id="input-vocabulary" type="text"/>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </div>
                                </div>
                            </section>

                            <section>
                                <div class="container">
                                    <div class="d-flex justify-content-center">
                                        <div id="right-answer"></div>
                                    </div>
                                </div>
                            </section>

                            <section>
                                <div class="d-flex justify-content-center">

                                    <div class="mr-2">
                                        <button class="btn btn-dark js-check-write-english-answer" type="button">確認
                                        </button>
                                    </div>

                                    <button class="form-control bg-light js-next-question" type="button"/>
                                    請按任意鍵繼續

                                </div>
                            </section>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <@modal.finishExamModal />
    </section>
</@layout.page>