var vocabulary = {
    totalQuestions: 10,
    currentQuestionNumber: 0,
    rightAnswerNumber: 0,
    wrongAnswerNumber: 0,
    question: "",
    questionChinese: "",

    init: function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        $('#total-questions').text(this.totalQuestions);
    },

    getRandomVocabulary: function () {
        var mode1Question = document.getElementById('gameMode1-question');
        var inputVocabulary = document.getElementById('input-vocabulary');
        var self = this;

        if (mode1Question !== null) {
            $.ajax({
                url: '/vocabulary/getRandomVocabulary',
                type: 'GET',
                dataType: "json"
            })
                .done(function (response) {
                    var chineseWord = response.chineseWord;
                    var englishWord = response.englishWord;
                    self.question = englishWord;

                    $(mode1Question).text(chineseWord);

                    $(inputVocabulary).attr('maxlength', englishWord.length);

                    // input field 根據單字長度顯示下劃線
                    var character_n = $(inputVocabulary).attr('maxlength');
                    var character_len = character_n * 1.5 + "ch"; // 1.5ch (一個字元+間隔)
                    $(inputVocabulary).css("width", character_len);
                    $(inputVocabulary).focus();

                })
                .fail(function (jqXHR) {
                    console.log(jqXHR);
                })
                .always(function () {
                    $('.js-next-question').hide();
                    $('.js-check-answer').show();
                    $(inputVocabulary).css("color", "black");
                    $(inputVocabulary).val('');
                    $(inputVocabulary).prop("disabled", false);
                    $(inputVocabulary).focus();
                    $('#fa-check-circle').hide();
                    $("#fa-times-circle").hide();
                    $("#right-answer").empty();
                })
        }
    },

    getRandomFourVocabulary: function () {
        var mode2Question = document.getElementById('gameMode2-question');
        var self = this;

        if (mode2Question !== null) {
            $.ajax({
                url: '/vocabulary/getRandomFourVocabulary',
                type: 'GET',
                dataType: "json"
            })
                .done(function (vocabularies) {
                    console.log(vocabularies);
                    $('#inlineRadio1').val(vocabularies[0].englishWord);
                    $('#option1-text').html("(A) " + vocabularies[0].chineseWord);
                    $('#inlineRadio2').val(vocabularies[1].englishWord);
                    $('#option2-text').html("(B) " + vocabularies[1].chineseWord);
                    $('#inlineRadio3').val(vocabularies[2].englishWord);
                    $('#option3-text').html("(C) " + vocabularies[2].chineseWord);
                    $('#inlineRadio4').val(vocabularies[3].englishWord);
                    $('#option4-text').html("(D) " + vocabularies[3].chineseWord);

                    // 選取其中一個選項當題目
                    var index = Math.floor(Math.random() * 4);
                    self.question = vocabularies[index].englishWord;
                    self.questionChinese = vocabularies[index].chineseWord;
                    $(mode2Question).text(vocabularies[index].englishWord);
                })
                .fail(function (jqXHR) {
                    console.log(jqXHR);
                })
                .always(function () {
                    $('.js-mode2-next-question').hide();
                    $('.js-check-choose-chinese-answer').show();
                    $('#rightans-layout').hide();
                })
        }

    },

    bindInputVocabulary: function () {
        $('#input-vocabulary').keypress(function (e) {
            var key = e.which;
            if (key == 13) { // the enter key code
                vocabulary.checkWriteEnglishAnswer();
            }
        });
    },

    bindMode2NextQuestion: function () {
        $('.js-mode2-next-question').click(function (e) {
            e.preventDefault();
            vocabulary.getRandomFourVocabulary();
        })
    },

    bindNextQuestion: function () {
        $('.js-next-question').click(function (e) {
            e.preventDefault();
            vocabulary.getRandomVocabulary();
        });
    },

    bindCheckWriteEnglishAnswer: function () {
        $('.js-check-write-english-answer').click(function (e) {
            e.preventDefault();
            vocabulary.checkWriteEnglishAnswer();
        });
    },


    checkWriteEnglishAnswer: function (answer) {

        var inputVocabulary = document.getElementById('input-vocabulary');
        var answer = $(inputVocabulary).val();

        this.currentQuestionNumber += 1;

        $('#total-questions-progress').width(this.currentQuestionNumber * 10 + "%")
            .text(this.currentQuestionNumber);


        if (answer.toUpperCase() === this.question.toUpperCase()) {
            $(inputVocabulary).css("color", "green");
            $("<i class='fas fa-check-circle fa-3x' style='color:#093;' id='fa-check-circle'></i>").insertAfter($(inputVocabulary));
            this.rightAnswerNumber++;
            $('#right-answers-progress').width(this.rightAnswerNumber * 10 + "%");
            $('#right-answers-number').text(this.rightAnswerNumber);
        } else {
            $(inputVocabulary).css("color", "red");
            $("<i class='fas fa-times-circle fa-3x' style='color:Tomato' id='fa-times-circle'></i>").insertAfter($(inputVocabulary));
            $('#right-answer').append('<p class="text-center">正確答案：' + this.question + '</p>')
            this.wrongAnswerNumber++;
            $('#wrong-answers-progress').width(this.wrongAnswerNumber * 10 + "%");
            $('#wrong-answers-number').text(this.wrongAnswerNumber);
        }


        $('.js-check-write-english-answer').hide();
        $('.js-next-question').show().focus();
        $("#input-vocabulary").prop("disabled", true);


        vocabulary.postQuesAns(this.question, answer);
        // 測驗完畢後顯示Modal
        if (this.currentQuestionNumber === this.totalQuestions) {
            vocabulary.calculateExamScore(this.rightAnswerNumber);
            $('#finishExamModalCenter').modal('show');
        }
    },

    bindCheckChooseChineseAnswer: function () {
        $('.js-check-choose-chinese-answer').click(function (e) {
            e.preventDefault();
            vocabulary.checkChooseChineseAnswer();
        });

    },


    checkChooseChineseAnswer: function () {
        var answer = $('input[name*=inlineRadioOptions]:checked').val();

        this.currentQuestionNumber += 1;
        $('#total-questions-progress').width(this.currentQuestionNumber * 10 + "%")
            .text(this.currentQuestionNumber);

        if (answer.toUpperCase() === this.question.toUpperCase()) {
            alert("正確");
            this.rightAnswerNumber++;
            $('#right-answers-progress').width(this.rightAnswerNumber * 10 + "%");
            $('#right-answers-number').text(this.rightAnswerNumber);

        } else {
            alert("錯誤");
            $('#rightans-layout').show();
            $('#rightans').text(this.questionChinese);
            this.wrongAnswerNumber++;
            $('#wrong-answers-progress').width(this.wrongAnswerNumber * 10 + "%");
            $('#wrong-answers-number').text(this.wrongAnswerNumber);
        }

        vocabulary.postQuesAns(this.question, answer);

        $('.js-check-choose-chinese-answer').hide();
        $('.js-mode2-next-question').show().focus();

        // 測驗完畢後顯示Modal
        if (this.currentQuestionNumber === this.totalQuestions) {
            vocabulary.calculateExamScore(this.rightAnswerNumber);
            $('#finishExamModalCenter').modal('show');
        }
    },

    calculateExamScore: function (rightAnswerNumber) {
        var score = rightAnswerNumber * 10;

        if (score >= 60) {
            $('#score').append(score).css('color', '#093');
        } else {
            $('#score').append(score).css('color', 'Tomato');
        }
    },


    // 傳給伺服器用來記錄單字正確或錯誤次數
    postQuesAns: function (question, answer, csrf) {
        var data = {};
        data["question"] = question;
        data["answer"] = answer;


        $.ajax({
            type: "POST",
            url: "/vocabulary/increVocabularyRightOrWrongAmount",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                console.log(response);
            },
            error: function (jqXHR) {

            }
        });
    },
}


var authenication = {

    checkAccountIfExists: function () {
        $('#email').on("blur", function () {

            var email = $(this).val();


            if (email != '') {

                var data = {};
                data["uid"] = email;

                $.ajax({
                    url: '/register/checkIsAccountExist',
                    type: 'POST',
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8"
                })
                    .done(function (resp) {
                        if (resp === false) {
                            $('#checkIsAccountExist').html("<span class='text-success'>此帳號可以使用</span>");
                        } else {
                            $('#checkIsAccountExist').html("<span class='text-danger'>此帳號已被註冊</span>");
                        }
                    })
            }
        });
    },


}


$(document).ready(function () {
    vocabulary.init();
    vocabulary.getRandomVocabulary();
    vocabulary.bindInputVocabulary();
    vocabulary.bindNextQuestion();
    vocabulary.bindCheckWriteEnglishAnswer();
    vocabulary.getRandomFourVocabulary();
    vocabulary.bindCheckChooseChineseAnswer();
    vocabulary.bindMode2NextQuestion()
    authenication.checkAccountIfExists();
});