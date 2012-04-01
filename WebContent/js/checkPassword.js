function doCheckPassword1(){
		var p1=document.getElementById("psd").value;
		var p2=document.getElementById("newpsd").value;
		var p3=document.getElementById("re_newpsd").value;
		if(p1.match("^[a-zA-Z0-9_]{1,20}$")&&p2.match("^[a-zA-Z0-9_]{1,20}$")
				&&p3.match("^[a-zA-Z0-9_]{1,20}$")){
			return true;
		}else{
			alert("请注意密码格式!!!");
			return false;
		}
}

function doCheckPassword2(){
	var p1=document.getElementById("newpsd").value;
	var p2=document.getElementById("re_newpsd").value;
	if(p1!=p2){
		alert("新密码不一致，请重新填写!");
		return false;
	}else{
		return true;
	}
}

function doCheckAll(){
	if(doCheckPassword1()&&doCheckPassword2()){
		return true;
	}else{
		return false;
	}
}

