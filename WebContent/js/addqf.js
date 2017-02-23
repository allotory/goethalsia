function changeQF(){

    var cc=document.getElementById("qfa");
     //alert(cc);

	 if(cc.innerText=="使用群发单显"){ 
	 	cc.innerText="删除群发单显";
	 	
	 	 var cc=document.getElementById("cca");
	 	 cc.innerText="添加抄送";
	 	 $.get("CCServlet?name=删除抄送",null ,callbackcc);
	 	 
	 	  var bcc=document.getElementById("bcca");
	 	  bcc.innerText="添加密送";
	 	  $.get("BCCServlet?name=删除密送",null ,callbackbcc);
	 	
	 	
	 	$.get("QFServlet?name=使用群发单显" ,null ,callbackqf);
	 } else {
	 	cc.innerText="使用群发单显";
	 	$.get("QFServlet?name=删除群发单显",null ,callbackqf);
	 } 

    

}

function callbackqf(data) {
   // alert("服务器端的数据回来了！");
   // alert(data);
    var resultObj = document.getElementById("fig");
    resultObj.innerHTML = data;
}


function callbackcc(data) {
    //alert("服务器端的数据回来了！");
   // alert(data);
    var resultObj = document.getElementById("asd");
    resultObj.innerHTML = data;
}


function callbackbcc(data) {
    //alert("服务器端的数据回来了！");
    //alert(data);
    var resultObj = document.getElementById("def");
    resultObj.innerHTML = data;
}