$(document).ready(function(){
	
	function checkGoodsName() {
		var goodsname = $(":text[name='goodsName']").val();
		if (goodsname.match("^[\\S]+$")) {
			return true;
		} else
			return false;
	}
	
	function checkGoodsUnit() {
		var goodsunit = $(":text[name='goodsUnit']").val();
		if (goodsunit.match("^[\\S]+$")) {
			return true;
		} else
			return false;
	}
	
	$("form[name='goodsform']").submit(function(){
		if(checkGoodsName()&&checkGoodsUnit()){
			return true;
		}else{
			alert("不符合要求，请仔细阅读规范");
			return false;
		}
		
	});
	
});
