function checkName() {
		var userId = document.getElementById("username").value;
		if (userId.match("^[a-zA-Z][a-zA-Z_0-9]{0,19}$")) {
			return true;
		} else
			return false;
	}
	function doCheckPassword(){
		var p=document.getElementById("psd").value;
		if(p.match("^[a-zA-Z0-9_]{1,20}$")){
			return true;
		}else
		  return false;
	}
	
	function doCheckAll(){
		if(checkName()&&doCheckPassword()&&doCheckEmail()&&docheckUserRealName()){
		     	return true;
	        }else{
		        alert("不符合要求，请仔细阅读规范");
              return false;
         }
	}
	
	function CheckAllUpdate(){
		if(doCheckPassword()&&doCheckEmail()&&docheckUserRealName()){
			return true;
		}
		alert("不符合要求，请仔细阅读规范2");
		return false;
	}
	
	function doCheckEmail(){
		var email=document.getElementById("mail").value;
		if(email.match("^\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+$")){
			return true;
		}else{
		   return false;
		}
	}

	function docheckUserRealName(){
		var name =document.getElementById("name").value;
		 if((/^[a-zA-Z0-9\u4e00-\u9fa5]{1,20}$/).test(name)){
		        return true;
//		 }else if(name.match(/^[a-zA-Z]{1,20}[0-9]{0,2}$/)){
//		        return true;	 
		}else{
		        return false;	
		}
	}