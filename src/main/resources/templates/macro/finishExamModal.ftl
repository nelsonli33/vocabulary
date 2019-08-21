<#macro finishExamModal>
    <div aria-hidden="true" aria-labelledby="finishExamModalCenterTitle" class="modal fade"
         id="finishExamModalCenter"
         role="dialog" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title" id="finishExamModalTitle">恭喜！
                        <span class="username"></span>
                        &nbsp;同學已經完成作答！
                    </h4>
                    <button aria-label="Close" class="close" data-dismiss="modal" type="button">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">

                    <p id="score">測驗分數: </p>
                    <div>
                        <h3>歡迎註冊為會員</h3>
                        <p>你將可以享有以下功能：</p>
                        <p>會員練習單字的統計資訊</p>
                    </div>
                </div>

                <!-- Model footer -->
                <div class="modal-footer">
                    <button class="btn btn-secondary" data-dismiss="modal" type="button">Close</button>
                    <button class="btn btn-primary" onclick="location.href='/register';"
                            type="button">註冊
                    </button>
                </div>
            </div>
        </div>
    </div>
</#macro>