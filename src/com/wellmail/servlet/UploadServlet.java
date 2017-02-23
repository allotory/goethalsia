package com.wellmail.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {
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
            path = new String(path.getBytes("ISO8859-1"),"GB18030");
            //System.out.println("path:"+path);
            //2.检验参数是否有问题  

            if(path == null || path.length() == 0) {
                out.println("文件名不能为空");
            } else {

                //3.校验操作

                
                
                try{
                	/* FileInputStream in = new FileInputStream(path);
              	  FileOutputStream ot = new FileOutputStream("D:/upload/"+name);

              	  int i = 0;
              	  while((i= in.read()) != -1){
              	    ot.write(i);
              	  }
              	  
              	  ot.close();
              	  in.close();*/
                	System.out.println("path:"+path);
              	  
              	 File srcFile  =  new File(path);   
                 if(!srcFile.exists() || !srcFile.isFile()){   
                     throw new RuntimeException("源文件:'" + path + "'没有被发现！");   
                 }   
                 //path:C:\Documents and Settings\Administrator\桌面\仿163弹出层特效 css.html
                // D:\ProgramFiles\apache-tomcat-6.0.14\bin\仿163弹出层特效 css.html
                 File desFile = new File("../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"+srcFile.getName());   
                 System.out.println(desFile.getAbsolutePath());
                 if(desFile.isDirectory()){   
                     desFile = new File(desFile,srcFile.getName());   
                 }   
                 BufferedInputStream in = new BufferedInputStream(new FileInputStream(srcFile));   
                 BufferedOutputStream ot=new BufferedOutputStream(new FileOutputStream(desFile));   
                 byte[] buff = new byte[4084];   
                 int length=-1;   
                 while((length=in.read(buff))!=-1){   
                     ot.write(buff,0,length);   
                 }   
                 out.flush();
                 in.close();   
                 ot.close();   
                 out.println("上传成功");
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

           
    }
}
}