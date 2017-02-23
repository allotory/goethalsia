package com.wellmail.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CCServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
         doGet(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

          try {
            httpServletResponse.setContentType("text/html;charset=GB18030");
            PrintWriter out = httpServletResponse.getWriter();

            //1.取参数  

            String name = httpServletRequest.getParameter("name");

        	//System.out.println("name1:"+name);

            //2.检验参数是否有问题  

            if(name == null || name.length() == 0) {
                out.println("系统错误，名字为空！");
            } else {

                //3.校验操作

                name = new String(name.getBytes("ISO8859-1"),"GB18030");
                
                
                if(name.equals("添加抄送")){
                	//System.out.println("name2:"+name);
                	out.println("<table class='rTable'  style='padding-top:5px'><tr valign='middle'><td align='right' style='padding-top:5px'>"
                			+"<input type='button' value='抄送人' ></td><td style='padding-top:5px'>"
                			+"<input type='text' name='cc' value='' class='login_table_text1_input' style='font-weight:normal;font-family:Verdana, Arial, Helvetica, sans-serif'  size='90'/>"
                			+"</td></tr></table>");
                }else if(name.equals("删除抄送")) {
                	//System.out.println("name3:"+name);
                	out.println("");
                }
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
