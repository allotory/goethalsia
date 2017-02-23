function changeBCC(){

    var bcc=document.getElementById("bcca");
    // alert(cc);

	 if(bcc.innerText=="添加密送"){ 
	 	bcc.innerText="删除密送";
	 	
	 	var qf=document.getElementById("qfa");
	    qf.innerText="使用群发单显";
	 	$.get("QFServlet?name=删除群发单显",null ,callbackqf);
	 
	 	
	 	$.get("BCCServlet?name=添加密送" ,null ,callbackbcc);
	 } else {
	 	bcc.innerText="添加密送";
	 	$.get("BCCServlet?name=删除密送",null ,callbackbcc);
	 } 

    

}

function callbackbcc(data) {
    //alert("服务器端的数据回来了！");
    //alert(data);
    var resultObj = document.getElementById("def");
    resultObj.innerHTML = data;
}

function callbackqf(data) {
   // alert("服务器端的数据回来了！");
   // alert(data);
    var resultObj = document.getElementById("fig");
    resultObj.innerHTML = data;
}