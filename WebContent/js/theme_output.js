// 嵌入页面中以适应邮箱换肤

var host = location.host;
var sPre = "skin_126green.css";
if(host.indexOf("126.com") > -1){
	sPre = "skin_126green.css";
}else if(host.indexOf("yeah.net") > -1){
	sPre = "skin_163blue.css";
}else if(host.indexOf("188.com") > -1){
	sPre = "skin_163blue.css";
}else if(host.indexOf("vip.163.com") > -1){
	sPre = "skin_163blue.css";
}
document.write('<link href="css/global.css" rel="stylesheet" type="text/css"><link href="/css/'+sPre+'" rel="stylesheet" type="text/css">');
