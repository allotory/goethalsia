function verify(){

    var jqueryObj = $("#inp_uname");
    var username = jqueryObj.val();
    // alert(username);

    $.get("AJAXServlet?name=" + username ,null ,callback);

}

function callback(data) {
    //alert("服务器端的数据回来了！");
    //alert(data);
    var resultObj = $("#result");
    resultObj.html(data);
}