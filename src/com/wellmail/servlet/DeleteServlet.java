package com.wellmail.servlet;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
         doGet(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

            httpServletResponse.setContentType("text/html;charset=GB18030");
            PrintWriter out = httpServletResponse.getWriter();

            //1.取参数  

            String path = httpServletRequest.getParameter("path");
            
            File srcFile  = null;

            //2.检验参数是否有问题  

            if(path == null || path.length() == 0) {
                out.println("用户名不能为空");
            } else {

                //3.校验操作

                path = new String(path.getBytes("ISO8859-1"),"GB18030");
                System.out.println("delete path:"+path);
                
                try{
                	/* FileInputStream in = new FileInputStream(path);
              	  FileOutputStream ot = new FileOutputStream("D:/upload/"+name);

              	  int i = 0;
              	  while((i= in.read()) != -1){
              	    ot.write(i);
              	  }
              	  
              	  ot.close();
              	  in.close();*/
                	srcFile  =  new File(path);   
                	File delFile = new File("D:/upload/"+srcFile.getName());
              	  
                   boolean   flag   =   (delFile).delete();   
                   System.out.println(flag);   
              	 
              	 }catch(Exception ex){
              		  ex.printStackTrace();
              	 }
                
        		//4.与传统应用不同之处，这一步需要将用户需要的数据返回给页面端，而不是将一个新的页面返回给客户端

              //  if(path.equals(username)) {
                	//out.println("name:"+name);
                	//out.println("username:"+username);
                 //   out.println("<img src='images/178.gif'> 用户名【<font color='red'>"+ path +"</font>】已经存在！");
                  //  break;
               // }
                            
    			//if(!path.equals(username)) {
    				//out.println("==================");
                	//out.println("name:"+name);
                	//out.println("username:"+username);
    			// out.println("<img src='images/002.gif'>用户名【<font color='green'>"+ path +"</font>】尚未存在,可以使用！");
    			//}

            out.println("文件"+ srcFile.getName()+"删除成功");
    }
}
}