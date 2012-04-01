//表示当前高亮的节点
var highlightindex = -1;
$(document).ready(function() {
	
    var wordInput = $("#qword");
    var wordInputOffset = wordInput.offset();
    var autoNode = $("#auto");
    var submitForm = $("#queryForm");
    var hiddenInput = $("#qwordAct");
    var contentType = "xml";
    var timeout;
    var time = 300;
    
    //自动补全框最开始应该隐藏起来
    autoNode.hide().css("border","1px black solid")
    		.css("background-color","white")
    		.css("position","absolute")
            .css("top",wordInputOffset.top + wordInput.height() + 5 + "px")
            .css("left",wordInputOffset.left + "px").width(wordInput.width() + 2);

    //submit()方法请单独在外部重写。
    $("#queryButton").click(submit);
    
    $("body").click(function(){
    	highlightindex = -1;
    	autoNode.hide();
    });
    
    //给文本框添加键盘按下并弹起的事件
    wordInput.keyup(function(event) {
        //处理文本框中的键盘事件
        var myEvent = event || window.event;
        var keyCode = myEvent.keyCode;
        //如果输入的是字母，应该将文本框中最新的信息发送给服务器
        //如果输入的是退格键或删除键，也应该将文本框中的最新信息发送给服务器
        if (keyCode >= 65 && keyCode <= 96 || keyCode == 8 || keyCode == 46) {
            //1.首先获取文本框中的内容
            var wordText = wordInput.val();
            if (wordText != "") {
            	if(timeout != null){
            		clearTimeout(timeout);
            	}
            	
            	var url = $(this).attr("alt");
            	timeout = setTimeout(function(){
            		 //2.将文本框中的内容发送给服务器段
                    $.post(url,{queryWord:wordText},function(data){
                        //将dom对象data转换成JQuery的对象
                        var jqueryObj = $(data);
                        //找到所有的name节点
                        var wordNodes = jqueryObj.find("name");
                        //遍历所有的word节点，取出单词内容，然后将单词内容添加到弹出框中
                        //需要清空原来的内容
                        autoNode.html("");
                        wordNodes.each(function(i) {
                            //获取单词内容
                            var wordNode = $(this);
                            var newDivNode =  $("<div>").attr("id",i);
                            //新建div节点，将单词内容加入到新建的节点中
                            //将新建的节点加入到弹出框的节点中
                            newDivNode.html(wordNode.text()).appendTo(autoNode);
                            
                            newDivNode.mouseover(function(){
                            	if(highlightindex != -1){
                            		autoNode.children("div").eq(highlightindex).css("background-color", "white");
                            	}
                            	highlightindex = $(this).attr("id");
                            	$(this).css("background-color", "red");
                            });
                            
                            newDivNode.mouseout(function(){
                            	//highlightindex = -1;
                            	$(this).css("background-color", "white");
                            });
                            
                            newDivNode.click(function(){
                            	// 下拉框有高亮内容
                                if (highlightindex != -1) {
                                    // 取出高亮节点的文本内容
                                    var comText = $(this).text();
                                    autoNode.hide();
                                    highlightindex = -1;
                                    // 文本框中的内容变成高亮节点的内容
                                    wordInput.val(comText);
                                }
                            });
                        });
                        //如果服务器段有数据返回，则显示弹出框
                        if (wordNodes.length > 0) {
                            autoNode.show();
                        } else {
                            autoNode.hide();
                            //弹出框隐藏的同时，高亮节点索引值也制成-1
                            highlightindex = -1;
                        }
                    },contentType);
            	},time);
               
            } else {
                autoNode.hide();
                highlightindex = -1;
            }
        } else if (keyCode == 38 || keyCode == 40) {
            //如果输入的是向上38向下40按键
            if (keyCode == 38) {
                //向上
                var autoNodes = autoNode.children("div");
                if (highlightindex != -1) {
                    //如果原来存在高亮节点，则将背景色改称白色
                    autoNodes.eq(highlightindex).css("background-color","white");
                    highlightindex--;
                } else {
                    highlightindex = autoNodes.length - 1;    
                }
                if (highlightindex == -1) {
                    //如果修改索引值以后index变成-1，则将索引值指向最后一个元素
                    highlightindex = autoNodes.length - 1;
                }
                //让现在高亮的内容变成红色
                autoNodes.eq(highlightindex).css("background-color","red");
            }
            if (keyCode == 40) {
                //向下
                var autoNodes = autoNode.children("div");
                if (highlightindex != -1) {
                    //如果原来存在高亮节点，则将背景色改称白色
                    autoNodes.eq(highlightindex).css("background-color","white");
                }
                highlightindex++;
                if (highlightindex == autoNodes.length) {
                    //如果修改索引值以后index变成-1，则将索引值指向最后一个元素
                    highlightindex = 0;
                }
                //让现在高亮的内容变成红色
                autoNodes.eq(highlightindex).css("background-color","red");
            }
        } else if (keyCode == 13) {
            //如果输入的是回车

            //下拉框有高亮内容
            if (highlightindex != -1) {
                //取出高亮节点的文本内容
                var comText = autoNode.hide().children("div").eq(highlightindex).text();
                highlightindex = -1;
                //文本框中的内容变成高亮节点的内容
                wordInput.val(comText);
                wordInput.focus();
            } else {
                //下拉框没有高亮内容
                autoNode.hide();
                wordInput.focus();
            }
            //提交请求
            submit();
        }
    });

    
});