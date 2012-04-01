function getCookie(c_name) {
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=");
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1;
			c_end = document.cookie.indexOf(";", c_start);
			if (c_end == -1)
				c_end = document.cookie.length;
			return unescape(document.cookie.substring(c_start, c_end));
		}
	}
	return "";
}

function setCookie(c_name, value, expiredays) {
	if (value == null || value == "") {
		return;
	}
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + expiredays);
	document.cookie = c_name + "=" + escape(value)
			+ ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
}

function checkCookie() {
	var username = getCookie('username');
	var password = getCookie("password");
	var name = $(":text[name='name']").val();
	var psd = $(":password[name='psd']").val();
	
	if(name == null || name.trim().length==0){
		$(":text[name='name']").focus();
	}else if(psd == null || psd.trim().length==0){
		$(":password[name='psd']").focus();
	}else{
		$(":text[name='validateCode']").focus();
	}
	
	if (username != null && username != "" && password != null
			&& password != "") {
		$(":text[name='name']").val(username);
		$(":password[name='psd']").val("jia mi");
		$(":text[name='name']").get(0).readOnly=true;
		$(":password[name='psd']").get(0).readOnly=true;
		$("form[action='login.action']").submit();
	}
}

function delCookie(name)// 删除cookie
{
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

function StartCookie() {

	var username = $(":hidden[name='userName']").val();
	var password = $(":hidden[name='password']").val();
	var cookie = $(":hidden[name='cookie']").val();
	if (cookie != "null") {
		if (username != null && username != "" && password != null
				&& password != "")

		{
			setCookie('username', username, 11);
			setCookie('password', password, 11);
		}
	}
}

