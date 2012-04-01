function operatorUser(formID,actionName, username, userID) {
	var form = document.getElementById(formID);
	form.action = actionName;
	form.username.value=username;//赋值方法
	form.userID.value=userID;
	form.submit();
}

function handleUser(username, userID, formID, actionName, admin, type) {
	var confirmMsg = null;
	var infoMsg = null;
	switch (type) {
	case 0:
		confirmMsg = "确认删除吗?";
		infoMsg = "不能删除自己!";
		break;
	case 1:
		confirmMsg = "确认修改吗?";
		infoMsg = "不能修改自己!";
		break;
	default:
		confirmMsg = "确认删除吗?";
		infoMsg = "不能删除自己!";
		break;
	}
	
	if (admin == username) {
		alert(infoMsg);
		return false;
	}
	
	if (window.confirm(confirmMsg)) {
		operatorUser(formID, actionName, username, userID);
	}
	
}

function adminUser(formID,actionName){
	var form = document.getElementById(formID);
	form.action=actionName;
	form.submit();
}

function showUpdate(f) {
	if (f == "true") {
		document.form.name.readOnly = "readOnly";
		document.form.mail.readOnly = "readOnly";
	}
}

function isChange(f) {
	if (f == "true") {
		document.form.permission.disabled = true;
	}
}

function isRecover() {
	document.form.permission.disabled = false;

}
