package com.test.mail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		 // 连接数据库   
        String driverName = "com.mysql.jdbc.Driver";   
        String dbURL = "jdbc:mysql://localhost/wellmail?autoReconnect=true";   
        String userName = "root";   
        String userPwd = "root";   
        
        Connection conn = null;   
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {   
            Class.forName(driverName);   
            conn = DriverManager.getConnection(dbURL, userName, userPwd);  

            String sql = "insert into cc values(null,'aaa',1)";

            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);   
            pstmt.executeUpdate();   
            
            rs = pstmt.getGeneratedKeys();   
            int newid = 0;   
            if(rs.next()) {
            	newid = rs.getInt(1);
            }
            
            System.out.println(newid);
            
            System.out.println("邮件插入成功"); 
        } catch (ClassNotFoundException e) {   
            e.printStackTrace();   
        } catch (SQLException e) {   
            e.printStackTrace();   
        } finally{
        	if(rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
        	
			if(pstmt != null) {
				try {
					pstmt.close();
					pstmt = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
        
	}

}
