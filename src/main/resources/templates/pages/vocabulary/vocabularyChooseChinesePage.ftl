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
                                <div id="total-questions-progress" class="progress-bar bg-dark" style="width:0%">0</div>
                            </div>
                            <div class="progress-content">
                                <span>總題數</span>
                                <span id="total-questions-number">10</span>
                            </div>
                        </div>
                        <div class="progress-section">
                            <div class="progress">
                                <div id="right-answers-progress" class="progress-bar bg-success" style="width:0%"></div>
                            </div>
                            <div class="progress-content">
                                <span>答對題數</span>
                                <span id="right-answers-number">0</span>
                            </div>
                        </div>
                        <div class="progress-section">
                            <div class="progress">
                                <div id="wrong-answers-progress" class="progress-bar bg-danger" style="width:0%"></div>
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
                                    <p class="text-dark text-center" style="font-size: 2rem;"
                                       id="gameMode2-question"></p>
                                </div>
                            </div>
                        </div>


                        <section class="answersLayout">

                            <div class="row justify-content-center my-5">
                                <div class="col-md-6 d-flex justify-content-center">
                                    <div class="form-check form-check-inline">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                                   id="inlineRadio1">
                                            <span id="option1-text"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-6 d-flex justify-content-start">
                                    <div class="form-check form-check-inline">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                                   id="inlineRadio2">
                                            <span id="option2-text"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="w-100"></div>
                                <div class="col-md-6 d-flex mt-4 justify-content-center ">
                                    <div class="form-check form-check-inline">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                                   id="inlineRadio3">
                                            <span id="option3-text"></span>
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-6 d-flex mt-4 justify-content-start">
                                    <div class="form-check form-check-inline">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="radio" name="inlineRadioOptions"
                                                   id="inlineRadio4">
                                            <span id="option4-text"></span>
                                        </label>
                                    </div>
                                </div>

                                <div id="rightans-layout">
                                    <div class="col mt-5">
                                        <p>正確答案:<span class="text-danger" id="rightans"></span></p>
                                    </div>
                                </div>

                                <div class="d-flex flex-column align-items-center w-100 my-5">
                                    <button type="button"
                                            class="btn btn-primary btn-lg w-25 my-2 js-check-choose-chinese-answer">
                                        回答
                                    </button>
                                    <button type="button"
                                            class="btn btn-primary btn-lg w-25 my-2 js-mode2-next-question">
                                        請按任意鍵繼續
                                    </button>
                                </div>

                        </section>
                    </div>
                </div>
            </div>
        </div>

        <@modal.finishExamModal />
    </section>
</@layout.page>




