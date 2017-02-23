function changeCC(){

    var cc=document.getElementById("cca");
    // alert(cc);

	 if(cc.innerText=="添加抄送"){ 
	 
	   var qf=document.getElementById("qfa");
	   qf.innerText="使用群发单显";
	 	$.get("QFServlet?name=删除群发单显",null ,callbackqf);
	 
	 	cc.innerText="删除抄送";
	 	$.get("CCServlet?name=添加抄送" ,null ,callbackcc);
	 } else {
	 	cc.innerText="添加抄送";
	 	$.get("CCServlet?name=删除抄送",null ,callbackcc);
	 } 

    

}

function callbackcc(data) {
    //alert("服务器端的数据回来了！");
   // alert(data);
    var resultObj = document.getElementById("asd");
    resultObj.innerHTML = data;
}

function callbackqf(data) {
   // alert("服务器端的数据回来了！");
   // alert(data);
    var resultObj = document.getElementById("fig");
    resultObj.innerHTML = data;
}