/**
 * 달력 js
 */

/* 시작일 달력 */
function uf_makeCalendarStartDate(startEleId, endEleId, icoUrl){
	$("#"+startEleId).datepicker({
		/* start date - text */
		//showButtonPanel: true,
		 changeMonth: true,
		 changeYear: true,
		 dateFormat:'yy-mm-dd',
		 showOn:"both",
		 buttonImage: icoUrl,
		 buttonImageOnly: true,
		 onClose:function(selectedDate){
			 $( "#"+endEleId ).datepicker( "option", "minDate", selectedDate );
		 }
	},$.datepicker.regional[ "ko" ] );
}

/* 종료일 달력 */
function uf_makeCalendarEndDate(startEleId, endEleId, icoUrl){
	$("#"+endEleId).datepicker({
		/* end date - text */
		 changeMonth: true,
		 changeYear: true,
		 dateFormat:'yy-mm-dd',
		 showOn:"both",
		 buttonImage: icoUrl,
		 buttonImageOnly: true,
		 onClose:function(selectedDate){
			 $( "#"+startEleId ).datepicker( "option", "maxDate", selectedDate );
		 }
	},$.datepicker.regional[ "ko" ] );
}

/* 시작일 달력 - 주간 */
function uf_makeCalendarStartDateWeek(startEleId, endEleId, icoUrl){
	/*
	 *  http://blog.naver.com/PostView.nhn?blogId=eun033&logNo=140152867214
	 *  */
	$("#"+startEleId).datepicker({
		/* start date - text */
		//showButtonPanel: true,
		 changeMonth: true,
		 changeYear: true,
		 dateFormat:'yy-mm-dd',
		 showOn:"both",
		 //buttonImage: "/lsiss/images/ico_cal02.png",
		 buttonImage: icoUrl,
		 buttonImageOnly: true,
		 onClose:function(selectedDate){
			 $( "#"+endEleId ).datepicker( "option", "minDate", selectedDate );
		 }
	},$.datepicker.regional[ "ko" ] );
}

/* 종료일 달력 - 주간 */
function uf_makeCalendarEndDateWeek(startEleId, endEleId, icoUrl){
	$("#"+startEleId).datepicker({
		/* end date - text */
		 changeMonth: true,
		 changeYear: true,
		 dateFormat:'yy-mm-dd',
		 showOn:"both",
		 //buttonImage: "/lsiss/images/ico_cal02.png",
		 buttonImage: icoUrl,
		 buttonImageOnly: true,
		 onClose:function(selectedDate){
			 $( "#"+endEleId ).datepicker( "option", "maxDate", selectedDate );
		 }
	},$.datepicker.regional[ "ko" ] );
}

/* 단독 달력 */
function uf_makeCalendarSingleDate(eleId, icoUrl){
	$("#"+eleId).datepicker({
		 changeMonth: true,
		 changeYear: true,
		 format: "yyyy-mm-dd",
		 showOn:"both",
		 todayBtn:"linked",
		 buttonImage: icoUrl,
		 buttonImageOnly: true,
		 autoclose: true,
		 todayHighlight: true,
		 orientation: "top auto",
		 keyboardNavigation: false,
		 forceParse: false
	},$.datepicker.regional[ "ko" ] );
}

function uf_StartDate(startEleId, endEleId, icoUrl){
	$("#"+startEleId).datepicker({
		/* start date - text */
		//showButtonPanel: true,
		 changeMonth: true,
		 changeYear: true,
		 dateFormat:'yy-mm-dd',
		 onClose:function(selectedDate){
			 $( "#"+endEleId ).datepicker( "option", "minDate", selectedDate );
		 }
	},$.datepicker.regional[ "ko" ] );
}

/* 종료일 달력 */
function uf_EndDate(startEleId, endEleId, icoUrl){
	$("#"+startEleId).datepicker({
		/* end date - text */
		 changeMonth: true,
		 changeYear: true,
		 dateFormat:'yy-mm-dd',
		 onClose:function(selectedDate){
			 $( "#"+endEleId ).datepicker( "option", "maxDate", selectedDate );
		 }
	},$.datepicker.regional[ "ko" ] );
}

/* Korean initialisation for the jQuery calendar extension. */
/* Written by DaeKwon Kang (ncrash.dk@gmail.com), Edited by Genie. */
jQuery(function($){
	$.datepicker.regional['ko'] = {
		closeText: '닫기',
		prevText: '이전달',
		nextText: '다음달',
		currentText: '오늘',
		monthNames: ['1월','2월','3월','4월','5월','6월',
		'7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월',
		'7월','8월','9월','10월','11월','12월'],
		dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		weekHeader: 'Wk',
		dateFormat: 'yy-mm-dd',
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: true,
		yearSuffix: '년'};
	$.datepicker.setDefaults($.datepicker.regional['ko']);
});


