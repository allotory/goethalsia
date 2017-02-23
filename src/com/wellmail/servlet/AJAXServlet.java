package com.wellmail.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AJAXServlet extends HttpServlet {
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

            String old = httpServletRequest.getParameter("name");
           

            //2.检验参数是否有问题  

            if(old == null || old.length() == 0) {
                out.println("用户名不能为空");
            } else {

                //3.校验操作

                String name = new String(old.getBytes("ISO8859-1"));
                
                //数据库连接
                Connection conn = null;
        		Statement stmt = null;
        		ResultSet rs = null;
        		try {
        			Class.forName("com.mysql.jdbc.Driver");
        			conn = DriverManager
        			    .getConnection("jdbc:mysql://localhost/wellmail", "root", "root");
        			String sql = "select username from users";
        			stmt = conn.createStatement();
        			rs = stmt.executeQuery(sql);
        			//System.out.println("deptno	dname");
        			String username = "";
        			while(rs.next()) {
        				username = rs.getString("username");
        				
        				 //4.与传统应用不同之处，这一步需要将用户需要的数据返回给页面端，而不是将一个新的页面返回给客户端

                        if(name.equals(username)) {
                        	//out.println("name:"+name);
                        	//out.println("username:"+username);
                            out.println("<img src='images/178.gif'> 用户名【<font color='red'>"+ name +"</font>】已经存在！");
                            break;
                        }
                            
        			}
        			if(!name.equals(username)) {
        				//out.println("==================");
                    	//out.println("name:"+name);
                    	//out.println("username:"+username);
        			 out.println("<img src='images/002.gif'>用户名【<font color='green'>"+ name +"</font>】尚未存在,可以使用！");
        			}
        		} catch (ClassNotFoundException e) {
        			e.printStackTrace();
        		} catch (SQLException e) {
        			e.printStackTrace();
        		} finally {
        			if(rs != null) {
        				try {
        					rs.close();
        					rs = null;
        				} catch (SQLException e) {
        					e.printStackTrace();
        				}
        			}
        			if(stmt != null) {
        				try {
        					stmt.close();
        					stmt = null;
        				}catch (SQLException e) {
        					e.printStackTrace();
        				}
        			}
        			if(conn != null) {
        				try {
        					conn.close();
        					conn = null;
        				}catch(SQLException e) {
        					e.printStackTrace();
        				}
        			}
        		}

            }
           // out.println("<br/> <a href=\"index.html\">返回校验界面</a>");
              
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
