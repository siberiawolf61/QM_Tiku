var proId, userType, userID, submitTime;
var questAnswerArr = new Array(10);			//questAnswerArr里面放的是整数：1代表A，2代表B，3代表C，4代表D
var questExplanationArr = new Array(10);
var deltaArr = new Array(10);
var answered = new Array(10);		//某题是否已经答过的标志

var allSponsor = new Array(5);
var allDetail = new Array(10);
var allSelectionA = new Array(10);
var allSelectionB = new Array(10);
var allSelectionC = new Array(10);
var allSelectionD = new Array(10);
var allSelectionCorrect = new Array(10);
var allExplain = new Array(10);
var allRate = new Array(10);

var question, correctRate, selectionA, selectionB, selectionC, selectionD, sponsorName, sponsorIntro, sponsorSite, sponsorTel;
var rightCount, leftCount, fund, OKCount;
var colunmsRight, progressText, progressBarInner, explanation, questionAnswer, explanationText, programUrl, programLogo;

$(document).ready(function(){
	$.ajax({
		  method: "POST",
		  url: "/QM_Tiku/initialization",
		  data: { "jspFlag":0, "projectId":1},
		  dataType: "json",
		  async: false,
		  cache: true,
		  success: function(data){
			  for(var i=0;i<5;i++){
				  allSponsor[i] = data.allSponsor[i].txt;
			  }
			  for(var j=0;j<10;j++){
				  allDetail[j] = data.allDetail[j].txt;
				  allSelectionA[j] = data.allSelectionA[j].txt;
				  allSelectionB[j] = data.allSelectionB[j].txt;
				  allSelectionC[j] = data.allSelectionC[j].txt;
				  allSelectionD[j] = data.allSelectionD[j].txt;
				  allSelectionCorrect[j] = data.allSelectionCorrect[j].txt;
				  allExplain[j] = data.allExplain[j].txt;
				  allRate[j] = data.allRate[j].txt;
			  }
		  },
		  error: function(){
			  alert(textStatus);
		  }
	});
	
	question = $(".question_inner");
	correctRate = $(".correct_rate");
	selectionA = $(".selection_a");
	selectionB = $(".selection_b");
	selectionC = $(".selection_c");
	selectionD = $(".selection_d");
	colunmsRight = $(".columns_right");
	sponsorName = $(".sponsor");
	sponsorIntro = $(".sponsor_intro");
	sponsorSite = $(".sponsor_site");
	sponsorTel = $(".sponsor_tel");
	rightCount = $(".right_count");
	leftCount = $(".left_count");
	fund = $(".fund");
	
	progressText = $(".progress_text");		//进度说明，默认显示
	progressBarInner = $(".progress_bar_inner");
	explanation = $(".explanation");			//解释部分，默认不显示
	questionAnswer = $(".question_answer");
	explanationText = $(".explanation_text");
	programUrl = $(".program_url");
	programLogo = $(".program_logo");
	
	showData();
});

//读取数据后，初始化页面
function showData(){
	sponsorName.text(allSponsor[0]);
	sponsorIntro.text(allSponsor[1]);
	sponsorSite.text(allSponsor[2]);
	sponsorSite.attr("href",allSponsor[2]);
	sponsorTel.text(allSponsor[3]);
	programUrl.attr("href",allSponsor[4]);
	programLogo.attr("href",allSponsor[4]);
	
	for(var i=0; i<10; i++){
		question.eq(i).text(allDetail[i]);
		correctRate.eq(i).text(((1-allRate[i])*100).toFixed(1));
		selectionA.eq(i).text(allSelectionA[i]);
		selectionB.eq(i).text(allSelectionB[i]);
		selectionC.eq(i).text(allSelectionC[i]);
		selectionD.eq(i).text(allSelectionD[i]);
		questExplanationArr[i] = allExplain[i];
		questAnswerArr[i] = allSelectionCorrect[i];
	}
	
	rightCount.text("0");
	OKCount = 6;
	leftCount.text(OKCount);
	fund.text("0");
	deltaArr = [-1,-1,-1,-1,-1,-1,-1,-1,-1,-1];
	answered = [false,false,false,false,false,false,false,false,false,false];
}

