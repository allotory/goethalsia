	// 导航栏配置文件
	var outlookbar=new outlook();
	var t;
	t=outlookbar.addtitle('基本操作','系统设置',1)
	outlookbar.additem('写信',t,'writetemp.jsp')
	outlookbar.additem('收件箱',t,'goreceive.do')
	outlookbar.additem('草稿箱',t,'goscript.do')
	outlookbar.additem('已发送',t,'gosent.do')
	outlookbar.additem('已删除',t,'godeleted.do')
	outlookbar.additem('垃圾邮件',t,'gorubbish.do')
	outlookbar.additem('广告邮件',t,'goad.do')
	
	t=outlookbar.addtitle('邮箱搬家','系统设置',1)
	outlookbar.additem('添加其他邮箱',t,'addothermailbox.do')
	outlookbar.additem('查看其它邮箱',t,'goothermailbox.do')
	
	t=outlookbar.addtitle('邮箱服务','系统设置',1)
	outlookbar.additem('联系人',t,'gocontactbox.do')
	outlookbar.additem('记事本',t,'gonotebook.do')
	outlookbar.additem('网盘',t,'gowebdiskfolder.do')
