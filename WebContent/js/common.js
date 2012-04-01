/**
 * 删除左右两端的空格
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * 删除左边的空格
 */
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/g, "");
};

/**
 * 删除右边的空格
 */
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/g, "");
};

function changePage(fname,id) {
	var form = document.getElementById(fname);
	form.id.value=id;
	form.submit();
}

function selAll(name,isChecked) {
	var obj = document.getElementsByName(name);
	for ( var i = 0; i < obj.length; i++) {
		obj[i].checked = isChecked;
	}
}

function isChanged(id, value){
	var obj = document.getElementById(id);
	obj.value = value;
}


function oper(fname,actionName) {
	var form = document.getElementById(fname);
	form.action = actionName;
	form.submit();
}

function handle(fname,actionName,type) {
	var confirmMsg = null;
	switch (type) {
	case 0:
		confirmMsg = "确认删除吗?";
		break;
	case 1:
		confirmMsg = "确认修改吗?";
		break;
	case 2:
		confirmMsg = "确认清空吗?";
		break;
	default:
		confirmMsg = "确认删除吗?";
		break;
	}
	if (window.confirm(confirmMsg)) {
		oper(fname,actionName);
	}
}

function changeIMG(id, str) {
	var date = new Date();
	var obj = document.getElementById(id);
	obj.src = str + date.getTime();
}

$(document).ready(function(){
	$("ul>span").click(function(){
		var id = $(this).attr("id");
		var text = $(this).html();
		if(id == "false"){
			$(this).nextAll("li").show(500);
//			$(this).nextAll("li").css("display","block");
			$(this).html("-"+text.substring(1));
			$(this).attr("id","true");
		}else{
			$(this).nextAll("li").hide(500);
//			$(this).nextAll("li").css("display","none");
			$(this).html("+"+text.substring(1));
			$(this).attr("id","false");
		}
	
	});
});

function startTime(id){
	var DandTMonths=new Array("1","2","3","4","5","6","7","8","9","10","11","12");
	var DandTDays= new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六","星期日");
	var today=new Date();// 声明一个时间对象
	var y = today.getFullYear();
	var m = today.getMonth();
	var d = today.getDate();
	var h=today.getHours();// 获取当前小时
	var m=today.getMinutes();// 获取当前分钟
	var s=today.getSeconds();// 获取当前秒数
	m=checkTime(m);
	s=checkTime(s);
	document.getElementById(id).innerHTML=today.getUTCFullYear()+"年"+DandTMonths[today.getMonth()]+"月"+today.getDate()+"日 "+DandTDays[today.getDay()]+ "  "+ h+":"+m+":"+s ;
	setTimeout("startTime('" + id + "')",1000);
}

function checkTime(i){
	if (i<9){
		i="0" + i;
	}
  return i;
}