//答题时，更新答题信息
function selectionClick(obj){
	var questionNum = 10, selectionNum = 4;
	var contentIdName = obj.parentNode.parentNode.getAttribute("id");
	var selectionClassName = obj.getAttribute("class");
	switch (contentIdName){
		case "content00": questionNum = 0; break;
		case "content01": questionNum = 1; break;
		case "content02": questionNum = 2; break;
		case "content03": questionNum = 3; break;
		case "content04": questionNum = 4; break;
		case "content05": questionNum = 5; break;
		case "content06": questionNum = 6; break;
		case "content07": questionNum = 7; break;
		case "content08": questionNum = 8; break;
		case "content09": questionNum = 9; break;
		default: break;
	}
	switch (selectionClassName){
		case "selection_a": selectionNum = 0; break;
		case "selection_b": selectionNum = 1; break;
		case "selection_c": selectionNum = 2; break;
		case "selection_d": selectionNum = 3; break;
		default: break;
	}
	if (answered[questionNum] == true) return;
	answered[questionNum] = true;
	var answerNum = questAnswerArr[questionNum] - 1;//当前题目的正确答案序号，从0开始
	if (answerNum == selectionNum){
		deltaArr[questionNum] = 0;
		rightCount[0].innerHTML ++;
		var progress = (rightCount[0].innerHTML / OKCount).toFixed(2) * 100;
		if(progress > 100) progress = 100;
		progressBarInner[0].style.width = progress + "%";
		if(leftCount[0].innerHTML != 0) leftCount[0].innerHTML --;
		fund[0].innerHTML = rightCount[0].innerHTML; 
		obj.style.backgroundColor = "lightgreen";
		progressText[0].style.display = "block";
		explanation[0].style.display = "none";
	} else {
		deltaArr[questionNum] = 1;
		obj.style.backgroundColor = "coral";
		obj.parentNode.getElementsByTagName("a")[answerNum].style.backgroundColor = "lightgreen";
		progressText[0].style.display = "none";
		explanation[0].style.display = "block";
		questionAnswer[0].innerHTML = questAnswerArr[questionNum];
		explanationText[0].innerHTML = questExplanationArr[questionNum];
	}
}

//根据滚动条滚动的位置，对columns_right设置需要的CSS属性
$(document).scroll(function(e) {
    if($(document).scrollTop() > 240){
    	colunmsRight.css({ "position": "fixed", "left": "57.1%", "top": "5px" });
    }
    else{
    	colunmsRight.css({ "position": "static", "top": "5px" });
    }
});

//提交按钮
function btnSubmit(){
	if(rightCount[0].innerHTML < OKCount){
		alert("哎呀抱歉，您答对的题目不到 "+OKCount+" 题，无法提交，只能继续答题或重新开始啦啦啦~");
		return ;
	}else{
		alert("提交成功！您完成了本次答题，为您小伙伴的项目支持了 "+fund[0].innerHTML+" 元基金~");
		updateData();
	}
}
//重来按钮
function btnReStart(){
	if(confirm("您确定要放弃本次答题重新开始吗？题目会重新加载哦~")){
		window.location.reload("/page/index03.jsp");
	}else{ return ;	}
}

//提交时，更新数据到数据库
function updateData(){
	userType = "weibo";
	userID = "sisssswww";
	submitTime = new Date().toString();
	
	var deltaArrs = deltaArr[0];
	for(var j=1;j<10;j++){
		deltaArrs = deltaArrs + "," + deltaArr[j];
	}
	
	$.ajax({
		  method: "POST",
		  url: "/QM_Tiku/initialization",
		  data: { "jspFlag":1, "userType":userType, "userID":userID, "submitTime":submitTime, "deltaArrs":deltaArrs },
		  dataType: "text",
		  success: function(jqXHR, textStatus){
			  window.location.href = allSponsor[4];
		  }
	});
}