// 傳給伺服器用來記錄單字正確或錯誤次數	
function postQuesAns(question,answer){
	var data = {};
	data["question"] = question;
	data["answer"] = answer;

	
	// $('#btn-next-question').prop("disabled",true);
	console.log(data);
    $.ajax({
            type: "POST",
            url: window.location +"/api/addVocabularyRightOrWrongNum",
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (data) {
      

                console.log("SUCCESS : ", data);
                // $("#btn-next-question").prop("disabled", false);

            },
            error: function (e) {

                console.log("ERROR : ", e);
               	// $("#btn-next-question").prop("disabled", false);

            }
     });
}



	
// 傳給伺服器會員練習單字紀錄
function postQuesAnsMember(question,answer,account){
	var data = {};
	data["question"] = question;
	data["answer"] = answer;
	data["account"] = account;

	
	console.log(data);
    $.ajax({
            type: "POST",
            url: window.location.origin+"/user/vocabulary/api/insertOrUpdateUserVocabulary",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
      
                console.log("SUCCESS : ", data);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
     });
}